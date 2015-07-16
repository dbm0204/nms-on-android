package nms.graphics;

import nms.main.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;


public class AddNodeDialog extends NodeManagerDialog {
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		// Get the layout inflater
		LayoutInflater inflater = getActivity().getLayoutInflater();

		// Inflate and set the layout for the dialog
		// Pass null as the parent view because its going in the dialog layout
		final View view = inflater.inflate(R.layout.fragment_add_node, null);
		builder.setView(view);

		builder.setMessage("Choose a type")
				.setPositiveButton(R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// Send the positive button event back to the
								// host activity
								mListener.onDialogPositiveClick(view,
										DialogType.ADD_NODE_DIALOG);
								// FIRE ZE MISSILES!
							}
						})
				.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// User cancelled the dialog
								// Send the negative button event back to the
								// host activity
								mListener.onDialogNegativeClick(view,
										DialogType.ADD_NODE_DIALOG);
							}
						});
		// Create the AlertDialog object and return it
		return builder.create();
	}

	// Use this instance of the interface to deliver action events
	NodeManagerDialogListener mListener;

	// Override the Fragment.onAttach() method to instantiate the
	// NoticeDialogListener
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// Verify that the host activity implements the callback interface
		try {
			// Instantiate the NoticeDialogListener so we can send events to the
			// host
			mListener = (NodeManagerDialogListener) activity;
		} catch (ClassCastException e) {
			// The activity doesn't implement the interface, throw exception
			throw new ClassCastException(activity.toString()
					+ " must implement NodeManagerDialogListener");
		}
	}
}
