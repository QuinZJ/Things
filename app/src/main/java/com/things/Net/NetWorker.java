package com.things.Net;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by asdf on 2017/4/16.
 */

public class NetWorker {
    private ExecutorService boss = Executors.newSingleThreadExecutor();
    private ExecutorService workers = Executors.newFixedThreadPool(5);
    private LinkedBlockingQueue<NetRequest> netRequests = new LinkedBlockingQueue<>();
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Integer.MIN_VALUE:
                    ((NetRequest) msg.obj).callOnResponse();
                    break;
                default:
                    throw new RuntimeException("Something happened! " + msg.toString());
            }
        }
    };

    public void start() {
        this.boss.execute(new Runnable() {
            @Override
            public void run() {
                while (!boss.isShutdown()) {
                    while (netRequests.size() > 0) {
                        workers.execute(new NetRequestAction(netRequests.poll(), handler));
                    }
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void pushRequest(NetRequest request) {
        this.netRequests.add(request);
    }

    public void stop() {
        this.boss.shutdown();
        this.workers.shutdown();
    }
}
