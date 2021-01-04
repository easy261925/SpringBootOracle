package com.easycc.project.controller;

import com.easycc.project.common.Exception.CustomException;
import com.easycc.project.common.utils.ResponseResult;
import com.easycc.project.common.utils.ResultCode;
import com.easycc.project.entity.SysUser;
import com.easycc.project.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author cc
 * @date 2021-01-04-下午2:46
 */
@RestController
@RequestMapping("/auth")
@Api(tags = "登录、退出、获取用户信息")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @ApiOperation("登录")
    public ResponseResult login(HttpServletRequest request, @RequestBody SysUser sysUser) {
        return authService.login(request, sysUser);
    }

    @PostMapping("/logout")
    @ApiOperation("退出")
    public ResponseResult logout(HttpServletRequest request) {
        return authService.logout(request);
    }

    @GetMapping("/getUserInfo")
    @ApiOperation("获取用户信息")
    public ResponseResult getUserInfo(HttpServletRequest request) {
        SysUser userInfo = authService.getUserInfo(request);
        if (StringUtils.isEmpty(userInfo)) {
            throw new CustomException(ResultCode.INVALID, "令牌失效，请重新登录");
        } else {
            return ResponseResult.ok().data("userInfo", userInfo);
        }
    }
}
