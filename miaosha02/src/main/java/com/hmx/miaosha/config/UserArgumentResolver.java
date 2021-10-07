package com.hmx.miaosha.config;

import com.hmx.miaosha.domain.MiaoshaUser;
import com.hmx.miaosha.service.MiaoshaUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: miaosha
 * @description:
 * @author: hmx
 * @create: 2021-10-07 21:21
 **/
@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private MiaoshaUserService miaoshaUserService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> clazz = parameter.getParameterType();
        return clazz == MiaoshaUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        String paramToken = request.getParameter(MiaoshaUserService.COOKIE_NAME_TOKEN);
        //寻找cookie中对应的token
        String cookieToken = getCookieValue(request, MiaoshaUserService.COOKIE_NAME_TOKEN);
        if (StringUtils.isBlank(cookieToken) && StringUtils.isBlank(paramToken)) {
            return null;
        }
        String token = StringUtils.isBlank(paramToken) ? cookieToken : paramToken;
        return miaoshaUserService.getByToken(response, token);
    }

    /**
     * 寻找cookie中存储的token
     * @param request
     * @param cookieName
     * @return
     */
    private String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
