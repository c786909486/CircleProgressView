package com.ckz.circleprogressview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ckz.circleprogress.CircleProgressView;

public class MainActivity extends AppCompatActivity {

    private CircleProgressView mShowProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mShowProgress = (CircleProgressView) findViewById(R.id.show_progress);
        mShowProgress.setProgress(50f);
    }
}
