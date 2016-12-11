package eknow.com.eknow.common;

import android.util.Base64;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jianguog on 16/12/11.
 */

public abstract class RequestBuilderBase {
    Map<String, String> params = new HashMap<String, String>();
    Map<String, String> requestData = new HashMap<String, String>();
    public abstract String getMethod();

    public Map<String, String> buildRequestParameters(){
        params.put("method", getMethod());
        String dataString = Base64.encodeToString(new JSONObject(requestData).toString().getBytes(), Base64.DEFAULT);
        params.put("data", dataString);
        return params;
    }

    public Map<String, String> getRequestData() {
        return requestData;
    }
}
