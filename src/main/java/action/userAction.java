package main.java.action;

import java.sql.SQLException;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.opensymphony.xwork2.ActionContext;

import main.java.except.BaseException;
import main.java.logic.userLogic;
import main.java.vo.userVO;
import main.rest.UserService;
import lombok.Data;

@Data
public class userAction {
	//userVO 변수 선언
	private userVO user = null;
	private int user_seq=0;
	private String user_name=null;
	private String user_passwd=null;
	private String user_email=null;
	private String user_phone=null;
	//userVO 변수 선언 끝
	private userVO s_user = null;
	
	private String modify_user;
	
	public String modifyUser() throws BaseException, SQLException {
		ActionContext context = ActionContext.getContext(); 
		Map<String, Object> session = context.getSession();
		Object obj = JSONValue.parse(modify_user);
		JSONObject object = (JSONObject)obj;
		
		
		s_user = (userVO) session.get("s_user");
		
		user = new userVO();
		
		user.setUser_seq(s_user.getUser_seq());
		user.setUser_passwd((String)object.get("user_passwd"));
		user.setUser_email(s_user.getUser_email());
		user.setUser_name(s_user.getUser_name());
		user.setUser_phone(s_user.getUser_phone());
		
		UserService.save(user);
		//userLogic.setUser(user);
		
		//바뀐 정보 session에 저장
		session.put("s_user", user);
		
		return "SUCCESS";
	}

}
