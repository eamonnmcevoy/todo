package com.emcevoy.beepboop.View.TaskDetail;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TimePicker;

import com.emcevoy.beepboop.Util.DateUtil;
import com.emcevoy.beepboop.Data.Task;
import com.emcevoy.beepboop.R;
import com.wealthfront.magellan.DialogCreator;
import com.wealthfront.magellan.Screen;

import java.util.Calendar;
import java.util.Date;

import butterknife.ButterKnife;

public class TaskDetailScreen extends Screen<TaskDetailView> {
    private Task task;

    public TaskDetailScreen(Task task) {
        this.task = task;
    }

    @Override
    protected TaskDetailView createView(Context context) {
        final TaskDetailView view = new TaskDetailView(context);
        view.configure(task, getActivity());

        view.setOnClickTaskDetailTimeListener(new TaskDetailView.OnClickTaskDetailTimeListener() {
            @Override
            public void onClickTaskDetailDate(final View v) {
                PopupMenu popup = new PopupMenu(getActivity(), v);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.date_menu_today:
                                updateDate(new Date());
                                return true;
                            case R.id.date_menu_tomorrow:
                                updateDate(DateUtil.addDays(new Date(),1));
                                return true;
                            case R.id.date_menu_nextweek:
                                updateDate(DateUtil.addDays(new Date(),7));
                                return true;
                            case R.id.date_menu_anytime:
                                return true;
                            case R.id.date_menu_custom:
                                showDateDialog();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.inflate(R.menu.detail_date_context_menu);
                popup.show();
            }

            @Override
            public void onClickTaskDetailTime(final View v) {
                PopupMenu popup = new PopupMenu(getActivity(), v);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.time_menu_morning:
                                updateTime(DateUtil.getMorning());
                                return true;
                            case R.id.time_menu_noon:
                                updateTime(DateUtil.getNoon());
                                return true;
                            case R.id.time_menu_afternoon:
                                updateTime(DateUtil.getAfternoon());
                                return true;
                            case R.id.time_menu_evening:
                                updateTime(DateUtil.getEvening());
                                return true;
                            case R.id.time_menu_night:
                                updateTime(DateUtil.getNight());
                                return true;
                            case R.id.time_menu_custom:
                                showTimeDialog();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.inflate(R.menu.detail_time_context_menu);
                popup.show();
            }

            @Override
            public void onClickTaskDetailTitle() {
                showTaskEditDialog();
            }
        });
        return view;
    }

    private void showDateDialog() {
        showDialog(new DialogCreator() {
            @Override
            public Dialog createDialog(Activity activity) {
                View v = View.inflate(TaskDetailScreen.this.getActivity(), R.layout.task_detail_date_view, null);
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
                                updateDate(date);
                                dialog.dismiss();
                            }
                        })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create();
            }
        });
    }

    private void showTimeDialog() {
        showDialog(new DialogCreator() {
            @Override
            public Dialog createDialog(Activity activity) {
                View v = View.inflate(TaskDetailScreen.this.getActivity(), R.layout.task_detail_time_view, null);
                final TimePicker taskTimePicker = ButterKnife.findById(v, R.id.task_time_picker);
                if(task.getDate() != null) {
                    Calendar cal=Calendar.getInstance();
                    cal.setTime(task.getDate());
                    taskTimePicker.setHour(cal.get(Calendar.HOUR_OF_DAY));
                    taskTimePicker.setMinute(cal.get(Calendar.MINUTE));
                }
                return new AlertDialog.Builder(TaskDetailScreen.this.getActivity()).setView(v)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Date date = getTimeFromTimePicker(taskTimePicker);
                                updateTime(date);
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

    private void showTaskEditDialog() {
        showDialog(new DialogCreator() {
            @Override
            public Dialog createDialog(Activity activity) {
                View v = View.inflate(TaskDetailScreen.this.getActivity(), R.layout.edit_task_dialog, null);
                final EditText taskTitle = ButterKnife.findById(v, R.id.edit_task_title);
                final EditText taskWhen = ButterKnife.findById(v, R.id.edit_task_when);

                taskTitle.setText(task.getTitle());
                return new AlertDialog.Builder(TaskDetailScreen.this.getActivity()).setView(v)
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                String title = taskTitle.getText().toString();
                                task.setTitle(title);
                                updateTitle(title);

                                String when = taskWhen.getText().toString();
                                if(!when.isEmpty()) {
                                    Date date = DateUtil.toDate(when);
                                    if(date != null) {
                                        updateDateTime(date);
                                    }
                                }
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

    private void updateTitle(String title) {
        final TaskDetailView taskDetailView = this.getView();
        task.setTitle(title);
        taskDetailView.updateTitle(task.getTitle());
    }

    private void updateDateTime(Date date) {
        final TaskDetailView taskDetailView = this.getView();
        task.setDateTime(date);
        taskDetailView.updateDateTime(task.getDate());
    }

    private void updateDate(Date date) {
        final TaskDetailView taskDetailView = this.getView();
        task.setDatePart(date);
        taskDetailView.updateDateTime(task.getDate());
    }

    private void updateTime(Date date) {
        final TaskDetailView taskDetailView = this.getView();
        task.setTimePart(date);
        taskDetailView.updateDateTime(task.getDate());
    }

    private static java.util.Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

    private static java.util.Date getTimeFromTimePicker(TimePicker timePicker){
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();
        return DateUtil.getTime(hour, minute);
    }
}
