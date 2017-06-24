package beans;

/**
 * Created by Jack on 2017-06-16.
 */
public class MusicCommnet {
    //private int user_id;
    private String user_name;
    private String comment;
    private long create_time;

    public MusicCommnet( String user_name, String comment, long create_time) {
        //this.user_id = user_id;
        this.user_name = user_name;
        this.comment = comment;
        this.create_time = create_time;
    }



    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }
}
