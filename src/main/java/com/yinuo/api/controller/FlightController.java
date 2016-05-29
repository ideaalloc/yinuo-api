package com.yinuo.api.controller;

import com.yinuo.api.model.vo.BaseResponse;
import com.yinuo.api.model.vo.FlightResponse;
import com.yinuo.api.service.FlightService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-13
 */
@Api("/航班")
@RestController
@RequestMapping(value = "/v1")
public class FlightController {
    static final Logger LOGGER = LoggerFactory.getLogger(FlightController.class);

    @Autowired
    FlightService flightService;

    @ApiOperation(value = "获取航班", nickname = "getResult")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "航班ID", required = true, dataType = "long", paramType = "path")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = FlightResponse.class)})
//    @PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping(method = RequestMethod.GET, path = "/flights/{id}", produces = "application/json")
    public ResponseEntity<BaseResponse> getFlight(@PathVariable Long id) {
        return new ResponseEntity<>(new FlightResponse(0, "successfully", flightService.findById(id)), HttpStatus.OK);
    }
}
