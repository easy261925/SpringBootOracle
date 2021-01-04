package com.easycc.project.controller;

import com.easycc.project.common.Exception.CustomException;
import com.easycc.project.common.utils.ResponseResult;
import com.easycc.project.common.utils.ResultCode;
import com.easycc.project.entity.SysUser;
import com.easycc.project.service.SysUserService;
import org.apache.catalina.User;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cc
 * @date 2021-01-04-下午2:46
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/login")
    public ResponseResult login(HttpServletRequest request, @RequestBody SysUser sysUser) {
        return sysUserService.login(request, sysUser);
    }

    @PostMapping("/logout")
    public ResponseResult logout(HttpServletRequest request) {
        return sysUserService.logout(request);
    }

    @GetMapping("/getUserInfo")
    public ResponseResult getUserInfo(HttpServletRequest request) {
        SysUser userInfo = sysUserService.getUserInfo(request);
        if (StringUtils.isEmpty(userInfo)) {
            throw new CustomException(ResultCode.INVALID, "令牌失效，请重新登录");
        } else {
            return ResponseResult.ok().data("userInfo", userInfo);
        }
    }
}
