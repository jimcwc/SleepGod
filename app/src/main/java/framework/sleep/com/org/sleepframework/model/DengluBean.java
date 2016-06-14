package framework.sleep.com.org.sleepframework.model;

import framework.sleep.com.org.sleepgod.allutils.JsonUtils;
import framework.sleep.com.org.sleepgod.request.RFEntityImp;

/**
 * Created by jimda on 2016/6/14.
 */
public class DengluBean implements RFEntityImp {
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String nickName;
    private String token;
    @Override
    public DengluBean newObject() {
        return new DengluBean();
    }

    @Override
    public void praseFromJson(JsonUtils jsonUtils) {
        nickName=jsonUtils.getString("nickName");
        token=jsonUtils.getString("token");
    }
}
