package com.emcevoy.beepboop.View;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.emcevoy.beepboop.Data.Subtask;
import com.emcevoy.beepboop.Data.Task;
import com.emcevoy.beepboop.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Admin on 18/06/2017.
 */

public class SubtaskListAdapter extends ArrayAdapter<Subtask> {

    List<Subtask> subtasks;
    Context context;

    public SubtaskListAdapter(Context context, List<Subtask> subtasks) {
        super(context, -1, subtasks);
        this.context = context;
        this.subtasks = subtasks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.subtask_row_item, null);
        TextView subtaskTitle = ButterKnife.findById(v, R.id.subtask_title);
        subtaskTitle.setText(subtasks.get(position).getTitle());
        return v;
    }
}
