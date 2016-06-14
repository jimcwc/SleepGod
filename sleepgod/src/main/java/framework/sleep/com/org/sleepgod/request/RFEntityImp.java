package framework.sleep.com.org.sleepgod.request;


import java.io.Serializable;

import framework.sleep.com.org.sleepgod.allutils.JsonUtils;

/**
 * Created by wutong on 2014/10/10.
 */
public interface RFEntityImp extends Serializable {
    <T extends RFEntityImp> T newObject();

    void praseFromJson(JsonUtils jsonUtils);
}
