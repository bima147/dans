package com.base.dans.configuration;
/*
IntelliJ IDEA 2023.3.6 (Ultimate Edition)
Build #IU-233.15026.9, built on March 21, 2024
@Author bimaprakoso a.k.a Bima Prakoso
Java Developer
Created on 19/06/24 21.41
@Last Modified 19/06/24 21.41
Version 1.0
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@PropertySource("classpath:other.properties")
public class OtherConfig {
    private static List<String> kataTerlarang;//penambahan 09-03-2024 untuk XSS saja

    public static List<String> getKataTerlarang() {
        return kataTerlarang;
    }
    @Value("#{'${kata.terlarang}'.split(',')}")
    private void setKataTerlarang(List<String> kataTerlarang) {
        OtherConfig.kataTerlarang = kataTerlarang;
    }
}
