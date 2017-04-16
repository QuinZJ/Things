package com.things.Spider.HTMLGetter;

/**
 * Created by asdf on 2017/4/16.
 */

public class DefaultHTMLGetter extends HTMLGetter<String> {
    public DefaultHTMLGetter(int tag, Object context) {
        super(tag, context);
    }

    @Override
    public String getResult() {
        return this.getHtml();
    }
}
