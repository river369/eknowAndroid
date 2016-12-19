package eknow.com.eknow.common;

/**
 * Created by jianguog on 16/12/19.
 */

public class EknowException extends Exception {
    public EknowException(String message) {        //用来创建指定参数对象
        super(message);                             //调用超类构造器
    }
}
