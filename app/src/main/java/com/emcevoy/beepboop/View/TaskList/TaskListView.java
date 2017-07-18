package com.emcevoy.beepboop.View.TaskList;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.emcevoy.beepboop.Data.Task;
import com.emcevoy.beepboop.R;
import com.wealthfront.magellan.BaseScreenView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaskListView extends BaseScreenView<TaskListScreen> {
    public RecyclerView recyclerView;
    private TaskListAdapter adapter;

    public TaskListView(Context context) {
        super(context);
        inflate(context, R.layout.task_list_view, this);
        ButterKnife.bind(this);
        configure();
    }

    private void configure() {
        recyclerView = ButterKnife.findById(this, R.id.task_list);
        adapter = new TaskListAdapter(new TaskListAdapter.OnClickListener() {
            @Override
            public void onClick(Task task) {
                getScreen().itemClicked(task);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void setData(List<Task> tasks) {
        adapter.setData(tasks);
    }

    @OnClick(R.id.fab)
    public void onClick() {
        getScreen().addTask();
    }

}
