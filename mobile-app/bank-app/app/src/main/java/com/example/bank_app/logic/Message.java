package com.example.bank_app.logic;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.support.v4.app.DialogFragment;
import com.example.bank_app.R;


public class Message extends DialogFragment {


    public void alert(String msg, Context context) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Information");
        alert.setMessage(msg);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });
        alert.show();
    }

    public ProgressDialog waiting(String msg, Context context){
        ProgressDialog progressDialog = new ProgressDialog(context,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(msg);
        progressDialog.show();
        return progressDialog;
    }
}
