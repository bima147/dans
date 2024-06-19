package com.base.dans.controller;
/*
IntelliJ IDEA 2023.3.6 (Ultimate Edition)
Build #IU-233.15026.9, built on March 21, 2024
@Author bimaprakoso a.k.a Bima Prakoso
Java Developer
Created on 19/06/24 21.22
@Last Modified 19/06/24 21.22
Version 1.0
 */

import com.base.dans.dto.user.LoginDTO;
import com.base.dans.handler.ResponseHandler;
import com.base.dans.models.Users;
import com.base.dans.services.UsersService;
import com.base.dans.util.ConstantMessage;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller untuk API Users
 */
@RestController
@RequestMapping("/api")
public class UsersController {
    /** Membuat variabel model mapper */
    @Autowired
    private ModelMapper modelMapper;

    /** Insiasi service users */
    @Autowired
    UsersService usersService;

    /** Membuat variabel untuk melakukan sorting */
    private Map<String,String> mapSorting = new HashMap<String,String>();

    /** Membuat konstruktor dari controller barang */
    public UsersController() {
        mapSorting();
    }

    /** Melakukan mapping */
    private void mapSorting()
    {
        mapSorting.put("id","idUser");
        mapSorting.put("nama","userName");
        mapSorting.put("nip","nip");
    }

    /** Api untuk users login */
    @PostMapping("/v1/users/auth/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDTO loginDTO,
                                        HttpServletRequest request) {
        if(loginDTO == null) {
            return new ResponseHandler().generateResponse(ConstantMessage.ERROR_DATA_INVALID, HttpStatus.BAD_REQUEST,null,null,null);
        }
        Users users = modelMapper.map(loginDTO,new TypeToken<Users>(){}.getType());
        return usersService.doLogin(users,request);
    }
}
