package com.things.Bean;

/**
 * Created by asdf on 2017/4/16.
 */

@HTMLParseToken("div.mhjsbody.clearfix")
public class ComicBean extends HTMLBean {
    @HTMLToken(elem = "img.fm", type = HTMLToken.Type.ATTR, attr = "src")
    public String pic;
    @HTMLToken(elem = "li:contains(名称)")
    public String name;
    @HTMLToken(elem = "li:contains(状态)")
    public String status;
    @HTMLToken(elem = "li:contains(作者)")
    public String author;
    @HTMLToken(elem = "li:contains(类型)")
    public String type;
    @HTMLToken(elem = "li:contains(更新)")
    public String update;
    @HTMLToken(elem = "div.wz.clearfix.t1 div")
    public String story;
}
