package com.emcevoy.beepboop.View;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.emcevoy.beepboop.Data.Task;
import com.emcevoy.beepboop.Data.TaskProvider;
import com.emcevoy.beepboop.R;
import com.wealthfront.magellan.Screen;

import java.util.List;

import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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

    public void addTask() {
        showDialog(activity -> {
            View v = getView().inflate(getActivity(), R.layout.new_task_dialog, null);
            final TextView whatTextView = ButterKnife.findById(v, R.id.whatInput);
            final TextView whenTextView = ButterKnife.findById(v, R.id.whenInput);
            return new AlertDialog.Builder(getActivity()).setView(v)
                    .setPositiveButton("Add", (dialog, id) ->
                            provider.add(new Task(whatTextView.getText().toString(), whenTextView.getText().toString()))
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(()->refreshData()))
                    .setNegativeButton("Cancel", (dialog, id) -> { })
                    .create();
        });
    }

    private void createObservable() {
        listObservable = Observable.fromCallable(() -> provider.getAll());
        refreshData();
    }

    private void refreshData() {
        listObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tasks -> getView().setData(tasks));
    }

    public void itemClicked(Task task) {
        getNavigator().goTo(new TaskDetailScreen(task));
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
