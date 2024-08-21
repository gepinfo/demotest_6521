package com.geppetto.demotest.config;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.lang3.StringUtils;

public class ServletContextFacadeRequestWrapper extends HttpServletRequestWrapper {

    private String contextPath;
    private String servletPath;

    public ServletContextFacadeRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getContextPath() {
        if (StringUtils.isNotBlank(contextPath)) {
            return contextPath;
        }
        return super.getContextPath();
    }

    @Override
    public String getServletPath() {
        if (StringUtils.isNotBlank(servletPath)) {
            return servletPath;
        }
        return super.getServletPath();
    }

    @Override
    public String getRequestURI() {
        String requestURI = super.getRequestURI();
        if (requestURI.equals(contextPath)) {
            return requestURI + "/";
        }
        return requestURI;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public void setServletPath(String servletPath) {
        this.servletPath = servletPath;
    }

}