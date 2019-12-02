package com.cmpe131.task_bounty;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateParentActivity extends AppCompatActivity implements TaskHub {

    private EditText newParentPin;
    private EditText newConfirmPin;
    private Button buttonCreatePin;
    private int ParentPin;
    private int ConfirmPin;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parentlogin);

        newParentPin = findViewById(R.id.inputParentPin);
        newConfirmPin = findViewById(R.id.inputConfrimPin);
        buttonCreatePin = findViewById(R.id.buttonCreatePin);

        View.OnClickListener btnClick = new View.OnClickListener() {
            public void onClick(View v) {
                ParentPin = Integer.parseInt(newParentPin.getText().toString());
                ConfirmPin = Integer.parseInt(newConfirmPin.getText().toString());

                if (ParentPin == ConfirmPin)   {
                    Parent parent = new Parent(ParentPin);
                    setPin(parent);
                    finish();
                }
                else if (ParentPin != ConfirmPin) {
                    Context context = getApplicationContext();
                    CharSequence text = "Pins do not match";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }

            }
        };
        buttonCreatePin.setOnClickListener(btnClick);
    }

    private void setPin(Parent parent) {
        parentPin.add(parent);
    }

}
