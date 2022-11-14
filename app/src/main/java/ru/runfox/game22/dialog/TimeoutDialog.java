package ru.runfox.game22.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import ru.runfox.game22.R;

public class TimeoutDialog extends DialogFragment
{
    private String correctAnswer;

    public void setCorrectAnswer(String value) {
        correctAnswer = value;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setMessage(R.string.timeoutDialogTitle);
        setCancelable(false);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View v = inflater.inflate(R.layout.dialog_timeout, null, false);
        TextView textTimeoutDialog = v.findViewById(R.id.text_timeout_dialog);
        String dialogText = getResources().getString(R.string.timeoutDialogCorrectAnswer);
        dialogText+=correctAnswer;
        textTimeoutDialog.setText(dialogText);
        alert.setView(v);

        alert.setPositiveButton(R.string.timeoutDialogButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        // Вернуть экземпляр диалогового окна для показа
        return alert.create();
    }
}