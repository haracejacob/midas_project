package main.rest;

import java.sql.SQLException;
import java.util.Collection;

import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.opensymphony.xwork2.ModelDriven;

import main.java.except.BaseException;
import main.java.vo.userVO;
import lombok.Data;

@Data
public class UserController implements ModelDriven<Object>{
		
	private static final long serialVersionUID = 89268916175477696L;
    private userVO model = new userVO();
    private String id;
    private Collection<userVO> list;
    
    public HttpHeaders create() throws BaseException, SQLException {
    	UserService.save(model);
        return new DefaultHttpHeaders("create").setLocationId(model.getUser_seq());
    }

    public HttpHeaders destroy() throws BaseException {
    	UserService.remove(id);
        return new DefaultHttpHeaders("destroy");
    }

    public HttpHeaders show() {
    	model = UserService.find(id);
        return new DefaultHttpHeaders("show").disableCaching();
    }

    public HttpHeaders update() throws BaseException, SQLException {
    	UserService.save(model);
    	return new DefaultHttpHeaders("update");
    }

    public HttpHeaders index() {
        list = UserService.findAll();
        return new DefaultHttpHeaders("index").disableCaching();
    }
    
    public Object getModel() {
        return list != null ? list : model;
    }

    public void setId(String id) {
        this.id = id;
    }
}