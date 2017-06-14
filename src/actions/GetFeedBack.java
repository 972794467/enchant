package actions;

import beans.FeedBack;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GetFeedBack extends ActionSupport {
    private  ArrayList<FeedBack> feedback;
    private  int ps;
    private int pn;
    private int count;

    public ArrayList<FeedBack> getFeedback() {
        return feedback;
    }

    public void setFeedback(ArrayList<FeedBack> feedback) {
        this.feedback = feedback;
    }

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



    @Override
    public String execute() throws Exception {
        int limit = ps;
        int offset = (pn - 1) * ps;
        feedback=new ArrayList<FeedBack>();

        ServletContext sctx = ServletActionContext.getServletContext();
        Connection con = (Connection) sctx.getAttribute("DBCon");
        ResultSet rs;

        String querySQL= "select send_user_id,title,content,create_time from messages where message_type="+1+" limit "+limit+" offset "+offset+" ";
        rs=con.createStatement().executeQuery(querySQL);
        while (rs.next()){
            FeedBack fbItem=new FeedBack(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
            feedback.add(fbItem);
            System.out.println(fbItem.getContent());
        }
        querySQL = "select COUNT(id) from messages where message_type=1";
        rs=con.createStatement().executeQuery(querySQL);
        //count=0;
        if (rs.next()){
            count=rs.getInt(1);
        }

        return SUCCESS;


    }
}