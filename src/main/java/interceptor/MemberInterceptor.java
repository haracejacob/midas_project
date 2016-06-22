package main.java.interceptor;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import main.java.vo.userVO;
import main.java.logic.commonLogic;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class MemberInterceptor extends AbstractInterceptor{
	private String redirectAction = "";
	private Integer user_seq=0;
	private Integer category_seq=0;
	
	public String intercept(ActionInvocation invocation) throws Exception{
		ActionContext context = invocation.getInvocationContext();
		Map<String, Object> session = context.getSession();
		
		userVO s_user = (userVO)session.get("s_user");
		
		category_seq = (Integer) ServletActionContext.getRequest().getAttribute("category_seq");
		user_seq = commonLogic.getCategoryUserConnection(s_user.getUser_seq(),category_seq);
		
		if(user_seq == null){
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
	
	public void destroy(){
		System.out.println("destroyed login user interceptor");
	}
	
	public void init(){
		System.out.println("init login user interceptor");
	}
}
