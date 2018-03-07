package eon.web.interceptor;

import eon.util.PermissionUtil;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class PermissionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            String expression = handlerMethod.getBeanType().getName() + ":" + method.getName();
            //检查是否有权限访问
            if (PermissionUtil.checkPermission(expression)) {
                //有权限
                return true;
            } else {
                //没有权限
                //是访问页面还是ajax
                ResponseBody rb = method.getAnnotation(ResponseBody.class);
                if (rb == null) {
                    //页面请求
                    response.sendRedirect("/noPermission.jsp");
                } else {
                    //ajax请求
                    response.sendRedirect("/noPermission.json");
                }
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
