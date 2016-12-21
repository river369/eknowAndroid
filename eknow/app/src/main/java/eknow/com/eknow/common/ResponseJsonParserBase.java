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
    JSONArray dataArray;
    JSONObject dataObject;
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

    public boolean isTagExisted() throws EknowException{
        try {
            if (code != 0) {
                return false;
            }
            JSONObject jo = response.getJSONObject("data");
            return jo.has(dataTag);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new EknowException(
                    "Fail to parse data from service as [" + response.toString() +
                            "]. error is [" + e.getMessage() + "]");
        }
    }
    public JSONArray getDataArray() throws EknowException{
        if (this.dataArray == null) {
            try {
                this.dataArray = response.getJSONObject("data").getJSONArray(dataTag);
            } catch (JSONException e) {
                e.printStackTrace();
                throw new EknowException(
                        "Fail to parse data from service as [" + response.toString() +
                                "]. error is [" + e.getMessage() + "]");
            }
        }
        return this.dataArray;
    }

    public JSONObject getDataObject() throws EknowException{
        if (this.dataObject == null) {
            try {
                this.dataObject = response.getJSONObject("data").getJSONObject(dataTag);
            } catch (JSONException e) {
                e.printStackTrace();
                throw new EknowException(
                        "Fail to parse data from service as [" + response.toString() +
                                "]. error is [" + e.getMessage() + "]");
            }
        }
        return this.dataObject;
    }
}
