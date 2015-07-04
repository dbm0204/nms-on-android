package com.example.ben.nodemanager;

import android.app.DialogFragment;
import android.view.View;

/**
 * Created by Ben on 6/27/2015.
 */
public class NodeManagerDialog extends DialogFragment
{
    public enum DialogType
    {
        ADD_NODE_DIALOG,
        DELTE_NODE_DIALOG,
        UNKNOWN_DIALOG
    }

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface NodeManagerDialogListener
    {
        public void onDialogPositiveClick(View view, DialogType type);
        public void onDialogNegativeClick(View view, DialogType type);
    }
}
