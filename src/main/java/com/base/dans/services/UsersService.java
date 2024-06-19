package com.base.dans.services;
/*
IntelliJ IDEA 2023.3.6 (Ultimate Edition)
Build #IU-233.15026.9, built on March 21, 2024
@Author bimaprakoso a.k.a Bima Prakoso
Java Developer
Created on 13/05/24 19.16
@Last Modified 13/05/24 19.16
Version 1.0
 */

import com.base.dans.core.IService;
import com.base.dans.core.security.JwtUtility;
import com.base.dans.handler.ResponseHandler;
import com.base.dans.models.Users;
import com.base.dans.repo.UsersRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

/**
 * Service untuk users
 * Kode Service : 01
 */
@Service
@Transactional
public class UsersService implements UserDetailsService {
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

    @Autowired
    private JdbcTemplate hrisJdbcTemplate;

    /**
     * Method untuk login
     */
    public ResponseEntity<Object> doLogin(Users users, HttpServletRequest request) { // RANGE 001-005
        Optional<Users> opUserResult = usersRepo.findByUsernameAndPassword(users.getUsername(), users.getPassword());//DATANYA PASTI HANYA 1
        Users nextUser = null;
        try
        {
            if(!opUserResult.isEmpty())
            {
                nextUser = opUserResult.get();
                /**
                 * Ketiga Informasi ini kalau butuh dibuatan saja di Model User nya
                 * kalau digunakan pastikan flow nya di check lagi !!
                 */
//                nextUser.setLastLoginDate(new Date());
//                nextUser.setTokenCounter(0);//SETIAP KALI LOGIN BERHASIL , BERAPA KALIPUN UJI COBA REQUEST TOKEN YANG SEBELUMNYA GAGAL AKAN SECARA OTOMATIS DIRESET MENJADI 0
//                nextUser.setPasswordCounter(0);//SETIAP KALI LOGIN BERHASIL , BERAPA KALIPUN UJI COBA YANG SEBELUMNYA GAGAL AKAN SECARA OTOMATIS DIRESET MENJADI 0
            }
            else
            {
                return new ResponseHandler().generateResponse("User Tidak Terdaftar",
                        HttpStatus.NOT_ACCEPTABLE,null,"FV01001",request);
            }
        }

        catch (Exception e)
        {
            return new ResponseHandler().generateResponse("Error",
                    HttpStatus.INTERNAL_SERVER_ERROR,null,"FE01001",request);
        }

        String token = authManager(nextUser,request);
        if (token.equals("FAILED"))
        {
            return new ResponseHandler().generateResponse("Login Gagal",
                    HttpStatus.NOT_ACCEPTABLE,null,"FV01002",request);
        }

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("token", token);
        return new ResponseHandler().generateResponse("Login Berhasil",
                HttpStatus.CREATED,map,null,request);
    }

    /** Methode untuk get data by username */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Users> opUser = usersRepo.findByUsername(s);
        if(opUser.isEmpty())
        {
            return null;
        }
        Users userNext = opUser.get();
         /*
            PARAMETER KE 3 TIDAK MENGGUNAKAN ROLE DARI SPRINGSECURITY CORE
            ROLE MODEL AKAN DITAMBAHKAN DI METHOD authManager DAN DIJADIKAN INFORMASI DI DALAM JWT
         */
        return new org.springframework.security.core.userdetails.
                User(userNext.getUsername(),userNext.getPassword(),new ArrayList<>());
    }


    /** Methode untuk auth manager */
    public String authManager(Users users, HttpServletRequest request)//RANGE 006-010
    {
        try {
            /* Untuk memasukkan user ke dalam */
            sBuild.setLength(0);
            UserDetails userDetails = loadUserByUsername(users.getUsername());
            if(userDetails==null)
            {
                return "FAILED";
            }

            /* Isi apapun yang perlu diisi ke dalam object mapz !! */
            mapz.put("ml",users.getUsername());//5-6-10
            mapz.put("wps",users.getPassword());//5-6-10
//        List<Menu> lMenu = user.getAkses().getListMenuAkses();
            String strAppendMenu = "";
            sBuild.setLength(0);
            String token = jwtUtility.generateToken(userDetails,mapz);
            token = token;

            return token;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
