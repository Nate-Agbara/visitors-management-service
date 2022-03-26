package com.termii.visitorslog.service;

import com.termii.visitorslog.domain.Users;

import java.util.Optional;

/**
 * @author: Nathan
 */
public interface UserService {

    Optional<Users> login(String username, String password);
}
