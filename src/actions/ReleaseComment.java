package actions;


import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.sql.Connection;
import java.sql.ResultSet;

public class ReleaseComment extends ActionSupport {
    private int user_id;
    private int to_user_id;
    private int music_id;
    private String music_name;
    private String comment;

    private int STATUS;

    public int getSTATUS() {
        return STATUS;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTo_user_id() {
        return to_user_id;
    }

    public void setTo_user_id(int to_user_id) {
        this.to_user_id = to_user_id;
    }

    public int getMusic_id() {
        return music_id;
    }

    public void setMusic_id(int music_id) {
        this.music_id = music_id;
    }

    public String getMusic_name() {
        return music_name;
    }

    public void setMusic_name(String music_name) {
        this.music_name = music_name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String execute() throws Exception {
        String user_name="",to_user_name="";
        if (music_name == null)
            music_name="";

        Connection con=(Connection) ServletActionContext.getServletContext().getAttribute("DBCon");
        String querySQL;
        ResultSet rs;

        querySQL="SELECT user_name FROM user_info WHERE user_id = "+user_id+" ";
        rs=con.createStatement().executeQuery(querySQL);
        if(rs.next()){
            user_name=rs.getString(1);
            System.out.println(user_name);
        }

        if (to_user_id != 0) {
             querySQL = "SELECT user_name FROM user_info WHERE user_id = " + to_user_id + " ";
             rs = con.createStatement().executeQuery(querySQL);
            if(rs.next()){
                to_user_name=rs.getString(1);
            }
        }
        System.out.println("INSERT INTO music_comment(user_id, user_name, to_user_id, to_user_name, music_id, music_name, comment, create_time) VALUE ("+user_id+","+user_name+","+to_user_id+","+to_user_name+","+music_id+",'"+music_name+"','"+comment+"',"+System.currentTimeMillis() / 1000+")");
        con.createStatement().executeUpdate("INSERT INTO music_comment(user_id, user_name, to_user_id, to_user_name, music_id, music_name, comment, create_time) VALUE ("+user_id+",'"+user_name+"',"+to_user_id+",'"+to_user_name+"',"+music_id+",'"+music_name+"','"+comment+"',"+System.currentTimeMillis() / 1000+")");
        STATUS=1000;

        return  SUCCESS;
    }
}