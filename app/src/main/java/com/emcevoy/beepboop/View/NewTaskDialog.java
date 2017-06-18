package com.emcevoy.beepboop.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.emcevoy.beepboop.Data.Task;
import com.emcevoy.beepboop.R;

import butterknife.ButterKnife;

public class NewTaskDialog extends Dialog {
    NewTaskDialogListener listener;

    public NewTaskDialog(@NonNull Context context) {
        super(context);
    }

//    public static NewTaskDialog newInstance(NewTaskDialogListener listener) {
//        NewTaskDialog dialog = new NewTaskDialog();
//        dialog.setListener(listener);
//        return dialog;
//    }

    public void setListener(NewTaskDialogListener listener) {
        this.listener = listener;
    }

//    @NonNull
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        View v = getLayoutInflater().inflate(R.layout.new_task_dialog, null);
//        final TextView whatTextView = ButterKnife.findById(v, R.id.whatInput);
//        final TextView whenTextView = ButterKnife.findById(v, R.id.whenInput);
//        return new AlertDialog.Builder(getActivity()).setView(v)
//            .setPositiveButton("Add", (dialog, id) -> listener.onSave(new Task(whatTextView.getText().toString(), whenTextView.getText().toString())))
//            .setNegativeButton("Cancel", (dialog, id) -> { })
//            .create();
//    }

    public interface NewTaskDialogListener{
        void onSave(Task task);
    }
}
