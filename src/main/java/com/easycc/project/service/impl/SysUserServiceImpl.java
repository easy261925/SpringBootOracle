package com.easycc.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easycc.project.common.Exception.CustomException;
import com.easycc.project.common.utils.RedisUtil;
import com.easycc.project.common.utils.ResponseResult;
import com.easycc.project.common.utils.ResultCode;
import com.easycc.project.config.JwtConfig;
import com.easycc.project.entity.SysUser;
import com.easycc.project.mapper.SysUserMapper;
import com.easycc.project.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.jsonwebtoken.Claims;
import io.swagger.models.auth.In;
import org.apache.http.HttpRequest;
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

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private JwtConfig jwtConfig;


    @Override
    public ResponseResult login(HttpServletRequest request, SysUser sysUser) {
        if (StringUtils.isEmpty(sysUser.getUsername()) || StringUtils.isEmpty(sysUser.getPassword())) {
            throw new CustomException(ResultCode.FAILED, "用户名或密码不能为空");
        }

        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>();
        sysUserQueryWrapper.eq("username", sysUser.getUsername())
                .eq("password", sysUser.getPassword())
                .eq("ban", 0);
        SysUser currentUser = baseMapper.selectOne(sysUserQueryWrapper);
        if (currentUser != null) {
            String redisKey = currentUser.getId().toString() + "_" + currentUser.getUsername();
            String redisTmpValue = redisUtil.get(redisKey, String.class);
            if (!StringUtils.isEmpty(redisTmpValue)) {
                redisUtil.del(redisTmpValue);
                redisUtil.del(redisKey);
            }
            String token = jwtConfig.getToken(redisKey);
            redisUtil.set(redisKey, token);
            redisUtil.set(token, redisKey);
            currentUser.setLoginTime(new Date());
            currentUser.setLoginIp(request.getRemoteAddr());
            baseMapper.updateById(currentUser);
            return ResponseResult.ok().data("data", currentUser).data("token", token);
        } else {
            throw new CustomException(ResultCode.FAILED, "用户名或密码错误");
        }
    }

    @Override
    public SysUser getUserInfo(HttpServletRequest request) {
        String token = request.getHeader(jwtConfig.getHeader());
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        Claims tokenClaim = jwtConfig.getTokenClaim(token);
        String subject = tokenClaim.getSubject();
        String[] tokenArray = subject.split("_");
        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>();
        sysUserQueryWrapper.eq("id", Integer.parseInt(tokenArray[0]))
                .eq("username", tokenArray[1]);
        return baseMapper.selectOne(sysUserQueryWrapper);
    }

    @Override
    public ResponseResult logout(HttpServletRequest request) {
        String token = request.getHeader(jwtConfig.getHeader());
        String redisTmpValue = redisUtil.get(token, String.class);
        redisUtil.del(token);
        redisUtil.del(redisTmpValue);
        return ResponseResult.ok();
    }
}
