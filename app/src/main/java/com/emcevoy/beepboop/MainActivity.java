package com.emcevoy.beepboop;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;

import com.emcevoy.beepboop.View.TaskListScreen;
import com.wealthfront.magellan.Navigator;
import com.wealthfront.magellan.support.SingleActivity;

public class MainActivity extends SingleActivity {
    @Override
    protected Navigator createNavigator() {
        return Navigator.withRoot(new TaskListScreen()).loggingEnabled(true).build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
