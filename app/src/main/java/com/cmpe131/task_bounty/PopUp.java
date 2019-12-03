package com.cmpe131.task_bounty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PopUp extends AppCompatActivity implements TaskHub {

    private Button Delete;
    private EditText Pin;
    private int ParentPin;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.7));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

        Pin = findViewById(R.id.inputDeleteParent);
        Delete = findViewById(R.id.buttonDelete);


        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Options.get(0) == 1 ) {
                    Options.remove(0);
                    ParentPin = Integer.parseInt(Pin.getText().toString());
                    if(ParentPin == parentPin.get(0).getPin()) {
                        startActivity(new Intent(PopUp.this, CreateActivity.class));
                        finish();
                    }
                    else{
                        Context context = getApplicationContext();
                        CharSequence text = "Incorrect Pin";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast.show();
                    }
                }
                else if (Options.get(0) == 2) {
                    Options.remove(0);
                    ParentPin = Integer.parseInt(Pin.getText().toString());
                    if(ParentPin == parentPin.get(0).getPin()) {
                        editTask();
                        finish();
                    }
                    else {
                        Context context = getApplicationContext();
                        CharSequence text = "Incorrect Pin";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast.show();
                    }

                }
                else if(Options.get(0) == 3) {
                    Options.remove(0);
                    ParentPin = Integer.parseInt(Pin.getText().toString());
                    if(ParentPin == parentPin.get(0).getPin()) {
                        parentPin.remove(0);
                        Context context = getApplicationContext();
                        CharSequence text = "The parent account has successfully been deleted";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast.show();
                        finish();
                    }
                    else {
                        Context context = getApplicationContext();
                        CharSequence text = "Incorrect Pin";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast.show();
                    }
                }
            }
        });
    }

    public void editTask() {


            Intent intent = new Intent(PopUp.this, EditActivity.class);
            intent.putExtra("INDEX", getIntent().getExtras().getInt("INDEX"));
            startActivity(intent);

    }
}
