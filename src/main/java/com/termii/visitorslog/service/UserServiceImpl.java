package com.termii.visitorslog.service;

import com.termii.visitorslog.domain.Users;
import com.termii.visitorslog.domain.Visitors;
import com.termii.visitorslog.dto.UsersDto;
import com.termii.visitorslog.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author: Nathan
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UsersRepository usersRepository;

    @Override
    public Optional<Users> login(String username, String password) {
        Optional<Users> result = usersRepository.findByUsernameAndPassword(username, password);
        return result.isPresent() ?
                result:
                Optional.empty();
    }
}
