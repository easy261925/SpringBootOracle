package com.easycc.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.easycc.project.common.utils.ResponseResult;
import com.easycc.project.entity.SysUser;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author cc
 * @since 2021-01-04
 */
public interface AuthService extends IService<SysUser> {

    ResponseResult login(HttpServletRequest request, SysUser sysUser);

    SysUser getUserInfo(HttpServletRequest request);

    ResponseResult logout(HttpServletRequest request);
}
