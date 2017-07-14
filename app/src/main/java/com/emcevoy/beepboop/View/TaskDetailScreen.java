package com.emcevoy.beepboop.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.PopupMenu;

import com.emcevoy.beepboop.Data.Task;
import com.emcevoy.beepboop.R;
import com.wealthfront.magellan.DialogCreator;
import com.wealthfront.magellan.Screen;

import java.util.Calendar;
import java.util.Date;

import butterknife.ButterKnife;

class TaskDetailScreen extends Screen<TaskDetailView> {
    private Task task;

    TaskDetailScreen(Task task) {
        this.task = task;
    }

    @Override
    protected TaskDetailView createView(Context context) {
        final TaskDetailView view = new TaskDetailView(context);
        view.configure(task, getActivity());

        view.setOnClickTaskDetailTimeListener(new TaskDetailView.OnClickTaskDetailTimeListener() {
            @Override
            public void onClickTaskDetailTime(final View v) {
                PopupMenu popup = new PopupMenu(getActivity(), v);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.date_menu_today:
                                return true;
                            case R.id.date_menu_tomorrow:
                                return true;
                            case R.id.date_menu_nextweek:
                                return true;
                            case R.id.date_menu_anytime:
                                return true;
                            case R.id.date_menu_custom:
                                showDateDialog(v);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.inflate(R.menu.detail_date_context_menu);
                popup.show();
            }
        });
        return view;
    }

    private void showDateDialog(final View viewToRefresh) {
        final TaskDetailView taskDetailView = this.getView();
        showDialog(new DialogCreator() {
            @Override
            public Dialog createDialog(Activity activity) {
                View v = TaskDetailScreen.this.getView().inflate(TaskDetailScreen.this.getActivity(), R.layout.task_detail_time_view, null);
                final DatePicker taskDatePicker = ButterKnife.findById(v, R.id.task_date_picker);
                if(task.getDate() != null) {
                    Calendar cal=Calendar.getInstance();
                    cal.setTime(task.getDate());
                    taskDatePicker.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                }
                return new AlertDialog.Builder(TaskDetailScreen.this.getActivity()).setView(v)
                    .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Date date = getDateFromDatePicker(taskDatePicker);
                                task.setDatePart(date);
                                taskDetailView.setDueDate(task.getDate());
                            }
                        })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {}
                    })
                    .create();
            }
        });
    }

    static java.util.Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }
}
