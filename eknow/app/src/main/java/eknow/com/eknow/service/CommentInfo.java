package eknow.com.eknow.service;

import java.io.Serializable;

/**
 * Created by jianguog on 16/12/21.
 */

public class CommentInfo implements Serializable {
    private String customerName;
    private String comments;
    private float stars;
    private String creation_date;

   // ,"comments":[{"id":"14","service_id":"57ed1cd444237515","order_id":"57edfd2e2aede674","customer_id":"57790fb728713607","seller_id":"579eacbd3447a903","customer_name":"北京欢迎您","seller_name":"文正","comments":"小哥很帅，人很热情。吃大螃蟹真过瘾。确是一次难忘的旅行。","stars":"5","creation_date":"2016-09-30 14:02:15","update_date":null}]

//    评论者
//
//            北京欢迎您
//
//    打分
//
//            非常满意
//
//    意见:小哥很帅，人很热情。吃大螃蟹真过瘾。确是一次难忘的旅行。
//
//    日期:2016-09-30


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    @Override
    public String toString() {
        return "CommentInfo{" +
                "customerName='" + customerName + '\'' +
                ", comments='" + comments + '\'' +
                ", stars=" + stars +
                ", creation_date='" + creation_date + '\'' +
                '}';
    }
}
