package com.cmpe131.task_bounty;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.cmpe131.task_bounty.adapter.TaskListAdapter;

import static com.cmpe131.task_bounty.TaskHub.completedTasks;

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
