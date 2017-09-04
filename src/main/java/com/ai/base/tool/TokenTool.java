package com.ai.base.tool;

import java.util.UUID;

public class TokenTool {
    public static String getToken(final String uuid) {
        return UUID.randomUUID().toString().toUpperCase();
    }

    public static String getMark() {
//		SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
//		return sdf.format(new Date());
        return (Math.random() + "").substring(2, 8);
    }

    public static void main(String[] args) {
        System.out.println(TokenTool.getToken(""));
        System.out.println(TokenTool.getMark());
        System.out.println((Math.random() + "").substring(2, 12));
    }
}
