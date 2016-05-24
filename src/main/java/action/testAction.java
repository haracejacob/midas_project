package main.java.action;

import java.util.ArrayList;
import java.util.List;

import main.java.except.BaseException;
import main.java.logic.testLogic;
import main.java.vo.testVO;
import lombok.Data;

@Data
public class testAction {
	private List<testVO> testList = null;
	
	public String execute() throws BaseException
	{
		//List<testVO> testList = new ArrayList<testVO>();
		
		testList = testLogic.getTestList();
		
		return "SUCCESS";
	}
}
