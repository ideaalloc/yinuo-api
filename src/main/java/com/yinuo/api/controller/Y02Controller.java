package com.yinuo.api.controller;

import com.yinuo.api.model.vo.BaseResponse;
import com.yinuo.api.model.vo.Y02Indexes;
import com.yinuo.api.model.vo.Y02Request;
import com.yinuo.api.model.vo.Y02Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-20
 */
@Api("/Y02")
@RestController
@RequestMapping(value = "/v1")
public class Y02Controller {
    static final Logger LOGGER = LoggerFactory.getLogger(Y02Controller.class);

    @ApiOperation(value = "获取Y02详细参数", nickname = "y02Details")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Y02Response.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponse.class),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(method = RequestMethod.POST, path = "/y02-details", produces = "application/json")
    public ResponseEntity<BaseResponse> y02Details(@RequestBody Y02Request y02Request) {
        List<Y02Indexes> result = new ArrayList<>();
        result.add(new Y02Indexes(1L, "13888888881", new Date(), "- -", "136", "1", Arrays.asList("125", "125", "125", "125", "125", "125", "125", "125", "125", "125", "125", "125"), "83", "27.0", "84", "13128909466", "- -"));
        result.add(new Y02Indexes(2L, "13888888882", new Date(), "- -", "136", "1", Arrays.asList("125", "125"), "83", "27.0", "84", "13128909466", "- -"));
        return new ResponseEntity<>(new Y02Response(0, "successfully", result), HttpStatus.OK);
    }
}
