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

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Class untuk mendapatkan data LDAP
 */
public class GetJobs {
    private StringBuilder stringBuilder = new StringBuilder();
    private String [] strException = new String[2];

    public GetJobs() {
        strException[0] = "ExecuteLDAP";
    }

    /**
     * Method untuk login menggunakan LDAP
     */
    public JsonNode all() {
        try
        {
            HttpResponse<JsonNode> response = Unirest.get("https://dev6.dansmultipro.com/api/recruitment/positions.json")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .asJson();
            // Handle the response as needed
            if (response.getStatus() == 200) {
                // Parse the JSON response
                JsonNode responseBody = response.getBody();
                // Extract data1 object
                return responseBody;
            }
        }
        catch (Exception e)
        {
            return null;
        }
        return null;
    }

    /**
     * Method untuk login menggunakan LDAP
     */
    public JsonNode detail(String id) {
        try
        {
            HttpResponse<JsonNode> response = Unirest.get("https://dev6.dansmultipro.com/api/recruitment/positions/" + id)
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .asJson();
            // Handle the response as needed
            if (response.getStatus() == 200) {
                // Parse the JSON response
                JsonNode responseBody = response.getBody();
                // Extract data1 object
                return responseBody;
            }
        }
        catch (Exception e)
        {
            return null;
        }
        return null;
    }
}
