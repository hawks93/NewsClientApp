package com.hawks93.newsclientapp.global;

/**
 * 作者hawks93
 * 版本1.0
 * 创建时间 2015/11/25 14:40
 */
public class GlobalContants {
    //模拟器访问不同主机可用10.0.2.2访问，对应主机ip.不同电脑可用127.0.0.1访问
    public static final String SERVER_URL="http://10.0.2.2:8080/zhbj";
    public static final String CATEGORIES_URL=SERVER_URL+"/categories.json";//获取分类信息的接口
}
