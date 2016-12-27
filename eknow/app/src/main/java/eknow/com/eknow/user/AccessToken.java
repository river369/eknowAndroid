package eknow.com.eknow.user;

/**
 * Created by jianguog on 16/12/27.
 */

public class AccessToken {
    long time;
    String token;
    long expireAfter;

    public AccessToken(long time, String token) {
        this.time = time;
        this.token = token;
        expireAfter = 7* 24 * 3600 * 1000;
    }

    public AccessToken(String line) {
        String[] array = line.split(",");
        this.time = Long.parseLong(array[0]);
        this.token = array[3];
        this.expireAfter = Long.parseLong(array[1]);
    }

    public boolean isTokenExpired() {
        long now = System.currentTimeMillis();
        if (time + expireAfter > now) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return time + "," + expireAfter + "," + Math.random() * 100 +"," + token + "," + Math.random() * 10000 ;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpireAfter() {
        return expireAfter;
    }

    public void setExpireAfter(long expireAfter) {
        this.expireAfter = expireAfter;
    }
}
