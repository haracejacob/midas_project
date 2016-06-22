package main.rest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.sun.xml.internal.ws.api.message.Message;

import main.java.except.BaseException;
import main.java.logic.commonLogic;
import main.java.logic.categoryLogic;
import main.java.vo.categoryVO;
import main.java.vo.todoVO;
import main.java.vo.userVO;

public class CategoryService {
	public static HashMap<String, categoryVO> messages = new HashMap<String, categoryVO>();
	private static int nextMessageId = 0;
	private static List<categoryVO> categoryList = null;
	
	static {
		categoryList = new ArrayList<categoryVO>();
	
	    try {
			categoryList = categoryLogic.getCategoryList();
			nextMessageId = commonLogic.getCurrentAI("category");
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	    for(int i=0; i<categoryList.size(); i++)
	    	messages.put(Integer.toString(categoryList.get(i).getCategory_seq()), categoryList.get(i));
	}
	
	public static List<categoryVO> findAll() {
		ActionContext context = ActionContext.getContext(); 
		Map<String, Object> session = context.getSession();
		
		userVO s_user = (userVO) session.get("s_user");
		
		List<categoryVO> categoryList = new ArrayList<categoryVO>(); 
		
		int user_seq = s_user.getUser_seq();
		for(String key : messages.keySet()) {
			if(user_seq == messages.get(key).getUser_seq()) {
				categoryList.add(messages.get(key));
			}
		}
		
		return categoryList;
	}
	
	public static categoryVO find(String id) {
		return messages.get(id);
	}
	
	public static void update(categoryVO message) throws SQLException, BaseException {
		//categoryLogic.updateCategory(message);
		System.out.println(message);
		messages.put(Integer.toString(message.getCategory_seq()), message);
	}
	
	public static void save(categoryVO message) throws BaseException, SQLException {
		ActionContext context = ActionContext.getContext(); 
		Map<String, Object> session = context.getSession();
		
		userVO s_user = (userVO) session.get("s_user");
		message.setUser_seq(s_user.getUser_seq());
		
		if (message.getCategory_seq() == 0) {
	        String id = String.valueOf(nextMessageId);
	        message.setCategory_seq(Integer.parseInt(id));
	        nextMessageId++;
	        
		    categoryLogic.insertCategory(message);
	    }
	    else {
	    	categoryLogic.setCategory(message);
	    }
		categoryList = categoryLogic.getCategoryList(s_user.getUser_seq());
		session.put("s_categoryList", categoryList);
	    messages.put(Integer.toString(message.getCategory_seq()), message);
	}
	
	@SuppressWarnings("unchecked")
	public static void remove(String id) throws BaseException {
		ActionContext context = ActionContext.getContext(); 
		Map<String, Object> session = context.getSession();
		
		List<categoryVO> my_category = (List<categoryVO>)session.get("s_categoryList");
		
		int seq = Integer.parseInt(id);
		categoryLogic.deleteCategory(seq);
		
		for(int i=0; i<my_category.size(); i++) {
			if(my_category.get(i).getCategory_seq() == seq) {
				my_category.remove(i);
				break;
			}
		}
		session.put("s_todoList", my_category);
		messages.remove(id);
	}
}
