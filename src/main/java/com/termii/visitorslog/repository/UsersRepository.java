package com.termii.visitorslog.repository;

import com.termii.visitorslog.domain.Users;
import com.termii.visitorslog.dto.UsersDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query("select u from Users u where upper(u.username) = upper(?1)")
    Optional<Users> findByUsername(String username);

    @Query("select u from Users u where upper(u.username) = upper(?1) and u.password = ?2")
    Optional<Users> findByUsernameAndPassword(@NonNull String username, @Nullable String password);
}