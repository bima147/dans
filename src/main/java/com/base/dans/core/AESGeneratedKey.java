package com.base.dans.core;
/*
IntelliJ IDEA 2023.3.6 (Ultimate Edition)
Build #IU-233.15026.9, built on March 21, 2024
@Author bimaprakoso a.k.a Bima Prakoso
Java Developer
Created on 19/06/24 21.41
@Last Modified 19/06/24 21.41
Version 1.0
 */

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

public class AESGeneratedKey {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // Tambahkan Bouncy Castle Provider
        Security.addProvider(new BouncyCastleProvider());

        try {
            // Inisialisasi generator kunci AES dengan Bouncy Castle
            KeyGenerator keyGen = KeyGenerator.getInstance("AES", "BC");

            // Atur panjang kunci (misalnya: 128, 192, atau 256 bit)
            keyGen.init(256);

            // Generate kunci AES
            SecretKey aesKey = keyGen.generateKey();

            // Tampilkan kunci AES
            System.out.println("AES Key: " + bytesToHex(aesKey.getEncoded()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method untuk mengubah byte array menjadi string heksadesimal
    public static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
