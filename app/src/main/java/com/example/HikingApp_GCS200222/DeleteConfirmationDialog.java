package com.example.HikingApp_GCS200222;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myproject.R;

public class DeleteConfirmationDialog extends Dialog {

    private OnDeleteListener onDeleteListener;

    public interface OnDeleteListener {
        void onDeleteConfirmed();
    }

    public DeleteConfirmationDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_delete_confirmation);

        Button confirmDeleteButton = findViewById(R.id.confirmDeleteButton);
        Button cancelDeleteButton = findViewById(R.id.cancelDeleteButton);

        confirmDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDeleteListener != null) {
                    onDeleteListener.onDeleteConfirmed();
                }
                dismiss();
            }
        });

        cancelDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void setDeleteListener(OnDeleteListener listener) {
        onDeleteListener = listener;
    }
}
