package com.base.dans.repo;
/*
IntelliJ IDEA 2023.3.6 (Ultimate Edition)
Build #IU-233.15026.9, built on March 21, 2024
@Author bimaprakoso a.k.a Bima Prakoso
Java Developer
Created on 19/06/24 21.13
@Last Modified 19/06/24 21.13
Version 1.0
 */

import com.base.dans.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@EnableTransactionManagement
public interface UsersRepo extends JpaRepository<Users, Long> {
    Optional<Users> findByUsernameAndPassword(String username, String password);
    Optional<Users> findByUsername(String username);
}
