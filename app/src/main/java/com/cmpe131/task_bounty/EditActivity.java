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


public class EditActivity extends AppCompatActivity implements TaskHub {

    private DatePicker editTaskDate;
    private EditText editTaskGoal;
    private EditText editTaskReward;
    private Button buttonDelete;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_edit);

        // Associate Objects to Components //
        editTaskDate = findViewById(R.id.inputEditDate);
        editTaskGoal = findViewById(R.id.inputEditGoal);
        editTaskReward = findViewById(R.id.inputEditReward);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonSave = findViewById(R.id.buttonSave);

        // Fetch Task from ArrayList //
        final int INDEX = getIntent().getExtras().getInt("INDEX");
        Task task = inProgressTasks.get(INDEX);

        // Display Existing Data //
        String dateStr[] = task.getDate().split("/");
        int month = Integer.parseInt(dateStr[0]) - 1;
        int day = Integer.parseInt(dateStr[1]);
        int year = Integer.parseInt(dateStr[2]);
        editTaskDate.init(year, month, day, null);
        editTaskGoal.setText(task.getGoal());
        editTaskReward.setText(task.getReward());

        // Button Press Action (Delete) //
        View.OnClickListener btnClickDel = new View.OnClickListener() {
            public void onClick(View v) {
                inProgressTasks.remove(INDEX);
                finish();   // End Activity
            }
        };
        buttonDelete.setOnClickListener(btnClickDel);

        // Button Press Action (Save) //
        View.OnClickListener btnClickSave = new View.OnClickListener() {
            public void onClick(View v) {
                String goal = editTaskGoal.getText().toString();
                String reward = editTaskReward.getText().toString();
                String date = formatDate(editTaskDate);
                if (goal.isEmpty()) {
                    Context context = getApplicationContext();
                    CharSequence text = "Please enter a goal.";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                } else {
                    Task task = new Task(goal, reward, date, false);
                    editTask(INDEX, task);
                    finish();   // End Activity
                }
            }
        };
        buttonSave.setOnClickListener(btnClickSave);
    }

    // Edit Task Object in ArrayList //
    private void editTask(int index, Task task) {
        inProgressTasks.set(index, task);
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
