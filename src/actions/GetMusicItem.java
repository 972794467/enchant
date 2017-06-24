package actions;

import beans.Music;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.ResultSet;

public class GetMusicItem extends ActionSupport {
    private int music_id;
    private Music musicItem;

    public int getMusic_id() {
        return music_id;
    }

    public void setMusic_id(int music_id) {
        this.music_id = music_id;
    }

    public Music getMusicItem() {
        return musicItem;
    }

    public void setMusicItem(Music musicItem) {
        this.musicItem = musicItem;
    }

    public String execute() throws Exception {

        ServletContext sctx = ServletActionContext.getServletContext();
        Connection con = (Connection) sctx.getAttribute("DBCon");
        ResultSet rs, rs1;
//        String querySql="SELECT count(music_id) FROM music_info";
//        rs=con.createStatement().executeQuery(querySql);
//        if(rs.next()){
//            offset=rs.getInt(1)/limit;
//        }

        String querySql = "select * from music_info WHERE music_id = "+music_id+" ";
        System.out.println(querySql);
        rs = con.createStatement().executeQuery(querySql);
        while (rs.next()) {
//            System.out.println(rs.getInt(1));
//                    rs.getString(2);
//                    rs.getInt(3);
//                    rs.getString(4);
//                    rs.getString(5);
//                    rs.getString(6);
//                    rs.getString(7);
//            System.out.println(   rs.getInt(8));
//                    rs.getString(9);
//                    rs.getString(10);
//                    rs.getInt(11);
//                    rs.getString(12);
//                    rs.getInt(13) ;
//                    rs.getInt(14);
            musicItem = new Music(rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getInt(8),
                    rs.getString(9),
                    rs.getString(10),
                    rs.getInt(11),
                    rs.getString(12),
                    rs.getInt(13),
                    rs.getInt(14));

        }

        return SUCCESS;
    }

}