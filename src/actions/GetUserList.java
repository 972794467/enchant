package actions;

import beans.UserItem;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GetUserList extends ActionSupport {

    private int ps;
    private int pn;
    private ArrayList<UserItem> userList;
    private UserItem[] users;
    private int count;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<UserItem> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<UserItem> userList) {
        this.userList = userList;
    }

    public UserItem[] getUsers() {
        return users;
    }

    public void setUsers(UserItem[] users) {
        this.users = users;
    }



    @Override
    public String execute() throws Exception {
        int limit = ps;
        int offset = (pn - 1) * ps;
        //System.out.println(sc + " " + sn);
        userList = new ArrayList<UserItem>();

        ServletContext sctx = ServletActionContext.getServletContext();
        Connection con = (Connection) sctx.getAttribute("DBCon");
        ResultSet rs, rs1;
//        String querySql="SELECT count(music_id) FROM music_info";
//        rs=con.createStatement().executeQuery(querySql);
//        if(rs.next()){
//            offset=rs.getInt(1)/limit;
//        }

        String querySql = "SELECT user_id,user_name,user_email,user_sex,user_type,user_avatar FROM user_info limit "+limit+" offset "+offset+" ";
        System.out.println(querySql);
        rs = con.createStatement().executeQuery(querySql);
        while (rs.next()) {

            UserItem useritem = new UserItem(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getInt(5),
                    rs.getString(6));
            System.out.println(useritem.getAvatar());
            userList.add(useritem);
        }

        querySql = "select COUNT(user_id) from user_info ";
        rs=con.createStatement().executeQuery(querySql);
        //count=0;
        if (rs.next()){
            count=rs.getInt(1);
        }
        users=(UserItem[])userList.toArray(new UserItem[0]);
        System.out.println(users.length+" "+users[0].getType());
        return SUCCESS;
    }
}