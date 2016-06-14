package framework.sleep.com.org.sleepgod.request;

import android.content.Context;

import framework.sleep.com.org.sleepgod.allutils.UHelper;


/**
 * 所有请求的 参数都需要从此出获取
 * Created  on 2014/10/13.
 */
public class RequestParamsBuilder {

    /**
     * 所有请求的 参数都需要从此出获取
     * 初始化就保存了一些通用的值，不需要每一个请求中又设置一次
     *
     * @param context
     * @return
     */
    public static RFRequestParams buildRequestParams(Context context) {
        Context mContext = context.getApplicationContext();
        RFRequestParams params = new RFRequestParams();
        params.setContentEncoding("UTF-8");
        params.put("deviceNum", UHelper.getUniqueID(mContext));
        params.put("deviceType", 1);
        params.put("systype", "android");
        params.put("versionCode", UHelper.getAppVersionInfo(context, UHelper.TYPE_VERSION_CODE));
//        User user = SessionManager.getInstance().getUser();
//        if (user != null) {
//            String token = user.getToken();
//            if ((!TextUtils.isEmpty(token)) && (!token.equals("null"))) {
//                params.put("token", token);
//            }
//        }
        return params;
    }
}
