package com.wf;

import org.apache.sshd.common.file.virtualfs.VirtualFileSystemFactory;
import org.apache.sshd.common.session.SessionContext;
import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.shell.ProcessShellCommandFactory;
import org.apache.sshd.sftp.server.SftpSubsystemFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2025/2/15 9:26
 */
public class SftpServer {
    private static final Integer PORT = 2222;
    private static final String HOST = "127.0.0.1";
    private static final String BASE_PATH = "D:\\workspace\\idea\\wf\\wf-sftp\\doc\\baseDir";
    private static final String SIGNATURE_FILE = "D:\\workspace\\idea\\wf\\wf-sftp\\doc\\";

    public static void main(String[] args) {
        startSftpServer();
    }

    private static void startSftpServer() {
        //创建SshServer对象
        SshServer sshd = SshServer.setUpDefaultServer();
        sshd.setHost(HOST);
        //配置端口
        sshd.setPort(PORT);
        //设置默认的签名文件，如果文件不存在会创建
        sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider(Paths.get(SIGNATURE_FILE)));
//        sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider(Paths.get("/opt/key")));
        //设置用户名和密码进行登录验证
        sshd.setPasswordAuthenticator(new MyPasswordAuthenticator());
        sshd.setPublickeyAuthenticator(new MyPublicKeyAuthenticator());
        //设置sftp子系统
        SftpSubsystemFactory sftpSubsystemFactory = new SftpSubsystemFactory();
        sftpSubsystemFactory.addSftpEventListener(new MySftpEventListener());
        sshd.setSubsystemFactories(Arrays.asList(sftpSubsystemFactory));

        //设置sfp默认的访问目录
        Path dir = Paths.get(BASE_PATH);

        sshd.setFileSystemFactory(new VirtualFileSystemFactory(dir.toAbsolutePath()));
        //给每个用户分配不同的访问目录
        sshd.setFileSystemFactory(new VirtualFileSystemFactory(dir.toAbsolutePath()) {
            @Override
            public Path getUserHomeDir(SessionContext session) throws IOException {
                String username = session.getUsername();
                Path homeDir = getUserHomeDir(username);
                if (homeDir == null) {
                    //这里给每个用户修改为默认目录+用户名+dir的目录格式
                    //可以根据实际的需求修改此处的代码
                    homeDir = getDefaultHomeDir().resolve(username + "dir");
                    setUserHomeDir(username, homeDir);
                }
                File file = new File(String.valueOf(homeDir));
                file.mkdirs();
                return homeDir;
            }
        });
        //设置ssh的shell环境
//        sshd.setShellFactory((c) -> {
//            ServerSession session = c.getSession();
//            String username = session.getUsername();
//            //除了root以外的的用户都不允许远程登录
//            if ("root".equals(username)) {
//                return InteractiveProcessShellFactory.INSTANCE.createShell(c);
//            } else {
//                return null;
//            }
//        });
        sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider());
        sshd.setCommandFactory(new ProcessShellCommandFactory());
        //启动ssh服务
        try {
            sshd.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Object obj = new Object();
        synchronized (obj) {
            try {
                obj.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
