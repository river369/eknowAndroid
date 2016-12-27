package eknow.com.eknow.user;

import android.content.Context;
import android.content.SharedPreferences;

import eknow.com.eknow.KeyConstants;
import eknow.com.eknow.MainActivity;
import eknow.com.eknow.common.EknowException;

/**
 * Created by jianguog on 16/12/27.
 */

public class AccessUtil {
    public static String getToken(SharedPreferences preferences) {
        if (preferences == null) {
           return null;
        }
        String currentPhone = preferences.getString(KeyConstants.sharedPreferenceCurrentPhone, "");
        if (currentPhone == null || currentPhone.equalsIgnoreCase("")){
            return null;
        }
        String tokenLine = preferences.getString(currentPhone, "");
        if (tokenLine == null || tokenLine.equalsIgnoreCase("")){
            return null;
        }
        AccessToken accessToken = new AccessToken(tokenLine);
        if (!accessToken.isTokenExpired()){
            String token = accessToken.getToken();
            if (token == null || token.equalsIgnoreCase("")){
                return null;
            }
            return accessToken.getToken();
        }
        return null;
    }
}
