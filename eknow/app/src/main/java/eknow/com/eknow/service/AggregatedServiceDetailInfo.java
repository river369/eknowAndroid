package eknow.com.eknow.service;

import java.util.ArrayList;
import java.util.List;

import eknow.com.eknow.user.UserInfo;

/**
 * Created by jianguog on 16/12/21.
 */

public class AggregatedServiceDetailInfo {
    UserInfo seller;
    List<CommentInfo> comments;

    public AggregatedServiceDetailInfo(UserInfo seller, List<CommentInfo> comments) {
        this.seller = seller;
        this.comments = comments;
    }

    public UserInfo getSeller() {
        return seller;
    }

    public void setSeller(UserInfo seller) {
        this.seller = seller;
    }

    public List<CommentInfo> getComments() {
        return comments;
    }

    public void setComments(List<CommentInfo> comments) {
        this.comments = comments;
    }
}
