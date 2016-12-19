package com.jinlin.adapter_helper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_lv, R.id.btn_rv, R.id.btn_lv_binding, R.id.btn_rv_binding})
    public void forward(View view) {
        switch (view.getId()) {
            case R.id.btn_lv:
                startActivity(new Intent(this, ListViewActivity.class));
                break;
            case R.id.btn_rv:
                startActivity(new Intent(this, RecyclerViewActivity.class));
                break;
            case R.id.btn_lv_binding:
                startActivity(new Intent(this, ListViewBingdingActivity.class));
                break;
            case R.id.btn_rv_binding:
                startActivity(new Intent(this, RecyclerViewBindingActivity.class));
                break;
        }
    }

}
