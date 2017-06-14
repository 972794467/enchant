package actions;

import beans.MusicItem;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SearchMusic extends ActionSupport {
    private String artist_name;
    private String music_name;
    private int ps;
    private int pn;
    private int count;
    ArrayList<MusicItem> musicList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<MusicItem> getMusicList() {
        return musicList;
    }

    public void setMusicList(ArrayList<MusicItem> musicList) {
        this.musicList = musicList;
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

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getMusic_name() {
        return music_name;
    }

    public void setMusic_name(String music_name) {
        this.music_name = music_name;
    }

    @Override
    public String execute() throws Exception {
        int limit = ps;
        int offset = (pn - 1) * ps;
        musicList=new ArrayList<MusicItem>();

        String querySQL="";
        if(artist_name==null || artist_name.equals(""))
             querySQL = "SELECT music_id,music_name,artist_name,music_file FROM music_info WHERE   music_name LIKE '"+music_name+"' limit "+limit+" offset "+offset+" ";
        if(music_name==null || music_name.equals(""))
             querySQL = "SELECT music_id,music_name,artist_name,music_file FROM music_info WHERE  artist_name like '"+artist_name+"' limit "+limit+" offset "+offset+" ";

        ServletContext sctx = ServletActionContext.getServletContext();
        Connection con = (Connection) sctx.getAttribute("DBCon");
        ResultSet rs;

        System.out.println(querySQL);
       rs=con.createStatement().executeQuery(querySQL);
       while (rs.next()){
           MusicItem music=new MusicItem(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
           musicList.add(music);
       }

        //String querySQL="";
        if(artist_name==null || artist_name.equals(""))
            querySQL = "SELECT COUNT (music_id) FROM music_info WHERE   music_name LIKE '"+music_name+"'  ";
        if(music_name==null || music_name.equals(""))
            querySQL = "SELECT COUNT (music_id) FROM music_info WHERE  artist_name like '"+artist_name+"'  ";

        rs=con.createStatement().executeQuery(querySQL);
        //count=0;
        if (rs.next()){
            count=rs.getInt(1);
        }
       return SUCCESS;
    }
}
