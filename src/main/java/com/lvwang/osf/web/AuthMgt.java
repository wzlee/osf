package com.lvwang.osf.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lvwang.osf.model.User;
import com.lvwang.osf.util.Property;

/**
 * Servlet Filter implementation class AuthMgt
 */
@WebFilter("/AuthMgt")
public class AuthMgt implements Filter {

	public static final String URL_COMMENT = "/comment/create";
	public static final String URL_POST = "/post/create";
	
    /**
     * Default constructor. 
     */
    public AuthMgt() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("destory");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		//System.out.println("filtering..............");
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse rpo = (HttpServletResponse)response;
		String servletPath = req.getServletPath();
		//System.out.println(servletPath);
		if(servletPath != null && servletPath.length() != 0) {
			if(servletPath.startsWith(URL_COMMENT) || servletPath.startsWith(URL_POST)) {
				HttpSession session = req.getSession();
				User user = (User)session.getAttribute("user");
				if(user == null) {
					String contextPath = req.getContextPath();
					session.setAttribute("lastvisit", contextPath+servletPath);
					rpo.setCharacterEncoding("UTF-8");  
				    rpo.setContentType("application/json; charset=utf-8"); 
				    PrintWriter writer = rpo.getWriter();
				    String json = "{\"status\":\""+Property.ERROR_ACCOUNT_NOTLOGIN+"\"}";
				    writer.write(json);
				    writer.close();
				    //System.out.println("user not login");
				} else {
					//System.out.println("go ahead");
					chain.doFilter(request, response);
				}
			} else {
				//System.out.println("go ahead");
				chain.doFilter(request, response);
			}
		}
		// pass the request along the filter chain
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}