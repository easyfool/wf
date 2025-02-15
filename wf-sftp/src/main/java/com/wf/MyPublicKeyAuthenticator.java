package com.wf;

import org.apache.sshd.server.auth.AsyncAuthException;
import org.apache.sshd.server.auth.pubkey.PublickeyAuthenticator;
import org.apache.sshd.server.session.ServerSession;

import java.security.PublicKey;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2025/2/15 9:29
 */
public class MyPublicKeyAuthenticator implements PublickeyAuthenticator {
    @Override
    public boolean authenticate(String s, PublicKey publicKey, ServerSession serverSession) throws AsyncAuthException {
        return false;
    }
}
