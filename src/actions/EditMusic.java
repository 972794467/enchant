package actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class EditMusic extends ActionSupport implements status {
    private String musicName;
    private String musicArtistName;
    private String musicDuration;
    private String musicQuality;
    private File musicFile;
    private String musicFileFileName;
    private File musicCoverFile;
    private String musicCoverFileFileName;
    private File musicLyricFile;
    private String musicLyricFileFileName;
    private String musicAlbum;
    private int music_id;
    private int STATUS;



    public int getMusic_id() {
        return music_id;
    }

    public void setMusic_id(int music_id) {
        this.music_id = music_id;
    }

    public String getMusicFileFileName() {
        return musicFileFileName;
    }

    public void setMusicFileFileName(String musicFileFileName) {
        this.musicFileFileName = musicFileFileName;
    }

    public String getMusicCoverFileFileName() {
        return musicCoverFileFileName;
    }

    public void setMusicCoverFileFileName(String musicCoverFileFileName) {
        this.musicCoverFileFileName = musicCoverFileFileName;
    }

    public String getMusicLyricFileFileName() {
        return musicLyricFileFileName;
    }

    public void setMusicLyricFileFileName(String musicLyricFileFileName) {
        this.musicLyricFileFileName = musicLyricFileFileName;
    }



    public int getSTATUS() {
        return STATUS;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMusicArtistName() {
        return musicArtistName;
    }

    public void setMusicArtistName(String musicArtistName) {
        this.musicArtistName = musicArtistName;
    }

    public String getMusicDuration() {
        return musicDuration;
    }

    public void setMusicDuration(String musicDuration) {
        this.musicDuration = musicDuration;
    }

    public String getMusicQuality() {
        return musicQuality;
    }

    public void setMusicQuality(String musicQuality) {
        this.musicQuality = musicQuality;
    }

    public File getMusicFile() {
        return musicFile;
    }

    public void setMusicFile(File musicFile) {
        this.musicFile = musicFile;
    }

    public File getMusicCoverFile() {
        return musicCoverFile;
    }

    public void setMusicCoverFile(File musicCoverFile) {
        this.musicCoverFile = musicCoverFile;
    }

    public File getMusicLyricFile() {
        return musicLyricFile;
    }

    public void setMusicLyricFile(File musicLyricFile) {
        this.musicLyricFile = musicLyricFile;
    }

    public String getMusicAlbum() {
        return musicAlbum;
    }

    public void setMusicAlbum(String musicAlbum) {
        this.musicAlbum = musicAlbum;
    }

    @Override
    public String execute() throws Exception {
        System.out.println(musicName+"\n"+musicFile.toString()+"\n "+musicCoverFileFileName);
        ServletContext sctx = ServletActionContext.getServletContext();
        HttpSession session = ServletActionContext.getRequest().getSession();
        String uagent=ServletActionContext.getRequest().getHeader("User-Agent");

        System.out.println(musicName+"\n "+musicCoverFileFileName+" \n"+musicFile.toString());
        if( session.getAttribute("admin")==null || !((String)session.getAttribute("admin")).equals("yes")){
            STATUS=NO_PRIVILEGES;

            if(uagent.matches(".*AppleWebKit.*") || uagent.matches(".*Mozilla.*") || uagent.matches(".*Chrome.*") ){
                ServletActionContext.getResponse().getWriter().print("<script>alert('NO_PRIVILEGES!');window.location.href='/enchant/enchantAdmin/index.jsp';</script>");
                return null;
            }
            return ERROR;
        }

        Connection con = (Connection) sctx.getAttribute("DBCon");
        con.createStatement().executeUpdate("DELETE FROM music_info WHERE music_id = "+music_id+" ");
        String insertSql = "INSERT INTO music_info (music_name,user_id,artist_name,album,cover_file,quality,duration,music_file,lyric_file,music_type,ext_info,create_time,update_time,music_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstat = con.prepareStatement(insertSql);

        pstat.setString(1,musicName);
        pstat.setInt(2,(int)session.getAttribute("user_id"));
        //pstat.setInt(2,2333);
        pstat.setString(3,musicArtistName);
        pstat.setString(4,musicAlbum);

        File saveFile;
        String saveFileName;

        saveFileName=musicCoverFile.getName().substring(0,musicCoverFile.getName().length()-4)+"_"+musicCoverFileFileName;
        saveFile=new File((String)sctx.getAttribute("CoverPath"),saveFileName);
        FileUtils.copyFile(musicCoverFile,saveFile);
        pstat.setString(5,saveFileName);

        System.out.println(musicQuality);
        int quality=-1;
        char [] dst=new char[1];
        musicQuality.getChars(0,1,dst,0);
        System.out.println(dst);
        switch (dst[0]){
            case '普':quality=0;break;
            case '较':quality=1;break;
            case '极':quality=2;break;
            case '无':quality=3;break;
            default:quality=-2;
        }
        pstat.setInt(6,quality);

        pstat.setInt(7,Integer.parseInt(musicDuration.split(":")[0])*60+Integer.parseInt(musicDuration.split(":")[1]));

        saveFileName=musicFile.getName().substring(0,musicFile.getName().length()-4)+"_"+musicFileFileName;
        saveFile=new File((String)sctx.getAttribute("MusicPath"),saveFileName);
        FileUtils.copyFile(musicCoverFile,saveFile);
        pstat.setString(8,saveFileName);

        saveFileName=musicLyricFile.getName().substring(0,musicLyricFile.getName().length()-4)+"_"+musicLyricFileFileName;
        System.out.println((String)sctx.getAttribute("LrcPath"));
        saveFile=new File((String)sctx.getAttribute("LrcPath"),saveFileName);
        FileUtils.copyFile(musicLyricFile,saveFile);
        pstat.setString(9,saveFileName);

        pstat.setInt(10,0);
        pstat.setString(11,"");
        pstat.setLong(12,System.currentTimeMillis() / 1000);
        pstat.setLong(13,System.currentTimeMillis() / 1000);
        pstat.setInt(14,music_id);
        System.out.println(pstat.toString());
        System.out.println(pstat.toString());
        pstat.execute();

        System.out.println(uagent);
        if(uagent.matches(".*AppleWebKit.*") || uagent.matches(".*Mozilla.*") || uagent.matches(".*Chrome.*") ){
            ServletActionContext.getResponse().getWriter().print("<script>alert('Edit Success!');window.location.href='/enchant/enchantAdmin/index.jsp';</script>");
            return null;
        }
        STATUS=1000;
        return SUCCESS;

    }
}