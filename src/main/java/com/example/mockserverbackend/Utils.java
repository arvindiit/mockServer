package com.example.mockserverbackend;

public class Utils {

    public static String[] getTenantAndPath(String completePath){
        String[] tenantAndPath = new String[2];
        int i = completePath.indexOf("/");
        tenantAndPath[0] = completePath.substring(0, i);
        tenantAndPath[1] = completePath.substring(i+1);

        return tenantAndPath;
    }
}
