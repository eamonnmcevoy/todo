package com.emcevoy.beepboop.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import com.emcevoy.beepboop.Data.Task;
import com.emcevoy.beepboop.Data.TaskProvider;
import com.emcevoy.beepboop.R;
import com.wealthfront.magellan.DialogCreator;
import com.wealthfront.magellan.Screen;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import com.joestelmach.natty.*;
import com.wealthfront.magellan.transitions.CircularRevealTransition;

public class TaskListScreen extends Screen<TaskListView> {
    private TaskProvider provider = new TaskProvider();
    Observable<List<Task>> listObservable;

    @Override
    protected TaskListView createView(Context context) {
        TaskListView v = new TaskListView(context);
        createObservable();
        return v;
    }

    @Override
    public String getTitle(Context context) {
        return "Home Screen";
    }

    void addTask() {

        showDialog(new DialogCreator() {
            @Override
            public Dialog createDialog(Activity activity) {
                View v = View.inflate(TaskListScreen.this.getActivity(), R.layout.new_task_dialog, null);
                final TextView newTaskTextView = ButterKnife.findById(v, R.id.newTaskInput);
                return new AlertDialog.Builder(TaskListScreen.this.getActivity()).setView(v)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                TaskListScreen.this.addTaskClickAdd(newTaskTextView.getText().toString());
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        })
                        .create();
            }
        });
    }

    void addTaskClickAdd(String text) {
        provider.createNewTask(text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        TaskListScreen.this.refreshData();
                    }
                });
    }

    private void createObservable() {
        listObservable = Observable.fromCallable(new Callable<List<Task>>() {
            @Override
            public List<Task> call() throws Exception {
                return provider.getAll();
            }
        });
        refreshData();
    }

    private void refreshData() {
        listObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Task>>() {
                    @Override
                    public void accept(@NonNull List<Task> tasks) throws Exception {
                        TaskListScreen.this.getView().setData(tasks);
                    }
                });
    }

    void itemClicked(Task task) {
        getNavigator()
                .goTo(new TaskDetailScreen(task));
    }


//    public void defaultTransitionButtonClicked() {
//        getNavigator().goTo(new DetailScreen());
//    }
//
//    public void circularRevealTransitionButtonClicked(View clickedView) {
//        getNavigator().overrideTransition(new CircularRevealTransition(clickedView)).goTo(new DetailScreen());
//    }
//
//    public void showTransitionButtonClicked() {
//        getNavigator().show(new DetailScreen());
//    }
//
//    public void showNowTransitionButtonClicked() {
//        getNavigator().showNow(new DetailScreen());
//    }
}
