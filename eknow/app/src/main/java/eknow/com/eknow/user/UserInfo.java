package eknow.com.eknow.user;

import java.io.Serializable;

/**
 * Created by jianguog on 16/12/21.
 */

public class UserInfo implements Serializable {

    private String userId;
    private String name;
    private String signature;
    private String gender;
    private String weixin;
    private String email;
    private String description;
    private float stars;
    private int serve_count;
    private String tag;
    private String status;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }

    public int getServe_count() {
        return serve_count;
    }

    public void setServe_count(int serve_count) {
        this.serve_count = serve_count;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", signature='" + signature + '\'' +
                ", gender='" + gender + '\'' +
                ", weixin='" + weixin + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", stars='" + stars + '\'' +
                ", serve_count='" + serve_count + '\'' +
                ", tag='" + tag + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
