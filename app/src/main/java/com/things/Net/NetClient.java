package com.things.Net;

/**
 * Created by asdf on 2017/4/16.
 */

public class NetClient {
    private static NetClient instance = null;
    private NetWorker worker = new NetWorker();

    private NetClient() {}

    public static NetClient getInstance() {
        if (NetClient.instance == null) {
            synchronized (NetClient.class) {
                if (NetClient.instance == null) {
                    NetClient.instance = new NetClient();
                }
            }
        }
        return NetClient.instance;
    }

    public void execRequest(final NetRequest request) {
        this.worker.pushRequest(request);
    }
}
