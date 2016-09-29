package it.mf.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

	private ServletContext context;

	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("AuthenticationFilter initialized");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String uri = req.getRequestURI();
//		req.getHeader(java.lang.String name)
		this.context.log("Requested Resource:" + uri + " ContextPath: " + req.getContextPath());

		HttpSession session = req.getSession(false);	//false ==> se non esiste non la crea
		
		if ((session != null && session.getAttribute("doceId") != null) || uri.endsWith("html") || uri.endsWith("login.jsp") || uri.endsWith("LoginServlet")) {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
		else {
			this.context.log("Unauthorized access request");
			res.sendRedirect(req.getContextPath() + "/login.jsp");
		}
		
	}

	public void destroy() {
		// close any resources here
	}

}
