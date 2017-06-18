package com.emcevoy.beepboop.View;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.emcevoy.beepboop.Data.Task;
import com.emcevoy.beepboop.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {
    private List<Task> taskList = new ArrayList<>();
    private OnClickListener onClickListener;

    public TaskListAdapter(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setData(List<Task> newTasks) {
        taskList.clear();
        taskList.addAll(newTasks);
        notifyDataSetChanged();
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_list_item, parent, false);
        return new TaskViewHolder(itemView, onClickListener);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        holder.setTask(taskList.get(position));
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        private Task task;

        public TaskViewHolder(View view, OnClickListener onClickListener) {
            super(view);
            title = ButterKnife.findById(view, R.id.task_title);
            this.itemView.setOnClickListener(v -> onClickListener.onClick(task));
        }

        public void setTask(Task task) {
            this.task = task;
            title.setText(task.getTitle());
        }
    }

    public interface OnClickListener {
        void onClick(Task task);
    }
}
