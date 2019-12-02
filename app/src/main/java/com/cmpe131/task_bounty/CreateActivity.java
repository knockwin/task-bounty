package com.cmpe131.task_bounty;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateActivity extends AppCompatActivity implements TaskHub {

    private DatePicker newTaskDate;
    private EditText newTaskGoal;
    private EditText newTaskReward;
    private Button buttonCreate;
    private String goal;
    private String reward;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_create);

        // Associate Objects to Components //
        newTaskDate = findViewById(R.id.inputNewDate);
        newTaskGoal = findViewById(R.id.inputNewGoal);
        newTaskReward = findViewById(R.id.inputNewReward);
        buttonCreate = findViewById(R.id.buttonCreate);

        // Button Press Action //
        View.OnClickListener btnClick = new View.OnClickListener() {
            public void onClick(View v) {
                 {
                    goal = newTaskGoal.getText().toString();
                    reward = newTaskReward.getText().toString();
                    date = formatDate(newTaskDate);
                    if (goal.isEmpty()) {
                        Context context = getApplicationContext();
                        CharSequence text = "Please enter a goal.";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast.show();
                    } else {
                        Task task = new Task(goal, reward, date);
                        setTask(task);
                        finish();   // End CreateActivity, return to MainActivity
                    }
                }
            }
        };
        buttonCreate.setOnClickListener(btnClick);
    }
    // Push Task Object to ArrayList //
    private void setTask(Task task) {
        inProgressTasks.add(task);
    }

    // Date Formatter //
    private String formatDate(DatePicker date) {
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDayOfMonth();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = format.format(calendar.getTime());
        return strDate;
    }

    // Hide Soft Keyboard //
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if (v != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + v.getLeft() - scrcoords[0];
            float y = ev.getRawY() + v.getTop() - scrcoords[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom())
                hideKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    public static void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }
}
