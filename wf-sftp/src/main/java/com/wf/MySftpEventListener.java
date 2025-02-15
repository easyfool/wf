package com.wf;

import org.apache.sshd.common.AttributeRepository;
import org.apache.sshd.server.session.ServerSession;
import org.apache.sshd.sftp.server.DirectoryHandle;
import org.apache.sshd.sftp.server.FileHandle;
import org.apache.sshd.sftp.server.Handle;
import org.apache.sshd.sftp.server.SftpEventListener;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2025/2/15 9:33
 */
public class MySftpEventListener implements SftpEventListener {
    public static final AttributeRepository.AttributeKey<FileHandleContext> FILEHANDLE_CONTEXT_ATTRIBUTE_KEY = new AttributeRepository.AttributeKey();

    @Override
    public void received(ServerSession session, int type, int id) throws IOException {
        SftpEventListener.super.received(session, type, id);
    }

    @Override
    public void receivedExtension(ServerSession session, String extension, int id) throws IOException {
        SftpEventListener.super.receivedExtension(session, extension, id);
    }

    @Override
    public void initialized(ServerSession session, int version) throws IOException {
        SftpEventListener.super.initialized(session, version);
    }

    @Override
    public void exiting(ServerSession session, Handle handle) throws IOException {
        SftpEventListener.super.exiting(session, handle);
    }

    @Override
    public void destroying(ServerSession session) throws IOException {
        SftpEventListener.super.destroying(session);
    }

    @Override
    public void opening(ServerSession session, String remoteHandle, Handle localHandle) throws IOException {
        SftpEventListener.super.opening(session, remoteHandle, localHandle);
    }

    @Override
    public void open(ServerSession session, String remoteHandle, Handle localHandle) throws IOException {
        SftpEventListener.super.open(session, remoteHandle, localHandle);
    }

    @Override
    public void openFailed(ServerSession session, String remotePath, Path localPath, boolean isDirectory, Throwable thrown) throws IOException {
        SftpEventListener.super.openFailed(session, remotePath, localPath, isDirectory, thrown);
    }

    @Override
    public void readingEntries(ServerSession session, String remoteHandle, DirectoryHandle localHandle) throws IOException {
        SftpEventListener.super.readingEntries(session, remoteHandle, localHandle);
    }

    @Override
    public void readEntries(ServerSession session, String remoteHandle, DirectoryHandle localHandle, Map<String, Path> entries) throws IOException {
        SftpEventListener.super.readEntries(session, remoteHandle, localHandle, entries);
    }

    @Override
    public void reading(ServerSession session, String remoteHandle, FileHandle localHandle, long offset, byte[] data, int dataOffset, int dataLen) throws IOException {
        SftpEventListener.super.reading(session, remoteHandle, localHandle, offset, data, dataOffset, dataLen);
    }

    @Override
    public void read(ServerSession session, String remoteHandle, FileHandle localHandle, long offset, byte[] data, int dataOffset, int dataLen, int readLen, Throwable thrown) throws IOException {
        SftpEventListener.super.read(session, remoteHandle, localHandle, offset, data, dataOffset, dataLen, readLen, thrown);
    }

    @Override
    public void writing(ServerSession session, String remoteHandle, FileHandle localHandle, long offset, byte[] data, int dataOffset, int dataLen) throws IOException {
        SftpEventListener.super.writing(session, remoteHandle, localHandle, offset, data, dataOffset, dataLen);
    }

    @Override
    public void written(ServerSession session, String remoteHandle, FileHandle localHandle, long offset, byte[] data, int dataOffset, int dataLen, Throwable thrown) throws IOException {
        SftpEventListener.super.written(session, remoteHandle, localHandle, offset, data, dataOffset, dataLen, thrown);
    }

    @Override
    public void blocking(ServerSession session, String remoteHandle, FileHandle localHandle, long offset, long length, int mask) throws IOException {
        SftpEventListener.super.blocking(session, remoteHandle, localHandle, offset, length, mask);
    }

    @Override
    public void blocked(ServerSession session, String remoteHandle, FileHandle localHandle, long offset, long length, int mask, Throwable thrown) throws IOException {
        SftpEventListener.super.blocked(session, remoteHandle, localHandle, offset, length, mask, thrown);
    }

    @Override
    public void unblocking(ServerSession session, String remoteHandle, FileHandle localHandle, long offset, long length) throws IOException {
        SftpEventListener.super.unblocking(session, remoteHandle, localHandle, offset, length);
    }

    @Override
    public void unblocked(ServerSession session, String remoteHandle, FileHandle localHandle, long offset, long length, Throwable thrown) throws IOException {
        SftpEventListener.super.unblocked(session, remoteHandle, localHandle, offset, length, thrown);
    }

    @Override
    public void closing(ServerSession session, String remoteHandle, Handle localHandle) throws IOException {
        SftpEventListener.super.closing(session, remoteHandle, localHandle);
    }

    @Override
    public void closed(ServerSession session, String remoteHandle, Handle localHandle, Throwable thrown) throws IOException {
        SftpEventListener.super.closed(session, remoteHandle, localHandle, thrown);
    }

    @Override
    public void creating(ServerSession session, Path path, Map<String, ?> attrs) throws IOException {
        SftpEventListener.super.creating(session, path, attrs);
    }

    @Override
    public void created(ServerSession session, Path path, Map<String, ?> attrs, Throwable thrown) throws IOException {
        SftpEventListener.super.created(session, path, attrs, thrown);
    }

    @Override
    public void moving(ServerSession session, Path srcPath, Path dstPath, Collection<CopyOption> opts) throws IOException {
        SftpEventListener.super.moving(session, srcPath, dstPath, opts);
    }

    @Override
    public void moved(ServerSession session, Path srcPath, Path dstPath, Collection<CopyOption> opts, Throwable thrown) throws IOException {
        SftpEventListener.super.moved(session, srcPath, dstPath, opts, thrown);
    }

    @Override
    public void removing(ServerSession session, Path path, boolean isDirectory) throws IOException {
        SftpEventListener.super.removing(session, path, isDirectory);
    }

    @Override
    public void removed(ServerSession session, Path path, boolean isDirectory, Throwable thrown) throws IOException {
        SftpEventListener.super.removed(session, path, isDirectory, thrown);
    }

    @Override
    public void linking(ServerSession session, Path source, Path target, boolean symLink) throws IOException {
        SftpEventListener.super.linking(session, source, target, symLink);
    }

    @Override
    public void linked(ServerSession session, Path source, Path target, boolean symLink, Throwable thrown) throws IOException {
        SftpEventListener.super.linked(session, source, target, symLink, thrown);
    }

    @Override
    public void modifyingAttributes(ServerSession session, Path path, Map<String, ?> attrs) throws IOException {
        SftpEventListener.super.modifyingAttributes(session, path, attrs);
    }

    @Override
    public void modifiedAttributes(ServerSession session, Path path, Map<String, ?> attrs, Throwable thrown) throws IOException {
        SftpEventListener.super.modifiedAttributes(session, path, attrs, thrown);
    }
}
