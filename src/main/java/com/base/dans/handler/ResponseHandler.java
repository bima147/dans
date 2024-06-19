package com.base.dans.handler;
/*
IntelliJ IDEA 2023.3.6 (Ultimate Edition)
Build #IU-233.15026.9, built on March 21, 2024
@Author bimaprakoso a.k.a Bima Prakoso
Java Developer
Created on 19/06/24 21.41
@Last Modified 19/06/24 21.41
Version 1.0
 */

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public ResponseHandler() {
    }

    public ResponseEntity<Object> generateResponse(String message,
                                                   HttpStatus status,
                                                   Object responseObj,
                                                   Object errorCode,
                                                   HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj==null?"":responseObj);
        map.put("timestamp", new Date());
        map.put("success",!status.isError());
        if(errorCode != null)
        {
            map.put("errorCode",errorCode);
            map.put("path",request.getPathInfo());
        }
        return new ResponseEntity<Object>(map,status);
    }

    public ResponseEntity<InputStreamResource> generateDownload(String message,
                                                                HttpStatus status,
                                                                Object responseObj,
                                                                Object errorCode,
                                                                HttpServletRequest request) {
        InputStream emptyStream = new ByteArrayInputStream(new byte[0]);
        InputStreamResource emptyResource = new InputStreamResource(emptyStream);

        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj == null ? "" : responseObj);
        map.put("timestamp", new Date());
        map.put("success", !status.isError());
        if (errorCode != null) {
            map.put("errorCode", errorCode);
            map.put("path", request.getPathInfo());
        }

        return ResponseEntity.status(status).body(emptyResource);
    }

    public ResponseEntity<Object> generateResponseUploadDMS(String errorCode,
                                                            String errorMessage,
                                                            Long idDocument,
                                                            String fileName,
                                                            HttpStatus status,
                                                            HttpServletRequest request) {
        Object[] details = new Object[1];
        Map<String, Object> detailsValue = new HashMap<String, Object>();
        detailsValue.put("idDocument", idDocument);
        detailsValue.put("fileName", fileName==null?"":fileName);
        detailsValue.put("errorCode", errorCode);
        detailsValue.put("ErrorMessage", errorMessage);
        details[0] = detailsValue;

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("errorCode", errorCode);
        map.put("errorMessage", errorCode == "00" ? "Success" : "Failed");
        map.put("details", details);
        return new ResponseEntity<Object>(map, status);
    }
}