package com.cmpe131.task_bounty;


import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.cmpe131.task_bounty.adapter.TaskListAdapter;


public class CompletedTasks extends AppCompatActivity implements TaskHub {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed);

        // Display Database //
        displayComp();

    }


    public void displayComp() {
        ListView listView = findViewById(R.id.compView);
        TaskListAdapter adapter = new TaskListAdapter(this, R.layout.adapter_view_layout, completedTasks);
        listView.setAdapter(adapter);

    }

}
