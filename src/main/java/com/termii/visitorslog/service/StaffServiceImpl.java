package com.termii.visitorslog.service;

import com.termii.visitorslog.domain.Staff;
import com.termii.visitorslog.dto.StaffDto;
import com.termii.visitorslog.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author: Nathan
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService{

    private final StaffRepository staffRepository;

    @Override
    public void save(StaffDto staffDto) {
        log.info("Inside create add staff: {}", staffDto.toString());
        staffRepository.save(Staff.builder()
                .staffName(staffDto.getStaffName())
                .phoneNumber(staffDto.getPhoneNumber())
                .emailAddress(staffDto.getEmailAddress())
                .address(staffDto.getAddress())
                .build());
    }

    @Override
    @Cacheable("staff")
    public Optional<Staff> findStaffById(long id) {
        log.info("Inside findStaffById");
        Optional<Staff> result = staffRepository.findById(id);
        return result.isPresent() ?
                result:
                Optional.empty();
    }

    @Override
    public List<Staff> findAllStaff() {
        log.info("Inside findAllStaff");
        return staffRepository.findAll();
    }
}
