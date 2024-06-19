package com.base.dans.util;
/*
IntelliJ IDEA 2023.3.6 (Ultimate Edition)
Build #IU-233.15026.9, built on March 21, 2024
@Author bimaprakoso a.k.a Bima Prakoso
Java Developer
Created on 19/06/24 22.11
@Last Modified 19/06/24 22.11
Version 1.0
 */

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public class TransformToDTO {
    public Map<String,Object> transformObject(Map<String,Object> mapz, List ls)//<PENAMBAHAN 21-12-2023>
    {
        Integer totalItems = ls.size();
        mapz.put("content",ls);
        mapz.put("totalItems",totalItems);

        return mapz;
    }
}
