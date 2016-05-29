package com.yinuo.api.controller;

import com.google.common.cache.CacheLoader;
import com.yinuo.api.cache.VCodeCache;
import com.yinuo.api.model.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-27
 */
@Api("/Sms")
@RestController
@RequestMapping(value = "/v1/sms")
public class SmsController {
    static final Logger LOGGER = LoggerFactory.getLogger(SmsController.class);
    static final VCodeCache vcodeCache = VCodeCache.getInstance();

    @ApiOperation(value = "发送短信验证码", nickname = "sendVerificationCode")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = CommonResponse.class),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = CommonResponse.class),
            @ApiResponse(code = 409, message = "conflicts", response = CommonResponse.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = CommonResponse.class),
            @ApiResponse(code = 424, message = "FAILED_DEPENDENCY", response = CommonResponse.class)})
    @RequestMapping(method = RequestMethod.POST, path = "/vcode/send", produces = "application/json")
    public ResponseEntity<BaseResponse> sendVerificationCode(@RequestBody SmsRequest smsRequest) {
        final SmsResponse sms;
        try {
            sms = vcodeCache.getSms(smsRequest.getMobile());
            LOGGER.info("received sms: {}", sms);
        } catch (ExecutionException e) {
            LOGGER.error("get sms vcode error");
            return new ResponseEntity<>(new CommonResponse(-2, "Get sms vcode error"), HttpStatus.FAILED_DEPENDENCY);
        } catch (CacheLoader.InvalidCacheLoadException e) {
            LOGGER.error("cache null: " + e.getCause());
            return new ResponseEntity<>(new CommonResponse(-4, "Cache null"), HttpStatus.CONFLICT);
        }
        if (sms.getErrorCode() == 0) {
            return new ResponseEntity<>(new CommonResponse(1, "Sent code successfully"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new CommonResponse(-5, sms.getReason()), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "验证短信验证码", nickname = "verifyCode")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = CommonResponse.class),
            @ApiResponse(code = 400, message = "BAD_REQUEST", response = CommonResponse.class)})
    @RequestMapping(method = RequestMethod.POST, path = "/vcode/verify", produces = "application/json")
    public ResponseEntity<BaseResponse> verifyCode(@RequestBody VCodeRequest vcodeRequest) {
        if (StringUtils.isBlank(vcodeRequest.getVcode()) || StringUtils.isBlank(vcodeRequest.getMobile())) {
            return new ResponseEntity<>(new CommonResponse(-2, "Invalid mobile or vcode"), HttpStatus.BAD_REQUEST);
        }
        final String code = vcodeCache.getCode(vcodeRequest.getMobile());
        if (vcodeRequest.getVcode().equalsIgnoreCase(code)) {
            return new ResponseEntity<>(new CommonResponse(1, "vcode verified Successfully"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new CommonResponse(0, "vcode verified failed"), HttpStatus.OK);
        }
    }
}
