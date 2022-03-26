package com.termii.visitorslog.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author: Nathan
 */
@Data
@Builder
public class StaffDto implements Serializable {

    private final String staffName;
    private final String phoneNumber;
    private final String emailAddress;
    private final String address;
}
