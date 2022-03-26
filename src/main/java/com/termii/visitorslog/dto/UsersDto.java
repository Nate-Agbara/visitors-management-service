package com.termii.visitorslog.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author: Nathan
 */
@Data
@Builder
public class UsersDto implements Serializable {
    private final String username;
    private final String password;
}
