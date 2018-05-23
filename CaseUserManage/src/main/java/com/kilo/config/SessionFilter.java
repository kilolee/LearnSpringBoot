package com.kilo.config;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kilo on 2018/5/22.
 */
public class SessionFilter implements Filter {
    protected Logger logger = LoggerFactory.getLogger(SessionFilter.class);
    //不登录也可以访问的资源
    private static Set<String> GreenUrlSet = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        GreenUrlSet.add("/toRegister");
        GreenUrlSet.add("/toLogin");
        GreenUrlSet.add("/login");
        GreenUrlSet.add("/loginOut");
        GreenUrlSet.add("/register");
        GreenUrlSet.add("/verified");
    }

    @Override
    public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) srequest;
        String uri = request.getRequestURI();
        sresponse.setCharacterEncoding("UTF-8");
        sresponse.setContentType("text/html;charset=UTF-8");

        //不处理指定的action，jsp
        if (GreenUrlSet.contains(uri) || uri.contains("/verified/")) {
            logger.debug("security filter,pass " + request.getRequestURI());
            chain.doFilter(srequest, sresponse);
            return;
        }

        if (uri.endsWith(".js") || uri.endsWith(".css")
                || uri.endsWith(".jpg") || uri.endsWith(".gif")
                || uri.endsWith(".png") || uri.endsWith(".ico")) {
            logger.debug("security filter,pass " + request.getRequestURI());
            chain.doFilter(srequest, sresponse);
            return;
        }

        String id = (String) request.getSession().getAttribute(WebConfiguration.LOGIN_KEY);
        if (StringUtils.isBlank(id)) {
            String html = "<script type=\"text/javascript\">window.location.href=\"/toLogin\"</script>";
            sresponse.getWriter().write(html);
        } else {
            chain.doFilter(srequest, sresponse);
        }
    }

    @Override
    public void destroy() {

    }
}
