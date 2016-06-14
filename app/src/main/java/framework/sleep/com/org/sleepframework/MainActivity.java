package framework.sleep.com.org.sleepframework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import framework.sleep.com.org.sleepframework.activity.ExampleRequst;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button mRequest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
    }

    private void assignViews() {
        mRequest = (Button) findViewById(R.id.request);
        mRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.request:{
                Intent intent =new Intent(this,ExampleRequst.class);
                startActivity(intent);
            }break;
        }
    }
}
