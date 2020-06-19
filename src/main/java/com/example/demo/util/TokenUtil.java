package com.example.demo.util;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.math.BigInteger;
import java.util.HashMap;

/**
 * 类名称: TokenUtil
 * 类描述: Token工具类
 *
 * @author: wqy
 * 创建时间: 2020/6/16 5:38 下午
 * Version 1.0
 */

@Component
public class TokenUtil {
    private static HashMap<Integer, String> realTimeTokens = new HashMap<Integer, String>(); // userId, Token

    /**
     * 查询是否存在Token
     * @param token
     * @return
     */
    public static boolean FindToken(String token){
        if (realTimeTokens.containsValue(token))
            return true;
        else
            return false;
    }

    /**
     * 查询是否已经存在user对应的token
     * @param userId
     * @return
     */
    public static boolean FindTokenByUser(int userId){
        if (realTimeTokens.containsKey(userId))
            return true;
        else
            return false;
    }

    /**
     * 生成Token
     * @param userId
     * @return
     */
    public static String GenerateToken(int userId){
        long curr = System.currentTimeMillis();
        String token = DigestUtils.md5DigestAsHex(String.valueOf(userId + "-" + curr).getBytes());
        token = new BigInteger(1, token.getBytes()).toString(16);
        realTimeTokens.put(userId, token);
        return token;
    }

    /**
     * 移除Token
     * @param userId 要移除的token对应的userId
     * @return 如果不存在Token，返回false；否则，返回true
     */
    public static boolean RemoveToken(int userId){
        if (FindTokenByUser(userId)) {
            realTimeTokens.remove(userId);
            return true;
        }else
            return false;
    }

    /**
     * 获取Token
     * @param userId
     * @return
     */
    public static String GetToken(int userId){
        if (realTimeTokens.containsKey(userId))
            return realTimeTokens.get(userId);
        else
            return GenerateToken(userId);
    }
}