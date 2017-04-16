package com.things.Spider.HTMLGetter;

import com.things.Net.NetRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Created by asdf on 2017/4/16.
 */

public class HTMLGetter extends NetRequest<HTMLGetter> {

    private String html;
    private String postData = "";

    private HTMLGetter(int tag, Object context) {
        super(tag, context);
    }

    public static Builder with(Object context) {
        return new Builder(context);
    }

    private HTMLGetter setPostData(String postData) {
        this.postData = postData;
        return this;
    }

    @Override
    public void handleInputStream(InputStream inputStream) {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line).append('\n');
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.html = builder.toString();
    }

    @Override
    public void handleOutputStream(OutputStream outputStream) {
        PrintWriter writer = new PrintWriter(outputStream);
        writer.print(this.postData);
        writer.flush();
        writer.close();
    }

    public String getHtml() {
        return html;
    }

    public static class Builder {

        private Object context;
        private String method;
        private String url;
        private String data;

        public Builder(Object context) {
            this.context = context;
        }

        public Builder args(String data) {
            this.data = data;
            return this;
        }

        public Builder load(String url) {
            return this.load(METHOD_GET, url);
        }

        public Builder load(String method, String url) {
            this.method = method;
            this.url = url;
            return this;
        }

        public void into(int what) {
            new HTMLGetter(what, this.context)
                    .open(this.method, this.url)
                    .setPostData(this.data)
                    .send();
        }
    }
}
