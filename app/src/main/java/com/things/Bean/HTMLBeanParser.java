package com.things.Bean;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by asdf on 2017/4/16.
 */

public class HTMLBeanParser {
    public static <T> T parse(Element element, Class<T> tClass) {
        T bean = null;
        try {
            bean = tClass.newInstance();
            for (Field field : tClass.getDeclaredFields()) {
                HTMLToken htmlToken = field.getAnnotation(HTMLToken.class);
                if (htmlToken == null) continue;
                field.setAccessible(true);
                try {
                    String elemStr = htmlToken.elem();
                    Element elem = HTMLToken.SELF.equals(elemStr) ? element : element.select(elemStr).first();
                    HTMLToken.Type type = htmlToken.type();
                    if (HTMLToken.Type.TEXT.equals(type)) {
                        field.set(bean, elem.text());
                    } else if (HTMLToken.Type.ATTR.equals(type)) {
                        field.set(bean, elem.attr(htmlToken.attr()));
                    }

                } catch (Exception e) {
                    //e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static <T> ArrayList<T> parseAll(Document document, Class<T> tClass) {
        ArrayList<T> beans = new ArrayList<>();
        for (Element element : document.select(tClass.getAnnotation(HTMLParseToken.class).value())) {
            beans.add(HTMLBeanParser.parse(element, tClass));
        }
        return beans;
    }
}
