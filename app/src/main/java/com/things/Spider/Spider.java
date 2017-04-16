package com.things.Spider;

import android.util.Log;

import com.things.Bean.ComicBean;
import com.things.Bean.HTMLBeanParser;
import com.things.Bean.SummaryBean.ComicSummaryBean;
import com.things.Net.OnResponse;
import com.things.Spider.HTMLGetter.DefaultHTMLGetter;
import com.things.Spider.HTMLGetter.HTMLBeanGetter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by asdf on 2017/4/16.
 */

public class Spider {

    private static final String HOST = "http://www.manhuatai.com/";
    private static final int MSG_TAG = 0;
    private static final int MSG_BOOK = 1;

    public Spider() {

    }

    public void tag(String tag) {
        String url = String.format(HOST + "%s.html", tag);
        new DefaultHTMLGetter(MSG_TAG, this).open(url).send();
    }

    public void book(String bookName) {
        String url = String.format(HOST + "%s", bookName);
        new HTMLBeanGetter<ComicBean>(MSG_BOOK, this)
                .parse(ComicBean.class)
                .open(url)
                .send();
    }


    @OnResponse(MSG_TAG)
    private void getTag(String html) {
        Document doc = Jsoup.parse(html);
        for (ComicSummaryBean comicSummaryBean : HTMLBeanParser.parseAll(doc, ComicSummaryBean.class)) {
            Log.e("bean", comicSummaryBean.toString());
        }
    }

    @OnResponse(MSG_BOOK)
    private void getBook(ComicBean comicBean) {
        Log.e("bean", comicBean.toString());
    }


}
