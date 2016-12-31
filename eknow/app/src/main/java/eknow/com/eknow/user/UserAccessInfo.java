package eknow.com.eknow.user;

import java.io.Serializable;

/**
 * Created by jianguog on 16/12/31.
 */

public class UserAccessInfo  implements Serializable {
    String userType;
    String phoneRegion;
    String phoneNumber;
    String password;
    String tempPasswrod;

    public UserAccessInfo(String userType, String phoneRegion, String phoneNumber, String password, String tempPasswrod) {
        this(userType, phoneRegion, phoneNumber, password);
        this.tempPasswrod = tempPasswrod;
    }

    public UserAccessInfo(String userType, String phoneRegion, String phoneNumber, String password) {
        this.userType = userType;
        this.phoneRegion = phoneRegion;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPhoneRegion() {
        return phoneRegion;
    }

    public void setPhoneRegion(String phoneRegion) {
        this.phoneRegion = phoneRegion;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTempPasswrod() {
        return tempPasswrod;
    }

    public void setTempPasswrod(String tempPasswrod) {
        this.tempPasswrod = tempPasswrod;
    }

    @Override
    public String toString() {
        return "UserAccessInfo{" +
                "userType='" + userType + '\'' +
                ", phoneRegion='" + phoneRegion + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", tempPasswrod='" + tempPasswrod + '\'' +
                '}';
    }
}
