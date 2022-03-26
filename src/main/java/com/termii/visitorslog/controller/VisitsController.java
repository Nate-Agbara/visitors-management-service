package com.termii.visitorslog.controller;

import com.termii.visitorslog.constants.AppConstants;
import com.termii.visitorslog.dto.VisitsDto;
import com.termii.visitorslog.service.VisitsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Nathan
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.VISITS_URL)
public class VisitsController {

    private final VisitsService visitsService;

    @PostMapping
    public ResponseEntity<?> createVisit(@RequestBody VisitsDto visitsDto){
        visitsService.logNewVisit(visitsDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
