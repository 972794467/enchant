package actions;

import beans.FavoriteMusicItem;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class GetFavouriteMusic extends ActionSupport {
    private String id;
    private Set<String> favouriteList;
    private ArrayList<FavoriteMusicItem> favoriteMusic;

    public ArrayList<FavoriteMusicItem> getFavoriteMusic() {
        return favoriteMusic;
    }

    public void setFavoriteMusic(ArrayList<FavoriteMusicItem> favoriteMusic) {
        this.favoriteMusic = favoriteMusic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<String> getFavouriteList() {
        return favouriteList;
    }

    public void setFavouriteList(Set<String> favouriteList) {
        this.favouriteList = favouriteList;
    }

    @Override
    public String execute() throws Exception {
        Jedis jedis= (Jedis)ServletActionContext.getServletContext().getAttribute("Jedis");
        favouriteList=jedis.smembers(id);
        favoriteMusic=new ArrayList<FavoriteMusicItem>();
        Iterator<String> iterator=favouriteList.iterator();

        System.out.println(favouriteList.isEmpty()+" "+favouriteList.size());
        String []item;
        while (iterator.hasNext()){
            item=iterator.next().split(";");
            System.out.println(item[3]);
            favoriteMusic.add(new FavoriteMusicItem(item[0],item[1],item[2],item[3]));
        }
        return SUCCESS;
    }
}