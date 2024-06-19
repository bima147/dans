package com.base.dans.controller;
/*
IntelliJ IDEA 2023.3.6 (Ultimate Edition)
Build #IU-233.15026.9, built on March 21, 2024
@Author bimaprakoso a.k.a Bima Prakoso
Java Developer
Created on 19/06/24 21.35
@Last Modified 19/06/24 21.35
Version 1.0
 */

import com.base.dans.dto.user.LoginDTO;
import com.base.dans.handler.ResponseHandler;
import com.base.dans.models.Users;
import com.base.dans.services.JobsService;
import com.base.dans.services.UsersService;
import com.base.dans.util.ConstantMessage;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller untuk API Users
 */
@RestController
@RequestMapping("/api")
public class JobController {
    /** Membuat variabel model mapper */
    @Autowired
    private ModelMapper modelMapper;

    /** Insiasi service users */
    @Autowired
    JobsService jobsService;

    /** Membuat variabel untuk melakukan sorting */
    private Map<String,String> mapSorting = new HashMap<String,String>();

    /** Membuat konstruktor dari controller barang */
    public JobController() {
        mapSorting();
    }

    /** Melakukan mapping */
    private void mapSorting()
    {
        mapSorting.put("id","idUser");
        mapSorting.put("nama","userName");
        mapSorting.put("nip","nip");
    }

    /** Api untuk all jobs */
    @GetMapping("/v1/jobs/all")
    public ResponseEntity<Object> findAll(HttpServletRequest request) {
        return jobsService.findAll(request);
    }

    /** Api untuk jobs detail */
    @GetMapping("/v1/jobs/detail/{id}")
    public ResponseEntity<Object> findAll(
            @PathVariable("id") String id,
            HttpServletRequest request) {
        return jobsService.findById(id, request);
    }
}
