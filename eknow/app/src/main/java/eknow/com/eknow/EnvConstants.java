package eknow.com.eknow;

/**
 * Created by jianguog on 16/12/6.
 */

public class EnvConstants {

    public static String BASE_URL = "http://www.clcentury.com/";
    public static String API_URL = BASE_URL + "weiphp/Addons/OverSea/Controller/APIDispatcher.php";
//      public static String BASE_URL = "http://api.androidhive.info/";
//      public static String API_URL = BASE_URL + "volley/person_object.json";

    public static String OSS_BASE_URL = "http://oss.clcentury.com/";
    public static String OSS_UPLOAD_URL = "http://clcentury.oss-cn-beijing.aliyuncs.com/";
    public static String OSS_CLCENTURY_BUCKET = "clcentury";
    public static String OSS_PIC_OBJ_PREFIX = "yzphoto/pics/";
    public static String SERVIC_PIC_URL = OSS_BASE_URL + OSS_PIC_OBJ_PREFIX;



    public static String getServicesPictureObjPrefix (String sellerId, String serviceId){
        return EnvConstants.OSS_PIC_OBJ_PREFIX + sellerId + "/" + serviceId;
    }
    public static String getServicesPictureURL (String sellerId, String serviceId){
        return EnvConstants.SERVIC_PIC_URL + sellerId + "/" + serviceId;
    }
    public static String getServicesMainPictureURL(String sellerId, String serviceId){
        return getServicesPictureURL(sellerId, serviceId) + "/main.png";
    }

}
