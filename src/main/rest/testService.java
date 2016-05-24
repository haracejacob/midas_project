package main.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.java.except.BaseException;
import main.java.logic.testLogic;
import main.java.vo.testVO;

public class testService {
	public static HashMap<String, testVO> messages = new HashMap<String, testVO>();
	private static int nextMessageId = 0;
	private static List<testVO> testList = null;
	
	static {
		testList = new ArrayList<testVO>();
	    try {
			testList = testLogic.getTestList();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    nextMessageId = testList.size()+1;
	    for(int i=0; i<nextMessageId-1; i++) 
	    	messages.put(Integer.toString(i+1), testList.get(i));
	}
	
	public static List<testVO> findAll() {
		return new ArrayList<testVO>(messages.values());
	}
	
	public static testVO find(String id) {
		return messages.get(id);
	}
	
	
	public static void save(testVO message) {
	    if (message.getSeq() == 0) {
	        String id = String.valueOf(nextMessageId);
	        message.setSeq(Integer.parseInt(id));
	        nextMessageId++;
	    }
	    messages.put(message.getId(), message);
	}
	
	public static void remove(String id) {
	    messages.remove(id);
	}
}
