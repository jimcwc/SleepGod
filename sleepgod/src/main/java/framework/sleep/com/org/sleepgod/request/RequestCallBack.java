package framework.sleep.com.org.sleepgod.request;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

import framework.sleep.com.org.sleepgod.allutils.JsonUtils;
import framework.sleep.com.org.sleepgod.allutils.UHelper;

/**
 * 请求回调CallBack
 * Created  on 2014/10/13.
 */
public abstract class RequestCallBack extends AsyncHttpResponseHandler {

    public static final String RES_DATA = "data";
    public static final String RES_DATA2 = "data2";
    private static final String TAG = "RequestCallBack";
    /**
     * token 失效
     */
    private static int RES_CODE_303 = 303;

    private Context mContext;

    public RequestCallBack(Context context) {
        this.mContext = context;
    }

    @Override
    public void onStart() {
        super.onStart();
        onStart(getRequestURI().toString());
    }

    @Override
    public void onProgress(int bytesWritten, int totalSize) {
        super.onProgress(bytesWritten, totalSize);
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        String charset = getCharset();
        String response = "";
        if (responseBody != null) {
            try {

                response = new String(responseBody, charset);

            } catch (UnsupportedEncodingException e) {
                UHelper.showToast(mContext, "服务器异常");
                response = new String(responseBody);
            }
        }
        debugResponse(response);
//        debugHeaders(headers);

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response);
        } catch (JSONException e) {
            jsonObject = new JSONObject();
        }
        JsonUtils jsonUtils = new JsonUtils(jsonObject);


        //token 校验
//       boolean enable =  checkToken(jsonUtils);
//        if (enable){
//            onSuccess(statusCode, headers, jsonUtils);
//        }
        onSuccess(statusCode, headers, jsonUtils);
    }


    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        String response = "";
        if (responseBody != null) {
            response = new String(responseBody);
        }

        debugResponse(response);
        onFailure(statusCode, headers, response, error);
    }

    public abstract void onStart(String uri);

    public abstract void onSuccess(int statusCode, Header[] headers, JsonUtils response);

    public abstract void onFailure(int statusCode, Header[] headers, String response, Throwable error);

    /**
     * 检查token是否有效
     */
    private boolean checkToken(JsonUtils jsonUtils) {
        if (jsonUtils == null || mContext == null) {
            return true;
        }
        if ((mContext instanceof Activity) && (!((Activity) mContext).isFinishing())) {
            if (jsonUtils.getCode() != RequestCallBack.RES_CODE_303) {
                return true;
            }
            UHelper.showToast(mContext,"你的登录信息已经过期，请重新登录!");
            //清除登陆信息
//            ((ClientApp) ((Activity) mContext).getApplication()).clearLoginUser();
//
//            //跳转到登陆界面
//            Intent intent = new Intent(mContext, DengluPageActivity.class);
//            intent.putExtra("isTokenError", true);
//            mContext.startActivity(intent);

            //关闭当前activity
//            ((Activity) mContext).finish();

            return false;
        }else{
            return true;
        }

    }

    /**
     * 打印头部信息
     *
     * @param headers
     */
    private void debugHeaders(Header[] headers) {

        if (headers != null) {
            Log.d(TAG, "Return Headers:");
            StringBuilder builder = new StringBuilder();
            for (Header h : headers) {
                String _h = String.format(Locale.CHINESE, "%s : %s", h.getName(), h.getValue());
                Log.d(TAG, _h);
                builder.append(_h);
                builder.append("\n");
            }

        }
    }

    /**
     * 打印响应信息
     *
     * @param responseString
     */
    private void debugResponse(String responseString) {
       // if (Constant.DEBUG){
//            Log.i("", "-----------------------------------------------------------------------------");
//            Log.d(TAG + "__Request:", getRequestURI().toString());
//            Log.d(TAG + "__Response:", decodeUnicode(responseString));
//            Log.i("", "-----------------------------------------------------------------------------");
      //  }

    }

    /**
     * Unicode 转换为UTF-8
     *
     * @param string
     * @return
     */
    private String decodeUnicode(String string) {
        char aChar;
        int len = string.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = string.charAt(x++);
            if (aChar == '\\') {
                aChar = string.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = string.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                return string;
                        }

                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }
}
