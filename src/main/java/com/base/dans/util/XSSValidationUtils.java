package com.base.dans.util;
/*
IntelliJ IDEA 2023.3.6 (Ultimate Edition)
Build #IU-233.15026.9, built on March 21, 2024
@Author bimaprakoso a.k.a Bima Prakoso
Java Developer
Created on 19/06/24 21.41
@Last Modified 19/06/24 21.41
Version 1.0
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XSSValidationUtils {

    /** ini adalah pola standar yang digunakan hacker untuk menyerang dengan xss*/
    private static final Pattern pattern = Pattern.compile("^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.\\/?\\s]*$", Pattern.CASE_INSENSITIVE);

    public static boolean isValidURL(String uri, List<String> listKataTerlarang) {
        AtomicBoolean flag= new AtomicBoolean(false);
         String[]  urls=uri.split("\\/");

        Arrays.stream(urls).filter(e->!StringUtils.isEmpty(e)).forEach(url->{
            String val = String.valueOf(url);
           // if(listKataTerlarang.stream().anyMatch(val::equalsIgnoreCase)){
            if(listKataTerlarang.stream().anyMatch(p->val.toLowerCase().contains(p.toLowerCase()))){
//                System.out.println("kata yang dilarang ditemukan !!!!!");
                flag.set(true);
            }
            Matcher matcher = pattern.matcher(val);
            if (!matcher.matches()) {
//                System.out.println("pola yang dilarang ditemukan !!!!!");
                flag.set(true);
            }
            /** buka pada saat development saja , tapi tutup manual saat pentest*/
//            else{
//                System.out.println("valid char found: "+val);
//            }
        });
        return !flag.get();
    }

    public static boolean isValidRequestParam(String param, List<String> listKataTerlarang) {
        AtomicBoolean flag= new AtomicBoolean(false);
        String[]  paramList=param.split("&");

        Arrays.stream(paramList).filter(e->!StringUtils.isEmpty(e)).forEach(url->{
            String val=String.valueOf(url);
//            System.out.println("value:"+val);
            if(listKataTerlarang.stream().anyMatch(val::equalsIgnoreCase)){
//                System.out.println("kata yang dilarang ditemukan !!!!!");
                flag.set(true);
            }
            Matcher matcher = pattern.matcher(val);
            if (!matcher.matches()) {
//                System.out.println("pola yang dilarang ditemukan!!!!!");
                flag.set(true);
            }
            /** buka pada saat development saja , tapi tutup manual saat pentest*/
//            else{
//                System.out.println("Aman  : "+val);
//            }
        });
        return !flag.get();
    }


    public static boolean isValidURLPattern(String uri, List<String> listKataTerlarang) {
        AtomicBoolean flag= new AtomicBoolean(false);
        String[]  urls=uri.split("\\/");

        try {
            Arrays.stream(urls).filter(e -> !StringUtils.isEmpty(e)).forEach(url -> {
                String val = String.valueOf(url);
                Map<String, Object> mapping = jsonToMap(new JSONObject(val));
//                System.out.println("Map; " + mapping);
                mapping.forEach((key, value) -> {
                    if (listKataTerlarang.stream().anyMatch(String.valueOf(value)::equalsIgnoreCase)) {
//                        System.out.println("kata yang dilarang ditemukan !!!!!");
                        flag.set(true);
                    }
                    Matcher matcher = pattern.matcher(String.valueOf(value));
                    if (!matcher.matches()) {
                        System.out.println(key + "  : Pola yang dilarang ditemukan!!!!!");
                        flag.set(true);
                    }
                    /** buka pada saat development saja , tapi tutup manual saat pentest*/
//                    else {
//                        System.out.println("Aman : " + String.valueOf(value));
//                    }
                });
            });
        }catch(Exception ex){
            if (!ex.getMessage().contains("Unterminated string at")) {
                flag.set(true);
            }
        }
        return !flag.get();
    }

    public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
        Map<String, Object> retMap = new HashMap<String, Object>();

        if(json != JSONObject.NULL) {
            retMap = toMap(json,retMap);
        }
        return retMap;
    }

    public static Map<String, Object> toMap(JSONObject object, Map<String, Object> map) throws JSONException {


        Iterator<String> keysItr = object.keys();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);
         //   System.out.println("key  "+key+"  value:"+ value);
            if(value instanceof JSONArray) {
                value = toList(key,(JSONArray) value,map);
            }else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value,map);
            }else {
                map.put(key, value);
            }
        }
        return map;
    }

    public static List<Object> toList(String key,JSONArray array,Map<String, Object> map ) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for(int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if(value instanceof JSONArray) {
                value = toList(key,(JSONArray) value,map);
            }else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value,map);
            }else{
                String mapValue=String.valueOf(value);
                if(map.containsKey(key)){
                    mapValue+=","+String.valueOf(map.get(key));
                }
                map.put(key, mapValue);
            }
            list.add(value);
        }
        return list;
    }


    public static String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

}