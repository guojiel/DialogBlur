package com.guojiel.dialogblur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.guojiel.library.BlurDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BlurDialog(MainActivity.this){
                    @Override
                    protected void onCreate(Bundle savedInstanceState) {
                        super.onCreate(savedInstanceState);
                        TextView tv = new TextView(MainActivity.this);
                        tv.setText("Dialog");
                        tv.setGravity(Gravity.CENTER);
                        tv.setBackgroundColor(0x66FFFFFF);
                        setContentView(tv, new ViewGroup.LayoutParams(600, 360));

                        WindowManager.LayoutParams lp = getWindow().getAttributes();
                        lp.dimAmount = 0f;
                    }
                }.show();
            }
        });
    }
}
