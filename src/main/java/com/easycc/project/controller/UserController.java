package com.easycc.project.controller;


import com.easycc.project.common.utils.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
public class UserController {

    @GetMapping("/user")
    public ResponseResult getAllUser() {
        return ResponseResult.ok();
    }
}

