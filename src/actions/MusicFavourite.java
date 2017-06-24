package actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import redis.clients.jedis.Jedis;

public class MusicFavourite extends ActionSupport {
    private String id;
    private String music_id;
    private String favor;
    private String artist;
    private String path;
    private String title;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    private int STATUS;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMusic_id() {
        return music_id;
    }

    public void setMusic_id(String music_id) {
        this.music_id = music_id;
    }

    public String getFavor() {
        return favor;
    }

    public void setFavor(String favor) {
        this.favor = favor;
    }



    public int getSTATUS() {
        return STATUS;
    }



    @Override
    public String execute() throws Exception {
        Jedis jedis=(Jedis)ServletActionContext.getServletContext().getAttribute("Jedis");
        System.out.println(favor);
        if(favor.equals("1") ){
            System.out.println(music_id+";"+path+";"+artist+";"+title);
            jedis.sadd(id,music_id+";"+path+";"+artist+";"+title);

            System.out.println("add"+music_id+";"+path+";"+artist+";"+title);
        }
        else {
            System.out.println("del"+music_id+";"+path+";"+artist+";"+title);
            jedis.srem(id,music_id+";"+path+";"+artist+";"+title);
        }
        //System.out.println("hello");
        STATUS=1000;
        return SUCCESS;
    }
}