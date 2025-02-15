package com.wf;

import org.apache.sshd.server.auth.AsyncAuthException;
import org.apache.sshd.server.auth.password.PasswordAuthenticator;
import org.apache.sshd.server.auth.password.PasswordChangeRequiredException;
import org.apache.sshd.server.session.ServerSession;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2025/2/15 9:28
 */
public class MyPasswordAuthenticator implements PasswordAuthenticator {
    @Override
    public boolean authenticate(String s, String s1, ServerSession serverSession) throws PasswordChangeRequiredException, AsyncAuthException {
        return true;
    }

    @Override
    public boolean handleClientPasswordChangeRequest(ServerSession session, String username, String oldPassword, String newPassword) {
        return PasswordAuthenticator.super.handleClientPasswordChangeRequest(session, username, oldPassword, newPassword);
    }
}
