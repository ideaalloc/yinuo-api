package com.yinuo.api.controller;

import com.yinuo.api.model.annotation.CurrentlyLoggedUser;
import com.yinuo.api.model.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Gary Yu {@literal <garyhbut@gmail.com>}
 * @version 1.0
 * @since 2016-05-26
 */
@Api("/Patient")
@RestController
@RequestMapping(value = "/v1")
@PreAuthorize("hasAnyAuthority('ROLE_PATIENT','ROLE_DOCTOR')")
public class PatientController {
    static final Logger LOGGER = LoggerFactory.getLogger(PatientController.class);

    @ApiOperation(value = "查询病人", nickname = "queryPatient")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = PatientResponse.class)})
    @RequestMapping(method = RequestMethod.POST, path = "/patients/query", produces = "application/json")
    public ResponseEntity<BaseResponse> queryPatient(@RequestBody PatientRequest patientRequest, @CurrentlyLoggedUser Principal principal) {
        LOGGER.info("Domain user {} accessed", principal.getUsername());
        return new ResponseEntity<>(
                new PatientResponse(0, "successfully", new PatientVo("1", "yuhuyi", "male", "27", "100", "18717113818", "13476816496", "nihao")),
                HttpStatus.OK);
    }
}
