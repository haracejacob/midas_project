package main.rest;

import java.util.Collection;

import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.opensymphony.xwork2.ModelDriven;

import main.java.vo.testVO;
import lombok.Data;

@Data
public class testController implements ModelDriven<Object>{
	private static final long serialVersionUID = 89268916175477696L;
    private testVO model = new testVO();
    private String id;
    private Collection<testVO> list;
    
    public HttpHeaders create() {
        testService.save(model);
        return new DefaultHttpHeaders("create");
    }

    public HttpHeaders destroy() {
        return new DefaultHttpHeaders("destroy");
    }

    public HttpHeaders show() {
        return new DefaultHttpHeaders("show").disableCaching();
    }

    public HttpHeaders update() {
    	testService.save(model);
        return new DefaultHttpHeaders("update");
    }

    public HttpHeaders index() {
        list = testService.findAll();
        return new DefaultHttpHeaders("index").disableCaching();
    }
    
    public Object getModel() {
        return (list != null ? list : model);
    }
    public void setId(String id) {
        if (id != null) {
            this.model = testService.find(id);
        }
        this.id = id;
    }
}
