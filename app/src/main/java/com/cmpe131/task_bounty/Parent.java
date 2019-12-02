package com.cmpe131.task_bounty;

import androidx.appcompat.app.AppCompatActivity;

public class Parent extends AppCompatActivity implements TaskHub {

    public int pin;

    // create parent user when you set the pin first time
    public Parent(int pin) {
        this.pin = pin;
    }

    public void setPin(int pin) { this.pin = pin; }
    public int getPin() { return pin; }


}