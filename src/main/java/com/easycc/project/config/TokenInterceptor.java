package com.easycc.project.config;

import com.easycc.project.common.Exception.CustomException;
import com.easycc.project.common.utils.RedisUtil;
import com.easycc.project.common.utils.ResultCode;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class TokenInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private JwtConfig jwtConfig;
    @Resource
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // 地址过滤
        String uri = request.getRequestURI();

        if (uri.contains("/static")) {
            return true;
        }
        // 登录
        if (uri.contains("/login")) {
            return true;
        }
        if (uri.contains("swagger")) {
            return true;
        }
        if (StringUtils.isEmpty(uri) || "/".equals(uri)) {
            return true;
        }
        if (uri.contains("/error")) {
            return true;
        }
        if (uri.contains("/csrf") || uri.contains("/robots")) {
            return true;
        }
        if (uri.contains("/favicon.ico")) {
            return true;
        }
        // Token 验证
        String token = request.getHeader(jwtConfig.getHeader());
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter(jwtConfig.getHeader());
        }
        if (StringUtils.isEmpty(token)) {
            System.out.println(uri);
            throw new CustomException(ResultCode.INVALID, "token 不能为空");
        }
        if (!redisUtil.hasKey(token)) {
            Claims claims = jwtConfig.getTokenClaim(token);
            if (claims != null) {
                String info = claims.getSubject();
                if (!StringUtils.isBlank(info) && info.indexOf("_") > 0) {
                    String[] array = info.split("_");
                    String tmp = array[0] + "_" + array[1];
                    redisUtil.del(tmp);
                }
            }
            throw new CustomException(ResultCode.INVALID, "token 失效，请重新登录");
        }
        Claims claims = jwtConfig.getTokenClaim(token);
        if (claims == null || jwtConfig.isTokenExpired(claims.getExpiration())) {
            String tmp = redisUtil.get(token, String.class);
            redisUtil.del(tmp);
            redisUtil.del(token);
            throw new CustomException(ResultCode.INVALID, "token 失效，请重新登录");
        }
        //设置 identityId 用户身份ID
        request.setAttribute("identityId", claims.getSubject());
        return true;
    }
}
