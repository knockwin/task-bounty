package com.cmpe131.task_bounty;

public class Task {

    private String goal;
    private String reward;
    private String date;
    private boolean parentTask;

    // parent: true if parent class, false if not parent class
    public Task(String goal, String reward, String date, boolean parent) {
        this.goal = goal;
        this.reward = reward;
        this.date = date;
        this.parentTask = parent;
    }

    public void setGoal(String goal) { this.goal = goal; }
    public String getGoal() {
        return goal;
    }

    public void setReward(String reward) { this.reward = reward; }
    public String getReward() {
        return reward;
    }

    public void setDate(String date) { this.date = date; }
    public String getDate() { return date; }

    public void setparentTask(String date) { this.parentTask = parentTask; }
    public boolean getparentTask() { return parentTask; }
}