package actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

public class Logout extends ActionSupport {
    @Override
    public String execute() throws Exception {
        ServletActionContext.getRequest().getSession().invalidate();
        return SUCCESS;
    }
}