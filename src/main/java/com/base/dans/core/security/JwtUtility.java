package com.base.dans.core.security;
/*
IntelliJ IDEA 2023.3.6 (Ultimate Edition)
Build #IU-233.15026.9, built on March 21, 2024
@Author bimaprakoso a.k.a Bima Prakoso
Java Developer
Created on 19/06/24 21.41
@Last Modified 19/06/24 21.41
Version 1.0
 */

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtility implements Serializable {
    private static final long serialVersionUID = 234234523523L;
    public static final long JWT_TOKEN_VALIDITY = 1 * 60 * 60;//untuk 60 menit

    public static final long JWT_TOKEN_VALIDITY_REGISTER = 1 * 60 * 60;//untuk 60 menit
    @Value("${jwt.secret}")
    private String secretKey;
    private String strArrAuth [] = null;

    /**
     * KONFIGURASI UNTUK CUSTOMISASI DIMULAI DISINI
     */


    /**
     * Ini untuk JWT filtering saja tanpa memperdulikan otorisasi user nya
     */
    public Map<String,Object> getApiAuthorization(String token,
                                                  Map<String,Object> mapz){
        Claims claims = getAllClaimsFromToken(token);
        mapz.put("uid",claims.get("uid"));
        mapz.put("un",claims.getSubject());//untuk subject / username sudah ada di claims token JWT
        mapz.put("ml",claims.get("username"));
        mapz.put("ipn",claims.get("nip"));
        return mapz;
    }

    /**
     * Ini untuk JWT filter dan otorisasi based on Menu saja
     */
    public Map<String,Object> getApiAuthorization(String token,
                                                  Map<String,Object> mapz,
                                                  String modulCode){

        Boolean isAuth = false;
        Claims claims = getAllClaimsFromToken(token);
        if(claims.get("ln")!=null){
            strArrAuth = claims.get("ln").toString().split("-");
            int intArr = strArrAuth.length;
            for (int i=0;i<intArr;i++)
            {
                if(modulCode.equals(strArrAuth[i]))
                {
                    isAuth = true;
                    mapz.put("isValid",isAuth);
                    break;
                }
            }
        }
        mapz.put("uid",claims.get("uid"));
        mapz.put("un",claims.getSubject());//untuk subject / username sudah ada di claims token JWT
        mapz.put("ml",claims.get("username"));
        mapz.put("ipn",claims.get("nip"));

        return mapz;
    }

    /**
     * Ini untuk JWT filter dan otorisasi per API di dalam class
     */
    public Map<String,Object> getApiAuthorization(String token,String modul,
                                                  Integer intPrivilage,
                                                  Map<String,Object> mapz){
        mapz.put("isValid",false);//inisialisasi map untuk key isValid adalah false, akan true jika sudah melewati kondisi dibawah
        Claims claims = getAllClaimsFromToken(token);
        String strModul = claims.get(modul)==null?"":claims.get(modul).toString();
        if(strModul!=null && !strModul.equals(""))
        {
            String strArrPrivilage = strModul.split("-")[intPrivilage].toString();
            if(strArrPrivilage.equals("1"))
            {
                /*
                    Transfer data dari JWT ke Object Java Map
                 */
                mapz.put("userId",claims.get("uid"));
                mapz.put("userName",claims.getSubject());//untuk subject / username sudah ada di claims token JWT
                mapz.put("email",claims.get("mail"));
                mapz.put("noHp",claims.get("phone"));
                mapz.put("isValid",true);
            }
        }
        return mapz;
    }

    public String getUsernameFromApiAuthorization(String token) {
        String jwt = token.substring(7);
        jwt = getUsernameFromToken(jwt);
        return jwt;
    }

    /**
     * KONFIGURASI CUSTOMISASI BERAKHIR DISINI
     * KONFIGURASI UNTUK JWT DIMULAI DARI SINI
     */

//    username dari token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
    //parameter token habis waktu nya
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //kita dapat mengambil informasi dari token dengan menggunakan secret key
    //disini juga validasi dari expired token dan lihat signature  dilakukan
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token untuk user
    public String generateToken(UserDetails userDetails,Map<String,Object> claims) {
        claims = (claims==null)?new HashMap<String,Object>():claims;
        return doGenerateToken(claims, userDetails.getUsername());
    }

    public String generateRegisterToken(UserDetails userDetails,Map<String,Object> claims) {
        claims = (claims==null)?new HashMap<String,Object>():claims;
        return doGenerateRegisterToken(claims, userDetails.getUsername());
    }

    //proses yang dilakukan saat membuat token adalah :
    //mendefinisikan claim token seperti penerbit (Issuer) , waktu expired , subject dan ID
    //generate signature dengan menggunakan secret key dan algoritma HS512,
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secretKey).compact();
    }

    private String doGenerateRegisterToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY_REGISTER * 1000))
                .signWith(SignatureAlgorithm.HS512, secretKey).compact();
    }

    //validate token
    public Boolean validateToken(String token) {
        /*
            Sudah otomatis tervalidaasi jika expired date masih aktif
         */
        String username = getUsernameFromToken(token);
        return (username!=null && !isTokenExpired(token));
    }
    /**
     * KONFIGURASI UNTUK JWT BERAKHIR DI SINI
     */
}