package main.java.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;

import main.java.vo.userVO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class LoginInterceptor extends AbstractInterceptor {	
	private String loginAction = "";
	
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext context = invocation.getInvocationContext();
		Map<String, Object> session = context.getSession();

		userVO s_user = (userVO)session.get("s_user");
		
		if(s_user == null){
			HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
			String queryString = request.getQueryString();
			String currentURI = ServletActionContext.getRequest().getServletPath() + (queryString != null ?"?" + queryString : "");
			context.getSession().put("url", currentURI);
			context.getSession().put("query", queryString);		
			ServletActionContext.getResponse().sendRedirect(loginAction);
			
			return null;
		}
		ServletActionContext.getRequest().setAttribute("is_login", 1);
		return invocation.invoke();
	}

	@Override
	public void destroy() {
		System.out.println("destroyed login user interceptor");
	}

	@Override
	public void init() {
		System.out.println("init login user interceptor");
	}
}
