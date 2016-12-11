package eknow.com.eknow.common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jianguog on 16/12/11.
 */

public abstract class ResponseJsonParserBase {
    //JSONObject response;
    int code;
    String msg;
    JSONArray data;

    public abstract String getDataType();

    public ResponseJsonParserBase(JSONObject response) {
        //this.response = response;
        try {
            this.code = response.getInt("code");
            this.msg = response.getString("msg");
            this.data = response.getJSONObject("data").getJSONArray(getDataType());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JSONArray getData() {
        return data;
    }
    public void setData(JSONArray data) {
        this.data = data;
    }
}
