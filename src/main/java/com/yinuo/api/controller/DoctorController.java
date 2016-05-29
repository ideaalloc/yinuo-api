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
@Api("/Doctor")
@RestController
@RequestMapping(value = "/v1")
@PreAuthorize("hasAnyAuthority('ROLE_DOCTOR')")
public class DoctorController {
    static final Logger LOGGER = LoggerFactory.getLogger(DoctorController.class);

    @ApiOperation(value = "查询医生", nickname = "queryDoctor")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = DoctorResponse.class)})
    @RequestMapping(method = RequestMethod.POST, path = "/doctors/query", produces = "application/json")
    public ResponseEntity<BaseResponse> queryDoctor(@RequestBody DoctorRequest doctorRequest, @CurrentlyLoggedUser Principal principal) {
        LOGGER.info("Doctor {} accessed", principal.getUsername());
        return new ResponseEntity<>(new DoctorResponse(0, "successfully", new DoctorVo("27", "Bill", "宜诺", "技术", "http://yinuo-storage.oss-cn-shenzhen.aliyuncs.com/avatar/23/68/6142252486859162368/high_12a04aa2-87dd-489f-afbd-787c3e87dec9.jpg", "13888888888")), HttpStatus.OK);
    }
}
