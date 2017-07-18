package com.black_dreams.notification.notificationandroido;

import android.app.Notification;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private NotificationHelper notificationHelper;
    private Button btnPrimary, btnSecondary;
    private EditText etPrimary, etsecondary;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationHelper = new NotificationHelper(this);
        init();
    }

    /**
     * @return get the title of text
     */
    private String getTitleText() {
        if (etPrimary != null) {
            return etPrimary.getText().toString();
        }
        return "";
    }

    /**
     * initilization
     */
    private void init() {
        btnPrimary = (Button) findViewById(R.id.primary_button);
        btnSecondary = (Button) findViewById(R.id.secondary_button);
        etPrimary = (EditText) findViewById(R.id.primary_edit_text);
        btnPrimary.setOnClickListener(this);
        btnSecondary.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(final View view) {
                Notification.Builder nb = null;
                nb = notificationHelper.getNotification(getTitleText(),
                        "Hey! sumit there. secondary",
                        SECONDARY_CHANNEL);

                if (nb != null) {
                    notificationHelper.notify(R.id.secondary_button, nb);
                }
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(final View view) {

        Notification.Builder nb = null;
        nb = notificationHelper.getNotification(getTitleText(), "Hey! sumit there. Primary", PRIMARY_CHANNEL);

        if (nb != null) {
            notificationHelper.notify(R.id.primary_button, nb);
        }
    }

}




