package actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.ResultSet;

public class GetMyinfo extends ActionSupport {
    private int id;
    private String name;
    private String email;
    private int sex;
    private int type;
    private String avatar;

    public int getId() {
        return id;
    }



    public String getName() {
        return name;
    }



    public String getEmail() {
        return email;
    }



    public int getSex() {
        return sex;
    }



    public int getType() {
        return type;
    }



    public String getAvatar() {
        return avatar;
    }



    @Override
    public String execute() throws Exception {
        ServletContext sctx = ServletActionContext.getServletContext();
        Connection con = (Connection) sctx.getAttribute("DBCon");
        ResultSet rs;
        System.out.println(ServletActionContext.getRequest().getSession().getAttribute("user_id"));
        rs=con.createStatement().executeQuery("SELECT  user_id,user_name,user_email,user_sex,user_type,user_avatar FROM user_info WHERE user_id = "+ServletActionContext.getRequest().getSession().getAttribute("user_id")+" ");
        if (rs.next()){
            id=rs.getInt(1);
            name=rs.getString(2);
            email=rs.getString(3);
            sex=rs.getInt(4);
            type=rs.getInt(5);
            avatar=rs.getInt(6)+"";
        }
        ServletActionContext.getResponse().getWriter().print("<script>alert('id:"+id+"\\nname:"+name+"\\nemail:"+email+"\\nsex:"+sex+"\\navatar:"+avatar+"');window.location.href='/enchant/enchantAdmin/index.jsp';</script>");
        return null;

    }
}