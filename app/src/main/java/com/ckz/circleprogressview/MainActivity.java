package com.ckz.circleprogressview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ckz.circleprogress.CircleProgressView;

public class MainActivity extends AppCompatActivity {

    private CircleProgressView mShowProgress;
    private TextView mShowText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mShowProgress = (CircleProgressView) findViewById(R.id.show_progress);
        mShowProgress.setProgress(50f);
        mShowText = (TextView) findViewById(R.id.show_text);
        mShowText.setText("显示进度条");
        mShowText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShowProgress.setEnableAni(true);
                mShowProgress.setProgress(95f);
            }
        });
    }
}
