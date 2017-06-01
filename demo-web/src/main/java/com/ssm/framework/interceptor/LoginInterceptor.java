package com.ssm.framework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        logger.debug("=====================preHandle==============================");
        System.out.println("=====================preHandle==============================");
        System.out.println("=========== url:" + request.getRequestURI());
        System.out.println("=========== paramerter:" + request.getParameter("rand"));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
        logger.debug("=====================postHandle==============================");
        System.out.println("===================================postHandle=====================================");
//        System.out.println(request.getCharacterEncoding());
//        System.out.println(modelAndView.getModel());
        
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception {
        logger.debug("=====================afterCompletion==============================");
        System.out.println("========================afterCompletion===================================");
    }
}
