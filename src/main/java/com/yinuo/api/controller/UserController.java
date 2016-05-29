package com.yinuo.api.controller;

import com.yinuo.api.model.type.Authority;
import com.yinuo.api.model.vo.BaseResponse;
import com.yinuo.api.model.vo.CommonResponse;
import com.yinuo.api.model.vo.UserRequest;
import com.yinuo.api.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
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
 * @since 2016-05-25
 */
@Api("/User")
@RestController
@RequestMapping(value = "/v1")
public class UserController {
    static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @ApiOperation(value = "注册用户", nickname = "registerUser")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success", response = CommonResponse.class),
            @ApiResponse(code = 226, message = "I'm used", response = CommonResponse.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = CommonResponse.class)})
    @RequestMapping(method = RequestMethod.POST, path = "/users/register", produces = "application/json")
    public ResponseEntity<BaseResponse> register(@RequestBody UserRequest userRequest) {
        if (StringUtils.isBlank(userRequest.getRole())) {
            return new ResponseEntity<>(new CommonResponse(-2, "Please provide the role"), HttpStatus.NOT_ACCEPTABLE);
        }
        try {
            Authority.valueOf(userRequest.getRole());
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new CommonResponse(-3, "Incorrect role"), HttpStatus.NOT_ACCEPTABLE);
        }
        if (userService.exists(userRequest.getUsername())) {
            return new ResponseEntity<>(new CommonResponse(0, "Username occupied"), HttpStatus.IM_USED);
        }
        userService.add(userRequest);
        return new ResponseEntity<>(new CommonResponse(1, "Register user successfully"), HttpStatus.CREATED);
    }
}
