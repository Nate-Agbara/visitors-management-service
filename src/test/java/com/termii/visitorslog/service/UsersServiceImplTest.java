package com.termii.visitorslog.service;

import com.termii.visitorslog.domain.Users;
import com.termii.visitorslog.exception.ApiRequestException;
import com.termii.visitorslog.repository.UsersRepository;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author: Nathan
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class UsersServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UsersRepository usersRepository;

    @DisplayName("Returning saved users from service layer")
    @Test
    public void getUserByUsernameAndPassword_forSavedUser_isReturned(){
        //given
        Users users = usersRepository.save(Users.builder()
                .username("username")
                .password("pass")
                .build());

        //when
        Optional<Users> retrievedUser = userService.login(users.getUsername(), users.getPassword());

        //then
        assertEquals("username", retrievedUser.get().getUsername());
        assertNotNull(retrievedUser.get().getId());
    }

    @Test
    void userLogin_whenMissingUser_notFoundExceptionThrown()
    {
        //given
        String fakeUsername = "fakeuser";
        String fakePassword = "fakepass";
        //when
        Optional<Users> user = userService.login(fakeUsername, fakePassword);

        //then
        assertEquals(Optional.empty(), user);
    }
}
