package org.concursuri.filters;

import jakarta.inject.Inject;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.Principal;

import org.concursuri.ejb.UsersBean;

@WebFilter("/*")
public class AcceptedUserFilter implements Filter {
    @Inject
    UsersBean usersBean;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String contextPath = httpRequest.getContextPath();
        String uri = httpRequest.getRequestURI();
        String path = uri.substring(contextPath.length());

        if (path.startsWith("/Login") || path.startsWith("/j_security_check")) {
            chain.doFilter(request, response);
            return;
        }

        Principal principal = httpRequest.getUserPrincipal();
        if (principal != null) {
            String username = principal.getName();
            if (!usersBean.isUserAccepted(username)) {
                try {
                    httpRequest.logout();
                } catch (ServletException ignored) {
                    // Best effort logout; continue with redirect.
                }
                httpRequest.getSession().invalidate();
                httpResponse.sendRedirect(contextPath + "/Login?notAccepted=1");
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
