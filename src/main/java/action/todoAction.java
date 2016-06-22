package main.java.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import main.java.except.BaseException;
import main.java.logic.todoLogic;
import main.java.vo.todoVO;
import main.java.vo.userVO;
import main.rest.TodoService;

import lombok.Data;

@Data
public class todoAction {
	//Todo 관련 변수 선언
	private todoVO todo = null;
	private int todo_seq=0;
	private int user_seq=0;
	private int category_seq;
	private String start_date=null;
	private String end_date=null;
	private String contents=null;
	private int complete=0;
	private int bookmark=0;
	
	private userVO s_user = null;
	
	private String modify_todo;
	
	public String modifyTodo() throws BaseException, SQLException {
		Object obj = JSONValue.parse(modify_todo);
		JSONObject object = (JSONObject)obj;
		
		todo = new todoVO();
		
		todo.setTodo_seq(Integer.parseInt((String)object.get("todo_seq")));
		todo.setContents((String)object.get("contents"));
		todo.setComplete(Integer.parseInt((String)object.get("complete")));
		todo.setBookmark(Integer.parseInt((String)object.get("bookmark")));
		
		TodoService.save(todo);
		//todoLogic.setTodo(todo);
		
		return "SUCCESS";
	}
	
	public String modifyComplete() throws BaseException, SQLException {

		todoLogic.setComplete(todo_seq,complete);
		
		return "SUCCESS";
	}
	
	public String modifyBookmark() throws BaseException, SQLException {

		/*if(todo_seq == 0)
			return "FAIL";*/
		todoLogic.setBookmark(5,1);
		
		return "SUCCESS";
	}
}
