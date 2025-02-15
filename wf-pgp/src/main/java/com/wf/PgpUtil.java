package com.wf;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2025/2/15 10:24
 */

import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.bcpg.HashAlgorithmTags;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.*;
import org.bouncycastle.openpgp.operator.PGPDigestCalculator;
import org.bouncycastle.openpgp.operator.jcajce.*;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

import static jdk.nashorn.internal.objects.NativeString.substring;

public class PgpUtil {

    private static final String IDENTITY="PGPID";

    private static final int KEY_WIDTH=2048;

    static byte[] compressFile(String fileName, int algorithm) throws IOException
    {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        PGPCompressedDataGenerator comData = new PGPCompressedDataGenerator(algorithm);
        PGPUtil.writeFileToLiteralData(comData.open(bOut), PGPLiteralData.BINARY,
                new File(fileName));
        comData.close();
        return bOut.toByteArray();
    }

    /**
     * Search a secret key ring collection for a secret key corresponding to keyID if it
     * exists.
     *
     * @param pgpSec a secret key ring collection.
     * @param keyID keyID we want.
     * @param pass passphrase to decrypt secret key with.
     * @return
     * @throws PGPException
     * @throws NoSuchProviderException
     */
    public static PGPPrivateKey findSecretKey(PGPSecretKeyRingCollection pgpSec, long keyID, char[] pass)
            throws PGPException, NoSuchProviderException
    {
        PGPSecretKey pgpSecKey = pgpSec.getSecretKey(keyID);

        if (pgpSecKey == null)
        {
            return null;
        }

//        return pgpSecKey.extractPrivateKey(pass, "BC");
        return pgpSecKey.extractPrivateKey(new JcePBESecretKeyDecryptorBuilder().setProvider("BC").build(pass));
    }

    static PGPPublicKey readPublicKey(String fileName) throws IOException, PGPException
    {
        InputStream keyIn = new BufferedInputStream(new FileInputStream(fileName));
        PGPPublicKey pubKey = readPublicKey(keyIn);
        keyIn.close();
        return pubKey;
    }

    /**
     * A simple routine that opens a key ring file and loads the first available key
     * suitable for encryption.
     *
     * @param input
     * @return
     * @throws IOException
     * @throws PGPException
     */
    static PGPPublicKey readPublicKey(InputStream input) throws IOException, PGPException
    {
//        PGPPublicKeyRingCollection pgpPub = new PGPPublicKeyRingCollection(
//                PGPUtil.getDecoderStream(input));
        PGPPublicKeyRingCollection pgpPub = new PGPPublicKeyRingCollection(
                PGPUtil.getDecoderStream(input),new JcaKeyFingerprintCalculator());

        //
        // we just loop through the collection till we find a key suitable for encryption, in the real
        // world you would probably want to be a bit smarter about this.
        //

        Iterator keyRingIter = pgpPub.getKeyRings();
        while (keyRingIter.hasNext())
        {
            PGPPublicKeyRing keyRing = (PGPPublicKeyRing)keyRingIter.next();

            Iterator keyIter = keyRing.getPublicKeys();
            while (keyIter.hasNext())
            {
                PGPPublicKey key = (PGPPublicKey)keyIter.next();

                if (key.isEncryptionKey())
                {
                    return key;
                }
            }
        }

        throw new IllegalArgumentException("Can't find encryption key in key ring.");
    }

    static PGPSecretKey readSecretKey(String fileName) throws IOException, PGPException
    {
        InputStream keyIn = new BufferedInputStream(new FileInputStream(fileName));
        PGPSecretKey secKey = readSecretKey(keyIn);
        keyIn.close();
        return secKey;
    }

    /**
     * A simple routine that opens a key ring file and loads the first available key
     * suitable for signature generation.
     *
     * @param input stream to read the secret key ring collection from.
     * @return a secret key.
     * @throws IOException on a problem with using the input stream.
     * @throws PGPException if there is an issue parsing the input stream.
     */
    static PGPSecretKey readSecretKey(InputStream input) throws IOException, PGPException
    {
//        PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(
//                PGPUtil.getDecoderStream(input));
        PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(
                PGPUtil.getDecoderStream(input),new JcaKeyFingerprintCalculator());

        //
        // we just loop through the collection till we find a key suitable for encryption, in the real
        // world you would probably want to be a bit smarter about this.
        //

        Iterator keyRingIter = pgpSec.getKeyRings();
        while (keyRingIter.hasNext())
        {
            PGPSecretKeyRing keyRing = (PGPSecretKeyRing)keyRingIter.next();

            Iterator keyIter = keyRing.getSecretKeys();
            while (keyIter.hasNext())
            {
                PGPSecretKey key = (PGPSecretKey)keyIter.next();

                if (key.isSigningKey())
                {
                    return key;
                }
            }
        }

        throw new IllegalArgumentException("Can't find signing key in key ring.");
    }



