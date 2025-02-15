package com.wf;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2025/2/15 10:23
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Iterator;

import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPCompressedData;
import org.bouncycastle.openpgp.PGPCompressedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedData;
import org.bouncycastle.openpgp.PGPEncryptedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedDataList;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPLiteralData;
import org.bouncycastle.openpgp.PGPObjectFactory;
import org.bouncycastle.openpgp.PGPOnePassSignatureList;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPPublicKeyEncryptedData;
import org.bouncycastle.openpgp.PGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.jcajce.JcaPGPObjectFactory;
import org.bouncycastle.openpgp.operator.jcajce.JcaKeyFingerprintCalculator;
import org.bouncycastle.openpgp.operator.jcajce.JcePGPDataEncryptorBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePublicKeyDataDecryptorFactoryBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePublicKeyKeyEncryptionMethodGenerator;
import org.bouncycastle.util.io.Streams;

import static com.wf.PgpUtil.findSecretKey;
import static com.wf.PgpUtil.readPublicKey;

/**
 * A simple utility class that encrypts/decrypts public key based
 * encryption large files.
 */
public class KeyBasedLargeFileProcessor {
    /**
     * @param inputFileName   要解密的文件名
     * @param keyFileName     私钥
     * @param passwd          私钥解密key
     * @param defaultFileName 输出解密的文件
     * @throws IOException
     * @throws NoSuchProviderException
     */
    public static void decryptFile(
            String inputFileName,
            String keyFileName,
            char[] passwd,
            String defaultFileName)
            throws IOException, NoSuchProviderException {
        InputStream in = new BufferedInputStream(new FileInputStream(inputFileName));
        InputStream keyIn = new BufferedInputStream(new FileInputStream(keyFileName));
        decryptFile(in, keyIn, passwd, defaultFileName);
        keyIn.close();
        in.close();
    }

    /**
     * decrypt the passed in message stream
     */
    public static void decryptFile(
            InputStream in,
            InputStream keyIn,
            char[] passwd,
            String defaultFileName)
            throws IOException, NoSuchProviderException {
        in = PGPUtil.getDecoderStream(in);

        try {
            PGPObjectFactory pgpF = new JcaPGPObjectFactory(in);
            PGPEncryptedDataList enc;

            Object o = pgpF.nextObject();
            //
            // the first object might be a PGP marker packet.
            //
            if (o instanceof PGPEncryptedDataList) {
                enc = (PGPEncryptedDataList) o;
            } else {
                enc = (PGPEncryptedDataList) pgpF.nextObject();
            }

            //
            // find the secret key
            //
            Iterator it = enc.getEncryptedDataObjects();
            PGPPrivateKey sKey = null;
            PGPPublicKeyEncryptedData pbe = null;
//            PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(
//                    keyIn, new JcaKeyFingerprintCalculator());
            PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(
                    PGPUtil.getDecoderStream(keyIn), new JcaKeyFingerprintCalculator());

            while (sKey == null && it.hasNext()) {
                pbe = (PGPPublicKeyEncryptedData) it.next();

                sKey = findSecretKey(pgpSec, pbe.getKeyID(), passwd);
            }

            if (sKey == null) {
                throw new IllegalArgumentException("secret key for message not found.");
            }

            InputStream clear = pbe.getDataStream(new JcePublicKeyDataDecryptorFactoryBuilder().setProvider("BC").build(sKey));

//            PGPObjectFactory    plainFact = new PGPObjectFactory(clear);
            PGPObjectFactory plainFact = new JcaPGPObjectFactory(clear);

            PGPCompressedData cData = (PGPCompressedData) plainFact.nextObject();

            InputStream compressedStream = new BufferedInputStream(cData.getDataStream());
            //PGPObjectFactory    pgpFact = new PGPObjectFactory(compressedStream);
            PGPObjectFactory pgpFact = new JcaPGPObjectFactory(compressedStream);

            Object message = pgpFact.nextObject();

            if (message instanceof PGPLiteralData) {
                PGPLiteralData ld = (PGPLiteralData) message;

                String outFileName = ld.getFileName();
                if (outFileName.length() == 0) {
                    outFileName = defaultFileName;
                }

                InputStream unc = ld.getInputStream();
                OutputStream fOut = new BufferedOutputStream(new FileOutputStream(outFileName));

                Streams.pipeAll(unc, fOut);

                fOut.close();
            } else if (message instanceof PGPOnePassSignatureList) {
                throw new PGPException("encrypted message contains a signed message - not literal data.");
            } else {
                throw new PGPException("message is not a simple encrypted file - type unknown.");
            }

            if (pbe.isIntegrityProtected()) {
                if (!pbe.verify()) {
                    System.err.println("message failed integrity check");
                } else {
                    System.err.println("message integrity check passed");
                }
            } else {
                System.err.println("no message integrity check");
            }
        } catch (PGPException e) {
            System.err.println(e);
            if (e.getUnderlyingException() != null) {
                e.getUnderlyingException().printStackTrace();
            }
        }
    }

