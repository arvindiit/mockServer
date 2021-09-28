package com.example.mockserverbackend.rest.service;

import com.example.mockserverbackend.rest.entity.CustomRestRequestHeaders;

import java.util.Map;
import java.util.Set;

public class ServiceHelper {

    public static boolean doAllHeadersMatch(Set<CustomRestRequestHeaders> customRestRequestHeaders, Map<String, String> headers){
        for (CustomRestRequestHeaders customRestRequestHeader: customRestRequestHeaders){
            String value = headers.get(customRestRequestHeader.getName());
            if(value != null && !value.equalsIgnoreCase(customRestRequestHeader.getValue())){
                return false;
            }
        }

        return true;
    }

}
