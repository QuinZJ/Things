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

public abstract class HTMLGetter<Result> extends NetRequest<HTMLGetter, Result> {

    private String html;
    private String postData = "";

    public HTMLGetter(int tag, Object context) {
        super(tag, context);
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

    public abstract Result getResult();

    public String getHtml() {
        return html;
    }

}
