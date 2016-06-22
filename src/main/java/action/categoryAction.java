package main.java.action;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.opensymphony.xwork2.ActionContext;

import main.java.except.BaseException;
import main.java.logic.categoryLogic;
import main.java.logic.todoLogic;
import main.java.vo.categoryVO;
import main.java.vo.userVO;
import main.java.vo.todoVO;
import main.rest.CategoryService;
import lombok.Data;

@Data
public class categoryAction {
	//categoryVO 변수선언
	private categoryVO category=null;
	private Integer category_seq=0;
	private int user_seq=0;
	private String category_name=null;
	private String category_hash=null;
	//categoryVO 변수선언 끝
	private userVO s_user = null;
	
	private String modify_category;
	
	public String modifyCategory() throws BaseException, SQLException {
		Object obj = JSONValue.parse(modify_category);
		JSONObject object = (JSONObject)obj;
		
		category = new categoryVO();
		
		category.setCategory_seq(Integer.parseInt((String)object.get("category_seq")));
		category.setCategory_name((String)object.get("category_name"));
		
		CategoryService.save(category);
		//categoryLogic.setCategory(category);
		
		return "SUCCESS";
	}
	
	public String checkCategoryHash() throws BaseException, SQLException {
		ActionContext context = ActionContext.getContext(); 
		Map<String, Object> session = context.getSession();
		
		userVO current_user = new userVO();
		current_user = (userVO)session.get("s_user");
		
		category_seq = categoryLogic.checkCategorySeq(category_hash);
		if(category_seq == null)
			return "FAIL";
		System.out.println(category_seq);
		
		//s_categoryList 가져오는 액션 점검 category_seq로 가져오는지 user_seq로 가져오는지
		categoryLogic.joinCategory(category_seq,current_user.getUser_seq());
		
		List<categoryVO> current_category = new ArrayList<categoryVO>();
		current_category = categoryLogic.getCategoryList(current_user.getUser_seq());
		session.put("s_categoryList", current_category);
		List<todoVO> current_todo = new ArrayList<todoVO>();
		current_todo = todoLogic.getTodoList(current_user.getUser_seq());
		session.put("s_todoList", current_todo);
		
		return "SUCCESS";
	}
}
