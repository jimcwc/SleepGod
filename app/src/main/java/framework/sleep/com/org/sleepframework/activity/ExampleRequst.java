package framework.sleep.com.org.sleepframework.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.Header;

import framework.sleep.com.org.sleepframework.R;
import framework.sleep.com.org.sleepframework.model.DengluBean;
import framework.sleep.com.org.sleepgod.allutils.JsonUtils;
import framework.sleep.com.org.sleepgod.allutils.UHelper;
import framework.sleep.com.org.sleepgod.request.RFAsyncHttpClient;
import framework.sleep.com.org.sleepgod.request.RFRequestParams;
import framework.sleep.com.org.sleepgod.request.RequestCallBack;
import framework.sleep.com.org.sleepgod.request.RequestParamsBuilder;

/**
 * Created by jimda on 2016/6/14.
 */
public class ExampleRequst extends Activity {
    private Button mSend;
    private TextView mTextView;
    protected RFAsyncHttpClient mHttpClient = new RFAsyncHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requestlayout);
        mSend = (Button) findViewById(R.id.send);
        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postRequest("150215xxxxx", "123456");
            }
        });
        mTextView = (TextView) findViewById(R.id.request_result);

    }

    /**
     * 发送请求
     */
    private void postRequest(final String userName, String password) {
        //发送请求方法
        RFRequestParams params = RequestParamsBuilder.buildRequestParams(this);
        params.put("userName", userName);
        params.put("password", password);
        params.put("versionCode", UHelper.getAppVersionInfo(ExampleRequst.this, UHelper.TYPE_VERSION_CODE));
        params.put("deviceType", 1);
        Log.d("login", "userName: " + userName);
        Log.d("login", "password: " + password);
        Log.d("login", "versionCode: " + UHelper.getAppVersionInfo(ExampleRequst.this, UHelper.TYPE_VERSION_CODE));


        mHttpClient.post(this,"http://xxx.xx.xx/xx/user/login2", params, new RequestCallBack(this) {
            @Override
            public void onStart(String uri) {

            }



            @Override
            public void onSuccess(int statusCode, Header[] headers, JsonUtils response) {

                if (response.getCode() == 100) {

                    DengluBean bean = response.getEntity(RES_DATA, new DengluBean());
                    if (bean != null) {
                        mTextView.setText("bean getToken: "+bean.getToken());
                    }


                } else {
                    UHelper.showToast(ExampleRequst.this, response.getMsg());
                    mTextView.setText("response.getMsg(): "+response.getMsg());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String response, Throwable error) {

                UHelper.showToast(ExampleRequst.this, "网络异常");
                mTextView.setText("网络异常response: " + response);

            }
        });
    }
}
