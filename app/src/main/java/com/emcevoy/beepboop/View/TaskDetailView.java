package com.emcevoy.beepboop.View;

import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.emcevoy.beepboop.Data.Subtask;
import com.emcevoy.beepboop.Data.Task;
import com.emcevoy.beepboop.R;
import com.wealthfront.magellan.BaseScreenView;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaskDetailView extends BaseScreenView<TaskListScreen> {
    @BindView(R.id.task_detail_title)
    TextView title;
    @BindView(R.id.task_detail_date)
    TextView dateText;
    @BindView(R.id.subtask_list)
    ListView subtaskList;
    @BindView(R.id.subtask_input)
    EditText subtaskInput;
    @BindView(R.id.new_subtask_widget)
    LinearLayout newSubtaskWidget;
    @BindView(R.id.task_detail_date_row)
    LinearLayout taskDetailDateRow;

    OnClickTaskDetailTimeListener onClickTaskDetailTimeListener;

    Task task;
    SubtaskListAdapter adapter;

    public TaskDetailView(Context context) {
        super(context);
        inflate(context, R.layout.task_detail, this);
        ButterKnife.bind(this);
    }

    public void configure(Task task, Activity activity) {
        this.task = task;
        title.setText(task.getTitle());
        if(task.getDate() != null) {
            setDueDate(task.getDate());
        }

        adapter = new SubtaskListAdapter(getContext(), task.getSubtasks());
        subtaskList.setAdapter(adapter);
        subtaskInput.setImeActionLabel("Add", KeyEvent.KEYCODE_ENTER);
        subtaskInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return TaskDetailView.this.addSubtask(new Subtask(v.getText().toString()));
            }
        });
        if(adapter.isEmpty()) subtaskList.setVisibility(GONE);

        activity.registerForContextMenu(taskDetailDateRow);
    }

    public void setDueDate(Date date) {
        String dateString = java.text.DateFormat.getDateInstance().format(date);
        dateText.setText(dateString);
    }

    public void setOnClickTaskDetailTimeListener(OnClickTaskDetailTimeListener onClickTaskDetailTimeListener) {
        this.onClickTaskDetailTimeListener = onClickTaskDetailTimeListener;
    }

    private boolean addSubtask(Subtask subtask) {
        task.addSubtask(subtask);
        adapter.notifyDataSetChanged();
        if(subtaskList.getVisibility()==GONE) subtaskList.setVisibility(VISIBLE);
        return true;
    }

    @OnClick(R.id.task_detail_date_row)
    public void onClickTaskDetailTime() {
        onClickTaskDetailTimeListener.onClickTaskDetailTime(taskDetailDateRow);
    }

    @OnClick(R.id.subtasks)
    public void onClickNewSubtask() {
        newSubtaskWidget.setVisibility(VISIBLE);
        newSubtaskWidget.requestFocus();
    }

    @OnClick(R.id.addSubtaskButton)
    public void onClickAddSubtaskButton() {
        String subtaskText = subtaskInput.getText().toString();
        if(!subtaskText.isEmpty())
            addSubtask(new Subtask(subtaskText));

        newSubtaskWidget.setVisibility(GONE);
        subtaskInput.setText("");
    }

    @OnClick(R.id.cancelAddSubtaskButton)
    public void onClickCancelSubtaskButton() {
        newSubtaskWidget.setVisibility(GONE);
        subtaskInput.setText("");
    }

    public interface OnClickTaskDetailTimeListener {
        void onClickTaskDetailTime(View v);
    }
}
