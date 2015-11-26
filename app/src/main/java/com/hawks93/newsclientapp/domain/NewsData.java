package com.hawks93.newsclientapp.domain;

import java.util.ArrayList;

/**
 * 作者hawks93
 * 版本1.0
 * 创建时间 2015/11/25 15:06
 */
public class NewsData {
    public int retcode;
    public ArrayList<NewsMenuData> data;

    //侧边栏数据对象
    public class NewsMenuData {
        public String id;
        public String title;
        public String url;
        public int type;
        public ArrayList<NewsTabData> children;

        @Override
        public String toString() {
            return "NewsMenuData{" +
                    "title='" + title + '\'' +
                    ", children=" + children +
                    '}';
        }
    }

    //新闻底下的11个子页签的对象
    public class NewsTabData {
        public String id;
        public String title;
        public String url;
        public int type;

        @Override
        public String toString() {
            return "NewsTabData{" +
                    "title='" + title + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NewsData{" +
                "data=" + data +
                '}';
    }
}
