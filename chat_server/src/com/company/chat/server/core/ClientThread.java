package com.company.chat.server.core;

import com.company.chat.common.Library;
import com.company.network.SocketThread;
import com.company.network.SocketThreadListener;

import java.net.Socket;

public class ClientThread extends SocketThread {

    private String nickname;
    private boolean isAuthorized;
    private boolean isReconnecting;

    public ClientThread(SocketThreadListener listener, String name, Socket socket) {
        super(name, listener, socket);
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    void authAccept(String nickname) {
        isAuthorized = true;
        this.nickname = nickname;
        sendMessage(Library.getAuthAccept(nickname));
    }

    void authFail() {
        sendMessage(Library.getAuthDenied());
        close();
    }

    void msgFormatError(String msg) {
        sendMessage(Library.getMsgFormatError(msg));
        close();
    }

    public boolean isReconnecting() {
        return isReconnecting;
    }
    void reconnect() {
        isReconnecting = true;
        close();
    }
}
