package actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import java.sql.Connection;

public class EditAvatar extends ActionSupport {
        private int id;
        private int avatar;
        private int STATUS;

    public int getSTATUS() {
        return STATUS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }



    @Override
    public String execute() throws Exception {
        ServletContext sctx = ServletActionContext.getServletContext();
        Connection con = (Connection)sctx.getAttribute("DBCon");
        System.out.println(id+" "+avatar);
        String updateSQL="UPDATE user_info SET user_avatar='"+avatar+"' WHERE user_id='"+id+"'";
        con.createStatement().executeUpdate(updateSQL);
        STATUS=1000;
        return SUCCESS;
    }
}