package actions;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;

public class DeleteMusic extends ActionSupport {
    private int music_id;
    private int STATUS;

    public int getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(int STATUS) {
        this.STATUS = STATUS;
    }

    public int getMusic_id() {
        return music_id;
    }

    public void setMusic_id(int music_id) {
        this.music_id = music_id;
    }

    @Override
    public String execute() throws Exception {
        ServletContext sctx = ServletActionContext.getServletContext();
        Connection con = (Connection) sctx.getAttribute("DBCon");
        ResultSet rs;

        String querySQL="SELECT cover_file,lyric_file,music_file FROM music_info WHERE music_id="+music_id+"";
        String updateSQL="DELETE FROM music_info WHERE music_id="+music_id+"";

        rs=con.createStatement().executeQuery(querySQL);
        //File cover,music,lrc;
        if(rs.next()){
            new File((String)sctx.getAttribute("CoverPath"),rs.getString(1)).delete();
            new File((String)sctx.getAttribute("LrcPath"),rs.getString(2)).delete();
            new File((String)sctx.getAttribute("MusicPath"),rs.getString(3)).delete();
        }

        con.createStatement().executeUpdate(updateSQL);
        STATUS=1000;
        return SUCCESS;

    }
}