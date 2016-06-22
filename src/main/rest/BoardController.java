package main.rest;

import java.sql.SQLException;
import java.util.Collection;

import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.opensymphony.xwork2.ModelDriven;

import main.java.except.BaseException;
import main.java.vo.boardVO;
import lombok.Data;

@Data
public class BoardController implements ModelDriven<Object>{
	private static final long serialVersionUID = 89268916175477696L;
    private boardVO model = new boardVO();
    private String id;
    private Collection<boardVO> list;
    
    public HttpHeaders create() throws BaseException, SQLException {
    	BoardService.save(model);
        return new DefaultHttpHeaders("create");
    }

    public HttpHeaders destroy() throws BaseException {
    	BoardService.remove(id);
        return new DefaultHttpHeaders("destroy");
    }

    public HttpHeaders show() {
    	model = BoardService.find(id);
        return new DefaultHttpHeaders("show").disableCaching();
    }

    public HttpHeaders update() throws BaseException, SQLException {
    	BoardService.save(model);
    	return new DefaultHttpHeaders("update");
    }

    public HttpHeaders index() {
        list = BoardService.findAll();
        return new DefaultHttpHeaders("index").disableCaching();
    }
    
    public Object getModel() {
        return list != null ? list : model;
    }

    public void setId(String id) {
        this.id = id;
    }
}
