package com.emcevoy.beepboop.View;

import android.content.Context;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.emcevoy.beepboop.Data.Subtask;
import com.emcevoy.beepboop.Data.Task;
import com.emcevoy.beepboop.R;
import com.wealthfront.magellan.BaseScreenView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnFocusChange;

public class TaskDetailView  extends BaseScreenView<TaskListScreen> {
    @BindView(R.id.task_detail_title)
    TextView title;
    @BindView(R.id.subtask_list)
    ListView subtaskList;
    @BindView(R.id.subtask_input)
    EditText subtaskInput;
    @BindView(R.id.new_subtask_widget)
    LinearLayout newSubtaskWidget;

    Task task;
    SubtaskListAdapter adapter;

    public TaskDetailView(Context context, Task task) {
        super(context);
        inflate(context, R.layout.task_detail, this);
        ButterKnife.bind(this);

        this.task = task;
        configure();
    }

    private void configure() {
        title.setText(task.getTitle());
        adapter = new SubtaskListAdapter(getContext(), task.getSubtasks());
        subtaskList.setAdapter(adapter);
        subtaskInput.setImeActionLabel("Add", KeyEvent.KEYCODE_ENTER);
        subtaskInput.setOnEditorActionListener((v, actionId, event) -> addSubtask(new Subtask(v.getText().toString())));
        if(adapter.isEmpty()) subtaskList.setVisibility(GONE);
    }

    private boolean addSubtask(Subtask subtask) {
        task.addSubtask(subtask);
        adapter.notifyDataSetChanged();
        if(subtaskList.getVisibility()==GONE) subtaskList.setVisibility(VISIBLE);
        return true;
    }

    @OnClick(R.id.subtasks)
    public void onClickNewSubtask() {
        newSubtaskWidget.setVisibility(VISIBLE);
        newSubtaskWidget.requestFocus();
    }

    @OnClick(R.id.addSubtaskButton)
    public void onClickAddSubtaskButton() {
        addSubtask(new Subtask(subtaskInput.getText().toString()));
        newSubtaskWidget.setVisibility(GONE);
        subtaskInput.setText("");
    }

    @OnClick(R.id.cancelAddSubtaskButton)
    public void onClickCancelSubtaskButton() {
        newSubtaskWidget.setVisibility(GONE);
        subtaskInput.setText("");
    }
}
