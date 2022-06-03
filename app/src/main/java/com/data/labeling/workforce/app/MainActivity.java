package com.data.labeling.workforce.app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.data.labeling.workforce.CallbackAdapter;
import com.data.labeling.workforce.Workforce;

public class MainActivity extends AppCompatActivity {

    private Workforce workforce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureWorkforce();

        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_show_offer).setOnClickListener(this::onShowOfferClick);
    }

    private void configureWorkforce(){
        workforce = Workforce.init(getApplicationContext(),"demo_code");
        workforce.setCallback(new CallbackAdapter() {
            @Override
            public void onReward() {
                Toast.makeText(MainActivity.this, "Congratulation", Toast.LENGTH_SHORT).show();
                Log.i("Application", "Congratulation");
            }

            @Override
            public void onFailTasking() {
                Toast.makeText(MainActivity.this, "Next time do it better", Toast.LENGTH_SHORT).show();
                Log.i("Application", "Next time do it better");
            }

            @Override
            public void onClose() {
                Log.i("Application", "Application continue working");
            }

            @Override
            public void onDisplay() {
                Log.i("Application", "Offer is opened");
            }

            @Override
            public void onFail(Throwable error) {
                Log.e("Application", "Something wrong with offer", error);
            }
        });
    }

    public void onShowOfferClick(View v) {
        workforce.showOffer();
    }
}