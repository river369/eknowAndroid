package eknow.com.eknow.utils;

import android.content.Context;
import android.content.SharedPreferences;

import eknow.com.eknow.KeyConstants;
import eknow.com.eknow.MainActivity;
import eknow.com.eknow.common.EknowException;
import eknow.com.eknow.user.AccessToken;

/**
 * Created by jianguog on 16/12/27.
 */

public class SharedPreferenceUtil {
    public static SharedPreferences preferences;

    public static String getToken() {
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

    public static void setToken(String token, String currentPhone) {
        if (preferences == null) {
            return;
        }
        SharedPreferences.Editor editor=preferences.edit();
        AccessToken accessToken = new AccessToken(System.currentTimeMillis(), token);
        editor.putString(KeyConstants.sharedPreferenceCurrentPhone, currentPhone);
        editor.putString(currentPhone, accessToken.toString());
        editor.commit();
    }
    
}
