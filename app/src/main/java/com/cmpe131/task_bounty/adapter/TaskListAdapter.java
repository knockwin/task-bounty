package com.cmpe131.task_bounty.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.cmpe131.task_bounty.R;
import com.cmpe131.task_bounty.Task;

import java.util.ArrayList;

public class TaskListAdapter extends ArrayAdapter<Task> {

    private Context mContext;
    private int mResource;

    /**
     * Default constructor for the TaskListAdapter
     *
     * @param context
     * @param resource
     * @param objects
     */
    public TaskListAdapter(Context context, int resource, ArrayList<Task> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get Task Info
        String goal = getItem(position).getGoal();
        String reward = getItem(position).getReward();
        String date = getItem(position).getDate();
        boolean parentTask = getItem(position).getparentTask();

        // Create Task Object
        Task task = new Task(goal, reward, date, parentTask);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvGoal = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvReward = (TextView) convertView.findViewById(R.id.textView2);
        TextView tvDate = (TextView) convertView.findViewById(R.id.textView3);

        tvGoal.setText(task.getGoal());
        tvReward.setText(task.getReward());
        tvDate.setText(task.getDate());

        return convertView;
    }
}
