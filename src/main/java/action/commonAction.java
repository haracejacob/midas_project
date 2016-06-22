package main.java.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import lombok.Data;
import main.java.except.BaseException;
import main.java.logic.categoryLogic;
import main.java.logic.commonLogic;
import main.java.logic.todoLogic;
import main.java.logic.userLogic;
import main.java.vo.categoryVO;
import main.java.vo.todoVO;
import main.java.vo.userVO;

@Data
public class commonAction {
	//user 관련 변수 선언
	private userVO s_user;
	private List<categoryVO> my_category;
	private List<todoVO> my_todo;
	
	private int user_seq = 0;
	private String user_name=null;
	private String user_phone=null;
	private String user_email=null;
	private String user_passwd=null;
	private String session_id=null;
	//user 관련 변수 끝
	
	private String err_msg=null;
	
	
	@SuppressWarnings("unchecked")
	public String execute() throws BaseException {
		ActionContext context = ActionContext.getContext(); 
		Map<String, Object> session = context.getSession();
		
		s_user = (userVO) session.get("s_user");
		if(s_user == null) {
			user_email=getUser_email();
			return "SUCCESS";
		}
		
		my_category = (List<categoryVO>)session.get("s_categoryList");
		my_todo = (List<todoVO>)session.get("s_todoList");
		
		session_id = ServletActionContext.getRequest().getSession().getId();
		
		return "LOGINED";
	}
	
	public String login() throws BaseException {
		ActionContext context = ActionContext.getContext(); 
		Map<String, Object> session = context.getSession();
		
		userVO current_user = new userVO();
		
		session.remove("s_user");
		
		current_user = commonLogic.loginCheck(user_email, user_passwd);
		
		if( current_user == null) {
			String check_id = commonLogic.IdCheck(user_email);
			
			if(check_id == null) {
				err_msg="아이디가 존재하지 않습니다.";
				return "SIGNUP";
			}
			else {
				err_msg="아이디/비밀번호가 틀렸습니다.";
				return "FAIL";
			}
			/*commonLogic.signUser(user_email,user_passwd);
			
			current_user = commonLogic.loginCheck(user_email, user_passwd);*/	
		}	
		my_category = categoryLogic.getCategoryList(current_user.getUser_seq());
		my_todo = todoLogic.getTodoList(current_user.getUser_seq());
		
		session.put("s_user", current_user);
		session.put("s_categoryList", my_category);
		session.put("s_todoList", my_todo);
		
		return "LOGINED";
	}
	
	public String logout() throws BaseException {
		ActionContext context = ActionContext.getContext(); 
		Map<String, Object> session = context.getSession();
		
		userVO current_user = new userVO();
		
		current_user = (userVO)session.get("s_user");
		
		if( current_user != null ){
			session.remove("s_user");
			session.remove("s_categoryList");
			session.remove("s_todoList");
		}
		
		return "SUCCESS";
	}
	
	public String signup() throws BaseException {
		ActionContext context = ActionContext.getContext(); 
		Map<String, Object> session = context.getSession();
		
		userVO current_user = new userVO();

		if(session.get("s_user") != null)
			return "SUCCESS";

		current_user.setUser_email(user_email);
		current_user.setUser_name(user_name);
		current_user.setUser_passwd(user_passwd);
		current_user.setUser_phone(user_phone);
		
		user_seq = commonLogic.signUser(current_user);
		current_user.setUser_seq(user_seq);
		my_category = categoryLogic.getCategoryList(current_user.getUser_seq());
		my_todo = todoLogic.getTodoList(current_user.getUser_seq());
		
		session.put("s_user", current_user);
		session.put("s_categoryList", my_category);
		session.put("s_todoList", my_todo);
		
		return "SUCCESS";
	}
}
