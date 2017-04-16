package com.things.Spider.HTMLGetter;

import com.things.Bean.HTMLBeanParser;

import org.jsoup.Jsoup;

/**
 * Created by asdf on 2017/4/16.
 */

public class HTMLBeanGetter<Bean> extends HTMLGetter<Bean> {

    private Class<Bean> tClass;

    public HTMLBeanGetter(int tag, Object context) {
        super(tag, context);
    }

    public HTMLBeanGetter<Bean> parse(Class<Bean> tClass) {
        this.tClass = tClass;
        return this;
    }

    @Override
    public Bean getResult() {
        return HTMLBeanParser.parseAll(Jsoup.parse(this.getHtml()), tClass).get(0);
    }
}
