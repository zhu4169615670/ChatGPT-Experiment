package com.huilian.interceptor;

import com.huilian.entity.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 资源拦截器
 */
public class ResourcesInterceptor extends HandlerInterceptorAdapter {
  
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        Object user = request.getSession().getAttribute("USER_SESSION");//获取当前会话对象
        //获取请求的路径
        String uri = request.getRequestURI();
        //如果用户是已登录状态，放行
        if (user != null) {
            
            return true;
        }

        //其他情况都直接跳转到登录页面
        request.setAttribute("msg", "您还没有登录，请先登录！");
        request.getRequestDispatcher("/toLogin").forward(request, response);
        return false;//拦截请求
    }
}