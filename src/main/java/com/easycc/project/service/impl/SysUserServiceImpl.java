package com.easycc.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.easycc.project.common.Exception.CustomException;
import com.easycc.project.common.utils.MD5;
import com.easycc.project.common.utils.RedisUtil;
import com.easycc.project.common.utils.ResponseResult;
import com.easycc.project.common.utils.ResultCode;
import com.easycc.project.config.JwtConfig;
import com.easycc.project.entity.SysUser;
import com.easycc.project.mapper.SysUserMapper;
import com.easycc.project.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cc
 * @since 2021-01-04
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

}
