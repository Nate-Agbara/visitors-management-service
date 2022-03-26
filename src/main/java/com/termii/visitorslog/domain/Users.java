package com.termii.visitorslog.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.termii.visitorslog.constants.AppConstants;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonIgnore
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    @NotEmpty(message = AppConstants.USERNAME_CANNOT_BE_EMPTY)
    private String username;

    @JsonIgnore
    @NotEmpty(message = AppConstants.PASSWORD_CANNOT_BE_EMPTY)
    @Column(name = "password", nullable = false)
    private String password;

}
