package actions;

import beans.MusicCommnet;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GetMusicComment extends ActionSupport {
    private int music_id;
    private ArrayList<MusicCommnet> musicCommnets;


    public int getMusic_id() {
        return music_id;
    }

    public void setMusic_id(int music_id) {
        this.music_id = music_id;
    }

    public ArrayList<MusicCommnet> getMusicCommnets() {
        return musicCommnets;
    }

    public void setMusicCommnets(ArrayList<MusicCommnet> musicCommnets) {
        this.musicCommnets = musicCommnets;
    }

    @Override
    public String execute() throws Exception {
        musicCommnets=new ArrayList<MusicCommnet>();
        ResultSet rs=((Connection) ServletActionContext.getServletContext().getAttribute("DBCon")).createStatement().executeQuery("SELECT user_id,user_name,comment,create_time FROM music_comment WHERE music_id = "+music_id+"");
        while (rs.next()){
            musicCommnets.add(new MusicCommnet(rs.getString(2),rs.getString(3),rs.getLong(4)));
        }

        return SUCCESS;

    }
}