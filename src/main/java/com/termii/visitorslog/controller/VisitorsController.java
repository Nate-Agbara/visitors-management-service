package com.termii.visitorslog.controller;

import com.termii.visitorslog.constants.AppConstants;
import com.termii.visitorslog.domain.Visitors;
import com.termii.visitorslog.dto.VisitorsDto;
import com.termii.visitorslog.exception.ApiRequestException;
import com.termii.visitorslog.service.VisitorsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

/**
 * @author: Nathan
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.VISITORS_URL)
public class VisitorsController {

    private final VisitorsService visitorsService;

    @PostMapping
    public ResponseEntity<?> addStaff(@RequestBody VisitorsDto visitorsDto){
        visitorsService.addVisitor(visitorsDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Visitors getStaffById(@PathVariable long id ){
        return visitorsService.findVisitorById(id).orElseThrow(() ->
                new ApiRequestException(AppConstants.NO_SUCH_VISITOR));
    }

    @GetMapping
    public List<Visitors> getAllStaff(){
        return visitorsService.findAllVisitor();
    }
}
