package com.things.Net;

import android.os.Handler;

import java.net.URL;
import java.net.URLConnection;

/**
 * Created by asdf on 2017/4/16.
 */

public class NetRequestAction implements Runnable {

    private NetRequest request;
    private Handler handler;
    private URLConnection connection;

    public NetRequestAction(NetRequest request, Handler handler) {
        this.request = request;
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            connection = new URL(request.getUrl()).openConnection();
            if (NetRequest.METHOD_POST.equals(request.getMethod())) {
                this.connection.setDoOutput(true);
                this.request.handleOutputStream(this.connection.getOutputStream());
            }
            this.request.handleInputStream(this.connection.getInputStream());
        } catch (Exception e) {
            this.request.exception = e;
        }
        this.handler.obtainMessage(Integer.MIN_VALUE, this.request).sendToTarget();
    }
}
