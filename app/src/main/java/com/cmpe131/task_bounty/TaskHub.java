package com.cmpe131.task_bounty;

import java.util.ArrayList;

public interface TaskHub {
    ArrayList<Task> inProgressTasks = new ArrayList<>();
    ArrayList<Task> completedTasks = new ArrayList<>();
    ArrayList<Parent>  parentPin = new ArrayList<>();
    ArrayList<Integer> Options = new ArrayList<>();

}
