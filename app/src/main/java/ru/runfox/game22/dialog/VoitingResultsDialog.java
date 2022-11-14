package ru.runfox.game22.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import ru.runfox.game22.R;

public class VoitingResultsDialog extends DialogFragment
{
    private int[] resultVoiting;

    public void setResultVoiting(int[] results) {
        resultVoiting = results;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setMessage(R.string.voitingResultTitle);
        setCancelable(false);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View v = inflater.inflate(R.layout.dialog_voiting_result, null, false);

        String[] chs = {"A", "B", "C", "D"};

        RadioGroup radioGroup = v.findViewById(R.id.group);
        for (int i = 0; i < 4; i++) {
            if (resultVoiting[i] < 0) {
                ((RadioButton) radioGroup.getChildAt(i)).setVisibility(View.INVISIBLE);
            } else {
                String vote = chs[i]+": "+String.valueOf(resultVoiting[i])+"%        ";
                if (resultVoiting[i]<10) {
                    vote+="  ";
                }
                if (resultVoiting[i]>0) {
                    vote+="|";
                }
                for (int j=0; j<(resultVoiting[i]/2); j++) {
                    vote+="|";
                }
                ((RadioButton) radioGroup.getChildAt(i)).setText(vote);
            }
        }


        alert.setView(v);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        // Вернуть экземпляр диалогового окна для показа
        return alert.create();
    }
}