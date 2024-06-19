package com.base.dans.services;
/*
IntelliJ IDEA 2023.3.6 (Ultimate Edition)
Build #IU-233.15026.9, built on March 21, 2024
@Author bimaprakoso a.k.a Bima Prakoso
Java Developer
Created on 19/06/24 21.37
@Last Modified 19/06/24 21.37
Version 1.0
 */

import com.base.dans.core.security.JwtUtility;
import com.base.dans.dto.job.JobsDTO;
import com.base.dans.handler.ResponseHandler;
import com.base.dans.models.Users;
import com.base.dans.repo.UsersRepo;
import com.base.dans.util.GetJobs;
import com.base.dans.util.TransformToDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.JsonNode;
import kong.unirest.json.JSONArray;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service untuk jobs
 * Kode Service : 02
 */
@Service
@Transactional
public class JobsService {
    private Map<String,Object> mapz = new HashMap<>();

    private StringBuilder sBuild = new StringBuilder();

    private String[] strExceptionArr = new String[2];

    /**
     * Object untuk menampung token yamg akan diverifikasi di class ModulAuthority nantinya
     */
    private Map<String, Object> mapToken = new HashMap<>();

    Map<String,Object> mapColum = new HashMap<>(); // Peta kolom

    Map<String,Object> mapResult = new HashMap<>(); // Hasil peta

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private UsersRepo usersRepo;

    // Inisiasi Model mapper
    @Autowired
    private ModelMapper modelMapper;

    // Inisiasi transfor DTO
    private TransformToDTO transformToDTO = new TransformToDTO();;
    private GetJobs getJobs = new GetJobs();;

    /**
     * Method untuk find all
     */
    public ResponseEntity<Object> findAll(HttpServletRequest request) { // RANGE 001-005
        try {
            // Mengambil data dari api
            JsonNode data = getJobs.all();
            if (data == null) {
                return new ResponseHandler().
                        generateResponse("Belum ada data tersedia!",
                                HttpStatus.OK,
                                null,//perubahan 21-12-2023
                                "FV02001",
                                request);
            }

            List<Object> dataObject = data.getArray().toList();
            String jsonString = null;
            List<JobsDTO> jobsDTO = new ArrayList<>();
            for (int i = 0; i < dataObject.size(); i++) {
                jsonString = dataObject.get(i).toString();
                ObjectMapper objectMapper = new ObjectMapper();
                JobsDTO jobs = modelMapper.map(objectMapper.readValue(jsonString, JobsDTO.class), new TypeToken<JobsDTO>() {}.getType());
                jobsDTO.add(jobs);
            }
            mapResult = transformToDTO.transformObject(mapResult,jobsDTO);

            return new ResponseHandler().
                    generateResponse("Berhasil mendapatkan data!",
                            HttpStatus.OK,
                            mapResult,//perubahan 21-12-2023
                            null,
                            request);
        } catch (Exception e) {
            return new ResponseHandler().
                    generateResponse("Terjadi Kesalahan pada server kami!",
                            HttpStatus.INTERNAL_SERVER_ERROR,
                            null,//perubahan 21-12-2023
                            "FE02002",
                            request);
        }
    }

    /**
     * Method untuk find all
     */
    public ResponseEntity<Object> findById(String id, HttpServletRequest request) { // RANGE 001-005
        try {
            // Mengambil data dari api
            JsonNode data = getJobs.detail(id);
            if (data == null) {
                return new ResponseHandler().
                        generateResponse("Belum ada data tersedia!",
                                HttpStatus.OK,
                                null,//perubahan 21-12-2023
                                "FV02001",
                                request);
            }

            List<Object> dataObject = data.getArray().toList();
            String jsonString = dataObject.get(0).toString();
            ObjectMapper objectMapper = new ObjectMapper();
            JobsDTO jobs = modelMapper.map(objectMapper.readValue(jsonString, JobsDTO.class), new TypeToken<JobsDTO>() {}.getType());

            return new ResponseHandler().
                    generateResponse("Berhasil mendapatkan data!",
                            HttpStatus.OK,
                            jobs,//perubahan 21-12-2023
                            null,
                            request);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseHandler().
                    generateResponse("Terjadi Kesalahan pada server kami!",
                            HttpStatus.INTERNAL_SERVER_ERROR,
                            null,//perubahan 21-12-2023
                            "FE02002",
                            request);
        }
    }
}
