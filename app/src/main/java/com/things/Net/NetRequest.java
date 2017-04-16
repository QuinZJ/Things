package com.things.Net;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;

/**
 * Created by asdf on 2017/4/16.
 */

public abstract class NetRequest<SubRequest, Result> {
    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";

    private Object context;
    private String url;
    private String method;
    protected Exception exception;

    private Method onSuccess;
    private Method onFail;

    public NetRequest() { }

    public NetRequest(int tag, Object context) {
        this.context = context;

        for (Method method : this.context.getClass().getDeclaredMethods()) {
            OnResponse onResponse = method.getAnnotation(OnResponse.class);
            if (onResponse == null) continue;
            if (onResponse.value() != tag) continue;
            method.setAccessible(true);
            if (onResponse.type().equals(OnResponse.Type.SUCCESS)) this.onSuccess = method;
            if (onResponse.type().equals(OnResponse.Type.FAIL)) this.onFail = method;
        }
    }

    public abstract void handleInputStream(InputStream inputStream);
    public abstract void handleOutputStream(OutputStream outputStream);
    public abstract Result getResult();

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public SubRequest open(String url) {
        return this.open(METHOD_GET, url);
    }

    public SubRequest open(String method, String url) {
        this.url = url;
        this.method = method;
        return (SubRequest) this;
    }

    public void send() {
        NetClient.getInstance()
                 .execRequest(this);
    }

    public void callOnResponse() {
        try {
            if (this.exception != null) {
                if (this.onFail == null) return;
                this.onFail.invoke(this.context, this);
            } else {
                if (this.onSuccess == null) return;
                this.onSuccess.invoke(this.context, this.getResult());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
