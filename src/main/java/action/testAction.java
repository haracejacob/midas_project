package main.java.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.except.BaseException;
import main.java.logic.testLogic;
import main.java.vo.testVO;
import lombok.Data;

@Data
public class testAction {
	//testVO
	private List<testVO> testList = null;
	private Integer seq =0;
	private String id = null;
	private Integer gems = 0;
	private Integer coins = 0;
	private Integer hearts = 0;
	private Integer highscore = 0;
	
	public String showTestList() throws BaseException
	{
		testList = testLogic.getTestList();
		
		return "SUCCESS";
	}
	
	public String insertTest() throws BaseException, SQLException
	{
		testVO testElement = new testVO();
		
		testElement.setId(id);
		testElement.setGems(gems);
		testElement.setCoins(coins);
		testElement.setHearts(hearts);
		testElement.setHighscore(highscore);
		
		testLogic.insertTest(testElement);
		
		return "SUCCESS";
	}
	
	public String deleteTest() throws BaseException
	{
		testLogic.deleteTest(seq);
		
		return "SUCCESS";
	}
}
