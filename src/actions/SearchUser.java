package actions;

import beans.UserItem;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SearchUser extends ActionSupport {
    private String name;
    private String id;
    private int ps;
    private int pn;
    private int count;
    ArrayList<UserItem> users;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public int getPn() {
        return pn;
    }

    public void setPn(int pn) {
        this.pn = pn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<UserItem> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<UserItem> users) {
        this.users = users;
    }

    @Override
    public String execute() throws Exception {
        int limit = ps;
        int offset = (pn - 1) * ps;
        users = new ArrayList<UserItem>();

        String querySQL = "";
        if (name==null || name.equals(""))
            querySQL = "SELECT user_id,user_name,user_email,user_sex,user_type,user_avatar FROM user_info WHERE   user_id = " + id + " limit "+limit+" offset "+offset+" ";
        if (id==null || id.equals(""))
            querySQL = "SELECT user_id,user_name,user_email,user_sex,user_type,user_avatar FROM user_info WHERE   user_name = '" + name + "' limit "+limit+" offset "+offset+" ";

        ServletContext sctx = ServletActionContext.getServletContext();
        Connection con = (Connection) sctx.getAttribute("DBCon");
        ResultSet rs;

        System.out.println(querySQL);
        rs = con.createStatement().executeQuery(querySQL);
        while (rs.next()) {
            UserItem user = new UserItem(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),rs.getInt(5),rs.getString(6));
            users.add(user);
        }

        if (name==null || name.equals(""))
            querySQL = "SELECT count(user_id) FROM user_info WHERE   user_id = " + id + " ";
        if (id==null || id.equals(""))
            querySQL = "SELECT COUNT (user_id) FROM user_info WHERE   user_name = '" + name + "'  ";

        rs=con.createStatement().executeQuery(querySQL);
        //count=0;
        if (rs.next()){
            count=rs.getInt(1);
        }
        return SUCCESS;
    }
}