package org.fit.pis.back;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public final class AuthenticationFilter implements Filter 
{
	@Inject
	AuthenticationBean auth;
    @SuppressWarnings("unused")
	private FilterConfig filterConfig = null;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
    {
        if (auth != null && auth.isAuthorized())
        {
            chain.doFilter(request, response);
        }
        else
        {
        	//((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/faces/login.xhtml");
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>Bad login</title></head><body>");
            out.println("<h1>Access denied</h1>");
            out.println("Access denied. <a href=\"../login.xhtml\">Try again</a>.");
            out.println("</body></html>");
        }
    }

    public void destroy() 
    {
    }

    public void init(FilterConfig filterConfig) 
    {
        this.filterConfig = filterConfig;
    }
}
