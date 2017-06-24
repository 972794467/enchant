package listeners;

import redis.clients.jedis.Jedis;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.File;

/**
 * Application Lifecycle Listener implementation class InitServer
 */
@WebListener
public class InitServer implements ServletContextListener {

    /**
     * Default constructor.
     */
    public InitServer() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
        String driver;
        String url;
        String user;
        String password;
        Connection conn;
        ServletContext sctx = arg0.getServletContext();
        try {
            driver = "org.mariadb.jdbc.Driver";
            url = "jdbc:mariadb://localhost:3306/EServer";
            user = "root";
            password = "root";

            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            sctx.setAttribute("DBCon", conn);
            System.out.println("数据库连接成功！");

        } catch (ClassNotFoundException classnotfoundexception) {
            classnotfoundexception.printStackTrace();
            System.err.println("db: " + classnotfoundexception.getMessage());
        } catch (SQLException sqlexception) {
            System.err.println("db.getconn(): " + sqlexception.getMessage());
        }
        Jedis jedis = new Jedis("localhost");
        System.out.println(jedis.ping());
        sctx.setAttribute("Jedis", jedis);
        System.out.println("Jedis conneected");

        String dirName = "pictures";
        System.out.println("路径" + sctx.getContextPath());
        String dirPath;
        dirPath = sctx.getRealPath(dirName);

        String avatarPath = sctx.getRealPath("avatars");
        String musicPath = sctx.getRealPath("music");
        String coverPath = sctx.getRealPath("cover");
        String lrcPath = sctx.getRealPath("lrc");
        File adir = new File(avatarPath);
        File mdir = new File(musicPath);
        File cdir = new File(coverPath);
        File ldir = new File(lrcPath);
        if (!mdir.exists() && !mdir.isDirectory()) {
            //目录不存在，创建目录
            mdir.mkdir();
        }
        if (!adir.exists() && !adir.isDirectory()) {
            //目录不存在，创建目录
            adir.mkdir();
        }
        if (!cdir.exists() && !cdir.isDirectory()) {
            //目录不存在，创建目录
            cdir.mkdir();
        }
        if (!ldir.exists() && !ldir.isDirectory()) {
            //目录不存在，创建目录
            ldir.mkdir();
        }
        sctx.setAttribute("MusicPath", mdir.getAbsolutePath());
        sctx.setAttribute("AvatarsPath", adir.getAbsolutePath());
        sctx.setAttribute("LrcPath", ldir.getAbsolutePath());
        sctx.setAttribute("CoverPath", cdir.getAbsolutePath());
    }

    /**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }

}
