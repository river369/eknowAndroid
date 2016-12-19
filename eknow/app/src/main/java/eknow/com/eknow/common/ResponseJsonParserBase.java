package eknow.com.eknow.common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jianguog on 16/12/11.
 */

public abstract class ResponseJsonParserBase {
    JSONObject response;
    int code;
    String msg;
    JSONArray data;
    String dataTag;

    public ResponseJsonParserBase(JSONObject response) throws EknowException {
        this.response = response;
        try {
            this.code = response.getInt("code");
            this.msg = response.getString("msg");
            //this.data = response.getJSONObject("data").getJSONArray(dataTag);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new EknowException(
                    "Fail to parse data from service as [" + response.toString() +
                            "]. error is [" + e.getMessage() + "]");
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

    public void setDataTag(String dataTag) {
        this.dataTag = dataTag;
    }

    public JSONArray getData() throws EknowException{
        if (this.data == null) {
            try {
                this.data = response.getJSONObject("data").getJSONArray(dataTag);
            } catch (JSONException e) {
                e.printStackTrace();
                throw new EknowException(
                        "Fail to parse data from service as [" + response.toString() +
                                "]. error is [" + e.getMessage() + "]");
            }
        }
        return this.data;
    }
    public void setData(JSONArray data) {
        this.data = data;
    }
}
