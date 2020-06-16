package com.example.demo.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 类名称: CookieUtil
 * 类描述: Cookie工具类
 *
 * @author: wqy
 * 创建时间: 2020/6/16 6:24 下午
 * Version 1.0
 */

public class CookieUtil {
    /**
     * 从请求的得到对应的Cookie
     *
     * @param request
     * @param key
     * @return
     */
    public static String getValue(HttpServletRequest request, String key) {
        Cookie cookie = get(request, key);
        if (cookie != null) {
            return cookie.getValue();
        }
        return null;
    }

    /**
     * 查询请求中的Cookie
     *
     * @param request
     * @param key
     */
    private static Cookie get(HttpServletRequest request, String key) {
        Cookie[] arr_cookie = request.getCookies();
        if (arr_cookie != null && arr_cookie.length > 0) {
            for (Cookie cookie : arr_cookie) {
                if (cookie.getName().equals(key)) {
                    return cookie;
                }
            }
        }
        return null;
    }
}