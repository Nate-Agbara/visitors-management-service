package com.termii.visitorslog.controller;

import com.termii.visitorslog.constants.AppConstants;
import com.termii.visitorslog.domain.Staff;
import com.termii.visitorslog.dto.StaffDto;
import com.termii.visitorslog.exception.ApiRequestException;
import com.termii.visitorslog.service.StaffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Nathan
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.STAFF_URL)
public class StaffController {

    private final StaffService staffService;

    @PostMapping
    public ResponseEntity<?> addStaff(@RequestBody StaffDto staffDto){
        staffService.save(staffDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Staff getStaffById(@PathVariable long id ){
        return staffService.findStaffById(id).orElseThrow(() ->
                new ApiRequestException(AppConstants.NO_SUCH_STAFF));
    }

    @GetMapping
    public List<Staff> getAllStaff(){
        return staffService.findAllStaff();
    }

}