    /**
     * @param outputFileName
     * @param inputFileName
     * @param encKeyFileName
     * @param armor              指示是否应该使用ASCII装甲格式
     * @param withIntegrityCheck 检查完整性
     * @throws IOException
     * @throws NoSuchProviderException
     * @throws PGPException
     */
    public static void encryptFile(
            String outputFileName,
            String inputFileName,
            String encKeyFileName,
            boolean armor,
            boolean withIntegrityCheck)
            throws IOException, NoSuchProviderException, PGPException {
        OutputStream out = new BufferedOutputStream(new FileOutputStream(outputFileName));
        PGPPublicKey encKey = readPublicKey(encKeyFileName);
        encryptFile(out, inputFileName, encKey, armor, withIntegrityCheck);
        out.close();
    }

    /**
     * @param out
     * @param fileName
     * @param encKey
     * @param armor              指示是否应该使用ASCII装甲格式
     * @param withIntegrityCheck 检查完整性
     * @throws IOException
     * @throws NoSuchProviderException
     */
    public static void encryptFile(
            OutputStream out,
            String fileName,
            PGPPublicKey encKey,
            boolean armor,
            boolean withIntegrityCheck)
            throws IOException, NoSuchProviderException {
        if (armor) {
            out = new ArmoredOutputStream(out);
        }

        try {
            //PGPEncryptedDataGenerator   cPk = new PGPEncryptedDataGenerator(new JcePGPDataEncryptorBuilder(PGPEncryptedData.CAST5).setWithIntegrityPacket(withIntegrityCheck).setSecureRandom(new SecureRandom()).setProvider("BC"));
            PGPEncryptedDataGenerator cPk = new PGPEncryptedDataGenerator(new JcePGPDataEncryptorBuilder(PGPEncryptedData.AES_256).setWithIntegrityPacket(withIntegrityCheck).setSecureRandom(new SecureRandom()).setProvider("BC"));


            cPk.addMethod(new JcePublicKeyKeyEncryptionMethodGenerator(encKey).setProvider("BC"));

            OutputStream cOut = cPk.open(out, new byte[1 << 16]);

            PGPCompressedDataGenerator comData = new PGPCompressedDataGenerator(
                    PGPCompressedData.ZIP);

            PGPUtil.writeFileToLiteralData(comData.open(cOut), PGPLiteralData.BINARY, new File(fileName), new byte[1 << 16]);

            comData.close();

            cOut.close();

            if (armor) {
                out.close();
            }
        } catch (PGPException e) {
            System.err.println(e);
            if (e.getUnderlyingException() != null) {
                e.getUnderlyingException().printStackTrace();
            }
        }
    }

    public static void main(
            String[] args)
            throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        encryptFile("Encypted File.txt.pgp", "Key.asc", "public.pem", true, true);

        decryptFile("Encypted File.txt.pgp", "private.pem", "123456789".toCharArray(), "Encypted File.txt");


    }
}
