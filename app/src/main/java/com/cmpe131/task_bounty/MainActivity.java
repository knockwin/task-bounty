package com.cmpe131.task_bounty;

import android.content.Intent;
import android.os.Bundle;

import com.cmpe131.task_bounty.adapter.TaskListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TaskHub {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Display Database //
        displayList();

        // Toolbar //
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Button to Create Task //
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTask();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        displayList();      // Refresh list
    }

    // Generate List for Display //
    public void displayList() {
        ListView listView = (ListView)findViewById(R.id.listView);
        TaskListAdapter adapter = new TaskListAdapter(this,R.layout.adapter_view_layout,inProgressTasks);
        listView.setAdapter(adapter);

        // Edit Selected Task
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), EditActivity.class);
                intent.putExtra("INDEX", i);    // passes data to activity
                startActivity(intent);
            }
        });
    }

    // Start Activity for Creating Task //
    public void createTask() {
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_Completed:
                Intent completed = new Intent(this, CompletedTasks.class);
                startActivity(completed);
                break;
            case R.id.action_ParentPin:
                Intent ParentPin = new Intent (this, ParentMode.class);
                startActivity(ParentPin);
                break;


        }

        return super.onOptionsItemSelected(item);
    }
}
