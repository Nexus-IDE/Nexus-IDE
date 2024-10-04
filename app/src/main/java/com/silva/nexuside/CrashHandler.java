package com.silva.nexuside;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private Context context;

    public CrashHandler(Context context) {
        this.context = context;
    }

    @Override
    public void uncaughtException(Thread thread, final Throwable throwable) {
        new MaterialAlertDialogBuilder(context)
                .setTitle("Ooops, houve um erro.")
                .setMessage(throwable.getMessage())
                .setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Fechar o aplicativo
                        System.exit(1);
                    }
                })
                .setCancelable(false)
                .create()
                .show();

        // Isso evita que o Android exiba a tela de "O aplicativo parou"
        // System.exit(1);
    }
}