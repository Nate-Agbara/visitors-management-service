package com.termii.visitorslog.service;

import com.termii.visitorslog.domain.Staff;
import com.termii.visitorslog.dto.StaffDto;

import java.util.List;
import java.util.Optional;

/**
 * @author: Nathan
 */
public interface StaffService {

    void save(StaffDto staffDto);

    Optional<Staff> findStaffById(long id);

    List<Staff> findAllStaff();
}
