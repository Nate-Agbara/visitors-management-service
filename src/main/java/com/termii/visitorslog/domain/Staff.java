package com.termii.visitorslog.domain;

import com.termii.visitorslog.constants.AppConstants;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@Entity
@Table(name = "staff")
@NoArgsConstructor
@AllArgsConstructor
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "staff_name", nullable = false)
    @NotEmpty(message = AppConstants.STAFF_NAME_IS_MANDATORY)
    private String staffName;

    @Column(name = "phone_number")
    @NotEmpty(message = AppConstants.PHONE_NUMBER_IS_MANDATORY)
    @Size(max = 11, message = AppConstants.PHONE_NUMBER_TOO_LONG)
    private String phoneNumber;

    @Column(name = "email_address", nullable = false, unique = true)
    @NotEmpty(message = AppConstants.EMAIL_ADDRESS_IS_MANDATORY)
    @Email(message = AppConstants.INVALID_EMAIL)
    private String emailAddress;

    @Column(name = "address")
    @NotEmpty(message = AppConstants.ADDRESS_IS_MANDATORY)
    @Size(max = 255, message = AppConstants.ADDRESS_TOO_LONG)
    private String address;

}