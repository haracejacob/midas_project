package main.rest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import main.java.except.BaseException;
import main.java.logic.commonLogic;
import main.java.logic.userLogic;
import main.java.vo.todoVO;
import main.java.vo.userVO;

public class UserService {
	public static HashMap<String, userVO> messages = new HashMap<String, userVO>();
	private static int nextMessageId = 0;
	private static List<userVO> userList = null;
	
	static {
		userList = new ArrayList<userVO>();
		
	    try {
			userList = userLogic.getUserList();
			nextMessageId = commonLogic.getCurrentAI("user");
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	    for(int i=0; i<userList.size(); i++)
	    	messages.put(Integer.toString(userList.get(i).getUser_seq()), userList.get(i));
	}
	
	public static List<userVO> findAll() {
		ActionContext context = ActionContext.getContext(); 
		Map<String, Object> session = context.getSession();
		
		userVO s_user = (userVO) session.get("s_user");
		
		List<userVO> userList = new ArrayList<userVO>(); 
		
		int user_seq = s_user.getUser_seq();
		userList.add(messages.get(Integer.toString(user_seq)));
		
		return userList;
	}
	
	public static userVO find(String id) {
		return messages.get(id);
	}
	
	public static void update(userVO message) throws SQLException, BaseException {
		//userLogic.updateuser(message);
		System.out.println(message);
		messages.put(Integer.toString(message.getUser_seq()), message);
	}
	
	public static void save(userVO message) throws BaseException, SQLException {
	    System.out.println(message);
		if (message.getUser_seq() == 0) {
	        String id = String.valueOf(nextMessageId);
	        message.setUser_seq(Integer.parseInt(id));
	        nextMessageId++;
	        
		    userLogic.insertUser(message);
	    }
	    else {
	    	userLogic.setUser(message);
	    }
	    messages.put(Integer.toString(message.getUser_seq()), message);
	}
	
	public static void remove(String id) throws BaseException {
		int seq = Integer.parseInt(id);
		userLogic.deleteUser(seq);
		
		messages.remove(id);
	}
}
