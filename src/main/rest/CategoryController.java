package main.rest;

import java.sql.SQLException;
import java.util.Collection;

import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.opensymphony.xwork2.ModelDriven;

import main.java.except.BaseException;
import main.java.vo.categoryVO;
import lombok.Data;

@Data
public class CategoryController implements ModelDriven<Object>{
	private static final long serialVersionUID = 89268916175477696L;
    private categoryVO model = new categoryVO();
    private String id;
    private Collection<categoryVO> list;
    
    public HttpHeaders create() throws BaseException, SQLException {
    	CategoryService.save(model);
        return new DefaultHttpHeaders("create");
    }

    public HttpHeaders destroy() throws BaseException {
    	CategoryService.remove(id);
        return new DefaultHttpHeaders("destroy");
    }

    public HttpHeaders show() {
    	model = CategoryService.find(id);
        return new DefaultHttpHeaders("show").disableCaching();
    }

    public HttpHeaders update() throws BaseException, SQLException {
    	CategoryService.save(model);
    	return new DefaultHttpHeaders("update");
    }

    public HttpHeaders index() {
        list = CategoryService.findAll();
        return new DefaultHttpHeaders("index").disableCaching();
    }
    
    public Object getModel() {
        return list != null ? list : model;
    }

    public void setId(String id) {
        this.id = id;
    }
}
