package main.rest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import main.java.except.BaseException;
import main.java.logic.commonLogic;
import main.java.logic.todoLogic;
import main.java.vo.categoryVO;
import main.java.vo.todoVO;
import main.java.vo.userVO;

public class TodoService {
	public static HashMap<String, todoVO> messages = new HashMap<String, todoVO>();
	private static int nextMessageId = 0;
	private static List<todoVO> todoList = null;
	
	static {
		todoList = new ArrayList<todoVO>();
		
	    try {
			todoList = todoLogic.getTodoList();
			nextMessageId = commonLogic.getCurrentAI("todo");
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	    for(int i=0; i<todoList.size(); i++)
	    	messages.put(Integer.toString(todoList.get(i).getTodo_seq()), todoList.get(i));
	}
	
	public static List<todoVO> findAll() {
		ActionContext context = ActionContext.getContext(); 
		Map<String, Object> session = context.getSession();
		
		userVO s_user = (userVO) session.get("s_user");
		
		List<todoVO> todoList = new ArrayList<todoVO>(); 
		
		int user_seq = s_user.getUser_seq();
		for(String key : messages.keySet()) {
			if(user_seq == messages.get(key).getUser_seq()) {
				todoList.add(messages.get(key));
			}
		}
		
		return todoList;
	}
	
	public static todoVO find(String id) {
		return messages.get(id);
	}
	
	public static void update(todoVO message) throws SQLException, BaseException {
		//todoLogic.updatetodo(message);
		System.out.println(message);
		messages.put(Integer.toString(message.getTodo_seq()), message);
	}
	
	public static void save(todoVO message) throws BaseException, SQLException {
		ActionContext context = ActionContext.getContext(); 
		Map<String, Object> session = context.getSession();
		
		userVO s_user = (userVO) session.get("s_user");
		message.setUser_seq(s_user.getUser_seq());
		if (message.getTodo_seq() == 0) {
	        String id = String.valueOf(nextMessageId);
	        message.setTodo_seq(Integer.parseInt(id));
	        nextMessageId++;
	        
		    todoLogic.insertTodo(message);
	    }
	    else {
	    	todoLogic.setTodo(message);
	    }
		todoList = todoLogic.getTodoList(s_user.getUser_seq());
		session.put("s_todoList", todoList);
	    messages.put(Integer.toString(message.getTodo_seq()), message);
	}
	
	@SuppressWarnings("unchecked")
	public static void remove(String id) throws BaseException {
		ActionContext context = ActionContext.getContext(); 
		Map<String, Object> session = context.getSession();
		
		List<todoVO> my_todo = (List<todoVO>)session.get("s_todoList");
		
		int seq = Integer.parseInt(id);
		todoLogic.deleteTodo(seq);
		
		for(int i=0; i<my_todo.size(); i++) {
			if(my_todo.get(i).getTodo_seq() == seq) {
				my_todo.remove(i);
				break;
			}
		}
		session.put("s_todoList", my_todo);
		messages.remove(id);
	}
}
