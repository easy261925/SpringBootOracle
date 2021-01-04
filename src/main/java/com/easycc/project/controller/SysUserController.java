package com.easycc.project.controller;


import com.easycc.project.common.utils.ResponseResult;
import com.easycc.project.entity.SysUser;
import com.easycc.project.service.SysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cc
 * @since 2021-01-04
 */
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @GetMapping("/user")
    public ResponseResult getAllUser() {
        List<SysUser> users = sysUserService.list();
        return ResponseResult.ok().data("list", users);
    }
}

