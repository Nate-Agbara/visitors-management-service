package com.termii.visitorslog.dto;

import com.termii.visitorslog.domain.Staff;
import com.termii.visitorslog.domain.Visitors;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: Nathan
 */
@Data
@Builder
public class VisitsDto implements Serializable {
    private final long visitorsId;
    private final long staffIdId;
    private final LocalDateTime dateOfVisit;
    private final String reasonForVisit;
}
