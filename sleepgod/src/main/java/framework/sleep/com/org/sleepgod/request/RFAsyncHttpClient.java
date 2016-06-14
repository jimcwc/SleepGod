package framework.sleep.com.org.sleepgod.request;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import framework.sleep.com.org.sleepgod.allutils.DeviceUtils;


/**
 * Created  on 2014/10/22.
 */
public class RFAsyncHttpClient extends AsyncHttpClient {

    public RFAsyncHttpClient() {
        String userAgent = "deviceModel:" + DeviceUtils.getDeviceModel()
                + "|android sdkVersion:" + DeviceUtils.getSDKVersionInt();
//                + "|IP:" + ClientApp.ip;
        this.setUserAgent(userAgent);
    }

    /**
     * POST 方式请求
     *
     * @param context         上下文
     * @param url             接口地址
     * @param params          请求参数 详见该类：（{@link RequestParamsBuilder}）
     * @param responseHandler 回调 使用（{@link RequestCallBack}）
     * @return
     */
    @Override
    public RequestHandle post(Context context, String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        printfRequestParams(url, params);
        return super.post(context, url, params, responseHandler);
    }

    /**
     * GET 方式请求(无参)
     *
     * @param context         上下文
     * @param url             接口地址
     * @param responseHandler 回调 使用（{@link RequestCallBack}）
     * @return
     */
    @Override
    public RequestHandle get(Context context, String url, ResponseHandlerInterface responseHandler) {
        return super.get(context, url, responseHandler);
    }

    /**
     * GET 方式请求
     *
     * @param context         上下文
     * @param url             接口地址
     * @param params          请求参数 详见该类：（{@link RequestParamsBuilder}）
     * @param responseHandler 回调 使用（{@link RequestCallBack}）
     * @return
     */
    @Override
    public RequestHandle get(Context context, String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        printfRequestParams(url, params);
        return super.get(context, url, params, responseHandler);
    }


    /**
     * @param params
     */
    private void printfRequestParams(String url, RequestParams params) {
            if (params instanceof RFRequestParams) {
                final String paramsString = ((RFRequestParams) params).getParamString();
                Log.i("", "-----------------------------------------------------------------------------");
                Log.d("RequestURL", url);
                Log.d("RequestParams", paramsString);
                Log.i("", "-----------------------------------------------------------------------------");
            }
        }
}
