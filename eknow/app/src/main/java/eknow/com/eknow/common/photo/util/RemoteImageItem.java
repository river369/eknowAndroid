package eknow.com.eknow.common.photo.util;

/**
 * Created by jianguog on 17/1/12.
 */

public class RemoteImageItem {
    String ossObj;
    String localPath;

    public String getOssObj() {
        return ossObj;
    }

    public void setOssObj(String ossObj) {
        this.ossObj = ossObj;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public RemoteImageItem(String ossObj, String localPath) {
        this.ossObj = ossObj;
        this.localPath = localPath;
    }
}
