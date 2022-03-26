package com.termii.visitorslog.service;

import com.termii.visitorslog.domain.Visits;
import com.termii.visitorslog.dto.VisitsDto;

/**
 * @author: Nathan
 */
public interface VisitsService {

    Visits logNewVisit(VisitsDto visitsDto);

}
