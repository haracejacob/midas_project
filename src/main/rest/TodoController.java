package main.rest;

import java.sql.SQLException;
import java.util.Collection;

import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.apache.struts2.rest.RestActionSupport;

import com.opensymphony.xwork2.ModelDriven;

import main.java.except.BaseException;
import main.java.vo.todoVO;
import lombok.Data;

    
@Data
public class TodoController extends RestActionSupport implements ModelDriven<Object>{
		
	private static final long serialVersionUID = 89268916175477696L;
    private todoVO model = new todoVO();
    private String id;
    private Collection<todoVO> list;
    
    public HttpHeaders create() throws BaseException, SQLException {
    	TodoService.save(model);
        return new DefaultHttpHeaders("create");
    }

    public HttpHeaders destroy() throws BaseException {
    	TodoService.remove(id);
        return new DefaultHttpHeaders("destroy");
    }

    public HttpHeaders show() {
    	model = TodoService.find(id);
        return new DefaultHttpHeaders("show").disableCaching();
    }

    public HttpHeaders update() throws BaseException, SQLException {
    	TodoService.save(model);
    	return new DefaultHttpHeaders("update");
    }

    public HttpHeaders index() {
        list = TodoService.findAll();
        return new DefaultHttpHeaders("index").disableCaching();
    }
    
    public Object getModel() {
        return list != null ? list : model;
    }

    public void setId(String id) {
        this.id = id;
    }
}