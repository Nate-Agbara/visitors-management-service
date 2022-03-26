package com.termii.visitorslog.controller;

import com.termii.visitorslog.constants.AppConstants;
import com.termii.visitorslog.domain.Users;
import com.termii.visitorslog.dto.UsersDto;
import com.termii.visitorslog.exception.ApiRequestException;
import com.termii.visitorslog.repository.UsersRepository;
import com.termii.visitorslog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

/**
 * @author: Nathan
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.LOGIN_URL)
public class UserController {

    private final UserService userService;

    @PostMapping
    public Users login(@RequestBody UsersDto usersDto){
        return userService.login(usersDto.getUsername(), usersDto.getPassword()).orElseThrow(() ->
                new ApiRequestException(AppConstants.USERNAME_OR_PASSWORD_INCORRECT));
    }
}
