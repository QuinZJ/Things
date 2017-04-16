package com.things.Bean.SummaryBean;

import com.things.Bean.HTMLBean;
import com.things.Bean.HTMLToken;

/**
 * Created by asdf on 2017/4/16.
 */

public class ComicSummaryBean extends HTMLBean {
    @HTMLToken(type = HTMLToken.Type.ATTR, attr = "href")
    public String url;
    @HTMLToken(type = HTMLToken.Type.ATTR, attr = "title")
    public String title;
    @HTMLToken(elem = "img", type = HTMLToken.Type.ATTR, attr = "src")
    public String pic;
    @HTMLToken(elem = "li.type")
    public String type;
    @HTMLToken(elem = "li.status")
    public String status;
    @HTMLToken(elem = "li:contains(剧情)")
    public String story;
}
