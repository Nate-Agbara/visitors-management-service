package com.termii.visitorslog.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: Nathan
 */
@Data
@Builder
public class VisitorsDto implements Serializable {
    private final String visitorName;
    private final String phoneNumber;
    private final String emailAddress;
    private final String address;
}