    /**
     * 私有方法，用于生成指定位宽的PGP RSA密钥对
     *
     * @param rsaWidth_ RSA密钥位宽
     * @return 未经私钥加密的PGP密钥对
     * @throws Exception IO错误，数值错误等
     */
    private static PGPKeyPair generateKeyPair(int rsaWidth_) throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA", "BC");//获取密钥对生成器实例
        kpg.initialize(rsaWidth_);//设定RSA位宽
        KeyPair kp = kpg.generateKeyPair();//生成RSA密钥对
        return new JcaPGPKeyPair(PGPPublicKey.RSA_GENERAL, kp, new Date());//返回根据日期，密钥对生成的PGP密钥对
    }

    /**
     * 获取PGP密钥<br>
     * 密钥是将密钥对的私钥部分用对称的加密方法CAST-128算法加密，再加上公钥部分
     *
     * @param identity_   密钥ID也就是key值，可以用来标记密钥属于谁
     * @param passPhrase_ 密钥的密码，用来解出私钥
     * @param rsaWidth_   RSA位宽
     * @return PGP密钥
     * @throws Exception IO错误和数值错误等
     */
    public static PGPSecretKey getSecretKey(String identity_, String passPhrase_, int rsaWidth_) throws Exception {
        char[] passPhrase = passPhrase_.toCharArray(); //将passPharse转换成字符数组
        PGPKeyPair keyPair = PgpUtil.generateKeyPair(rsaWidth_); //生成RSA密钥对
        PGPDigestCalculator sha1Calc = new JcaPGPDigestCalculatorProviderBuilder().build().get(HashAlgorithmTags.SHA1); //使用SHA1作为证书的散列算法
        /**
         * 用证书等级生成的认证，将公私钥对和PGP ID密码绑定构造PGP密钥（SecretKey）
         *
         * @param certificationLevel PGP密钥的证书等级
         * @param keyPair 需要绑定的公私钥对
         * @param id 需要绑定的ID
         * @param checksumCalculator 散列值计算器，用于计算私钥密码散列
         * @param hashedPcks the hashed packets to be added to the certification.（先不管）
         * @param unhashedPcks the unhashed packets to be added to the certification.（也先不管）
         * @param certificationSignerBuilder PGP证书的生成器
         * @param keyEncryptor 如果需要加密私钥，需要在这里传入私钥加密器
         * @throws PGPException 一些PGP错误
         */
        return new PGPSecretKey(
                PGPSignature.DEFAULT_CERTIFICATION,
                keyPair,
                identity_,
                sha1Calc,
                null,
                null,
                new JcaPGPContentSignerBuilder(keyPair.getPublicKey().getAlgorithm(), HashAlgorithmTags.SHA1),
                //密钥的加密方式
                new JcePBESecretKeyEncryptorBuilder(PGPEncryptedData.CAST5, sha1Calc).setProvider("BC").build(passPhrase)
        );
    }

    /**
     * 输出带pem的text
     * @param armor
     * @param identity
     * @param passPhrase
     * @param keyWidth
     * @param pubKeyFile
     * @param priKeyFile
     * @return
     * @throws Exception
     */
    public static String[] getPGPSecretkey(boolean armor, String identity, String passPhrase, int keyWidth,String pubKeyFile, String priKeyFile) throws Exception {
        OutputStream priKeyOutputstream;
        OutputStream pubKeyOutputstream;
        try (ByteArrayOutputStream pubKeyOut = new ByteArrayOutputStream();
             ByteArrayOutputStream priKeyOut = new ByteArrayOutputStream()) {
            if (armor) {
                // key format with armored
                priKeyOutputstream = new ArmoredOutputStream(priKeyOut);
                pubKeyOutputstream = new ArmoredOutputStream(pubKeyOut);
            } else {
                // key format without armored
                priKeyOutputstream = new FileOutputStream(priKeyFile);
                pubKeyOutputstream = new FileOutputStream(pubKeyFile);
            }
            Security.addProvider(new BouncyCastleProvider());
            PGPSecretKey secretKey = PgpUtil.getSecretKey(identity, passPhrase, keyWidth);
            secretKey.encode(priKeyOutputstream);
            (priKeyOutputstream).close();
            PGPPublicKey publicKey = secretKey.getPublicKey();
            publicKey.encode(pubKeyOutputstream);
            (pubKeyOutputstream).close();
            String[] keyPair = new String[2];
            if (armor) {
                keyPair[0] = pubKeyOut.toString();
                keyPair[1] = priKeyOut.toString();
            } else {
                keyPair[0] = pubKeyFile;
                keyPair[1] = priKeyFile;

            }
            return keyPair;
        }

    }

    /**
     * 设置密码生成密钥对
     * @param passPhrase
     * @return
     * @throws Exception
     */
    public static String[] generatePePKeyPair(String passPhrase) throws Exception {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        PGPSecretKey secretKey = PgpUtil.getSecretKey(IDENTITY, passPhrase, KEY_WIDTH);
        String privateKeyString = new BASE64Encoder().encode(secretKey.getEncoded());
        PGPPublicKey publicKey = secretKey.getPublicKey();
        byte[] encoded = publicKey.getEncoded();
        String publicKeyString = new BASE64Encoder().encode(encoded);
        String[] keyPair = new String[2];
        keyPair[0] = publicKeyString;
        keyPair[1] = privateKeyString;
        return keyPair;
    }

    /**
     * 根据密码生成带pem的密钥对，包含begin end
     * @param passPhrase
     * @return
     * @throws Exception
     */
    public static String[] generatePePKeyBlockPair(String passPhrase) throws Exception {
        return getPGPSecretkey(true, IDENTITY, passPhrase, KEY_WIDTH, null, null);
    }

    /**
     * 生成带pem的密钥对，包含begin end
     * @param
     * @return
     * @throws Exception
     */
    public static String[] generatePgPKeyBlockPairAndPhrase() throws Exception {
        String[] keyBlockPair = new String[3];
        String passPhrase = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
        String[] keyPair = getPGPSecretkey(true, IDENTITY, passPhrase, KEY_WIDTH, null, null);
        keyBlockPair[0] = keyPair[0];
        keyBlockPair[1] = keyPair[1];
        keyBlockPair[2] = passPhrase;
        return keyBlockPair;
    }

    /**
     * 生成带pem的密钥对，包含begin end
     * @param
     * @return
     * @throws Exception
     */
    public static String[] generatePgPKeyBlockPairAndPhrase(String identity,String passPhrase) throws Exception {
        String[] keyBlockPair = new String[3];
        String[] keyPair = getPGPSecretkey(true, identity, passPhrase, KEY_WIDTH, null, null);
        keyBlockPair[0] = keyPair[0];
        keyBlockPair[1] = keyPair[1];
        keyBlockPair[2] = passPhrase;
        return keyBlockPair;
    }






    @SuppressWarnings("restriction")
    public static void main(String[] args) throws Exception {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        String passPhrase_ = "123456789"; //对密钥进行解密
        char[] passPhrase = passPhrase_.toCharArray(); //将passPharse转换成字符数组

        PGPSecretKey secretKey = PgpUtil.getSecretKey("wathdata", passPhrase_, 2048);

        // 这里打印私钥-------------重要
        String privateKeyString = new BASE64Encoder().encode(secretKey.getEncoded());
        System.out.println(privateKeyString);


        PGPPublicKey publicKey = secretKey.getPublicKey();
        //FileOutputStream fileOutputStream = new FileOutputStream("c://1.txt");
        byte[] encoded = publicKey.getEncoded();
        // 这里打印公钥----------------重要
        String publicKeyString = new BASE64Encoder().encode(encoded);
        System.out.println(publicKeyString);

        String[] stringArr1 = generatePePKeyPair(passPhrase_);

        String[] stringArr2 = generatePePKeyBlockPair(passPhrase_);

        String[] stringArr3 = generatePgPKeyBlockPairAndPhrase();

        System.out.println("-----------privateKey--------");
        System.out.println(stringArr3[1]);
        System.out.println("-----------publicKey--------");
        System.out.println(stringArr3[0]);

        System.out.println("-----------passPhrase--------");
        System.out.println(stringArr3[2]);

    }


}
