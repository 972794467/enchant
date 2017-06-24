package beans;

/**
 * Created by Jack on 2017-06-18.
 */
public class FavoriteMusicItem {
    private String music_id;
    private String artist;
    private String path;
    private String title;

    public FavoriteMusicItem(String music_id, String path,String artist_name,  String title) {
        this.music_id = music_id;
        this.artist = artist_name;
        this.path = path;
        this.title = title;
    }

    public String getMusic_id() {
        return music_id;
    }

    public void setMusic_id(String music_id) {
        this.music_id = music_id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
