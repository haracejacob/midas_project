package main.java.interceptor;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import main.java.vo.userVO;


@SuppressWarnings("serial")
public class UserInterceptor extends AbstractInterceptor {	
	private String redirectAction = "";
	private Integer user_seq;
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext context = invocation.getInvocationContext();
		Map<String, Object> session = context.getSession();

		userVO s_user = (userVO)session.get("s_user");
		user_seq = (Integer) ServletActionContext.getRequest().getAttribute("user_seq");
		
		if(s_user.getUser_seq() != user_seq){
			HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
			String queryString = request.getQueryString();
			String currentURI = ServletActionContext.getRequest().getServletPath() + (queryString != null ?"?" + queryString : "");
			context.getSession().put("url", currentURI);
			context.getSession().put("query", queryString);
			ServletActionContext.getResponse().sendRedirect(redirectAction);
			return null;
		}

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

	public Integer getUser_seq() {
		return user_seq;
	}

	public void setUser_seq(Integer user_seq) {
		this.user_seq = user_seq;
	}
	
}
