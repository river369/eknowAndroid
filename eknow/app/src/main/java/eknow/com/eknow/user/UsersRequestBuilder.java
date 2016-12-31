package eknow.com.eknow.user;

import java.util.Map;

import eknow.com.eknow.common.RequestBuilderBase;
import eknow.com.eknow.user.UserAccessInfo;
/**
 * Created by jianguog on 16/12/11.
 */

public class UsersRequestBuilder extends RequestBuilderBase {

    public UsersRequestBuilder() {
    }

    public Map<String, String> buildSignInRequestParameters(UserAccessInfo userAccessInfo){
        getRequestData().put("userType", userAccessInfo.getUserType());
        getRequestData().put("phoneRegion", userAccessInfo.getPhoneRegion());
        getRequestData().put("phoneNumber", userAccessInfo.getPhoneNumber());
        getRequestData().put("password", userAccessInfo.getPassword());
        setMethod("signIn");
        return buildRequestParameters();
    }

}
