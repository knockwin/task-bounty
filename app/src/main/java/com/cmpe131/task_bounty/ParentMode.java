package com.cmpe131.task_bounty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ParentMode extends AppCompatActivity implements TaskHub {


    Button CreatePin;
    Button DeleteParent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parentmode);

        CreatePin = findViewById(R.id.buttonStartPin);
        DeleteParent = findViewById(R.id.buttonDeleteParent);

        CreatePin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(parentPin.size() < 1) {
                    startActivity(new Intent(ParentMode.this, CreateParentActivity.class));
                }
                else {
                    Context context = getApplicationContext();
                    CharSequence text = "There is already a pin created";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }

            }
        });

        DeleteParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Options.add(3);
                if(parentPin.size() == 0) {
                    Context context = getApplicationContext();
                    CharSequence text = "There is no parent account";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();

                }
                else {
                    startActivity(new Intent(ParentMode.this, PopUp.class));

                }
            }
        });
    }
}
