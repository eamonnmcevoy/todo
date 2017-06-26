package com.emcevoy.beepboop.View;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.emcevoy.beepboop.Data.Subtask;
import com.emcevoy.beepboop.R;

import java.util.List;

import butterknife.ButterKnife;

class SubtaskListAdapter extends ArrayAdapter<Subtask> {

    private List<Subtask> subtasks;
    private Context context;

    SubtaskListAdapter(Context context, List<Subtask> subtasks) {
        super(context, -1, subtasks);
        this.context = context;
        this.subtasks = subtasks;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.subtask_row_item, parent, false);
        }
        final TextView subtaskTitle = ButterKnife.findById(convertView, R.id.subtask_title);
        final CheckBox subtaskCheckbox = ButterKnife.findById(convertView, R.id.subtask_checkbox);
        subtaskCheckbox.setTag(position);

        subtaskCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                int i = (Integer) v1.getTag();
                subtasks.get(i).setDone(subtaskCheckbox.isChecked());
                if (subtasks.get(i).getDone()) {
                    subtaskTitle.setPaintFlags(subtaskTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    subtaskTitle.setPaintFlags(subtaskTitle.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                }
            }
        });

        subtaskTitle.setText(subtasks.get(position).getTitle());
        subtaskCheckbox.setChecked(subtasks.get(position).getDone());
        if(subtasks.get(position).getDone()) {
            subtaskTitle.setPaintFlags(subtaskTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else {
            subtaskTitle.setPaintFlags(subtaskTitle.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }
        return convertView;
    }
}
