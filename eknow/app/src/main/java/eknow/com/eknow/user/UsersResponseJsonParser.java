package eknow.com.eknow.user;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import eknow.com.eknow.EnvConstants;
import eknow.com.eknow.common.EknowException;
import eknow.com.eknow.common.ResponseJsonParserBase;
import eknow.com.eknow.user.UserAccessInfo;

/**
 * Created by jianguog on 16/12/11.
 */

public class UsersResponseJsonParser extends ResponseJsonParserBase {

    public UsersResponseJsonParser(JSONObject response) throws EknowException {
        super(response);
    }

    EknowException reportError(String method, String messages){
        return new EknowException("Fail to parse user data in [" + method + "]. error is [" + messages + "]");
    }

    /**
     * get the service info, currently not used since it has already be got from list.
     * May be can be used in future.
     *
     * @return
     * @throws EknowException
     */
    public String getToken() throws EknowException {
        try {
            String token = getDataValueByKey("token");
            return token;
        } catch (EknowException e) {
            e.printStackTrace();
            throw reportError(Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
        }
    }
}
