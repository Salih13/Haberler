package com.aniltekinarslan.haber;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;


/**
 * Created by Ertan on 11.07.2017.
 */

public class DialogsUtils {

    static Snackbar snackbar;

    public static Snackbar showSnackBar (CoordinatorLayout coordinatorLayout, String message, String buttonText, int time){
        snackbar = Snackbar.make(coordinatorLayout, message, time*1000)
                .setAction(buttonText, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
        snackbar.setActionTextColor(Color.RED);
        snackbar.show();
        return snackbar;
    }

    public static ProgressDialog showProgressDialog(Context context){
        ProgressDialog progressDialog = new ProgressDialog(context, R.style.CustomProgressDialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
        return progressDialog;
    }
}
