package com.emcevoy.beepboop.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.emcevoy.beepboop.Data.Task;
import com.emcevoy.beepboop.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;

import static android.text.format.DateUtils.MINUTE_IN_MILLIS;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {
    private List<Task> taskList = new ArrayList<>();
    private OnClickListener onClickListener;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("E, MMM dd yyyy");

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
        public TextView dueDate;
        private Task task;

        public TaskViewHolder(View view, final OnClickListener onClickListener) {
            super(view);
            title = ButterKnife.findById(view, R.id.task_title);
            dueDate = ButterKnife.findById(view, R.id.task_duedate);
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onClick(task);
                }
            });
        }

        public void setTask(Task task) {
            this.task = task;
            title.setText(task.getTitle());

            Date d = task.getDate();
            if(d != null) {
                String dateString = DateUtils.getRelativeTimeSpanString(d.getTime(), new Date().getTime(), MINUTE_IN_MILLIS).toString();
                dueDate.setText(dateString);
            }
        }
    }

    public interface OnClickListener {
        void onClick(Task task);
    }
}
