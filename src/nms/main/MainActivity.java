package nms.main;

import nms.canvas.WorkSpace;
import nms.graphics.AddNodeDialog;
import nms.graphics.DrawableNetworkComponent;
import nms.graphics.NodeManagerDialog;
import nms.help.About;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

public class MainActivity extends Activity implements OnClickListener,
		NodeManagerDialog.NodeManagerDialogListener {
	Button add, delete, about;
	NodeManagerDialog mOpenDialog;
	WorkSpace mWorkspace;
	private static final int DIALOG_CHOICE = 20;
	private static final int imageArray[] = { R.drawable.ic_router,
			R.drawable.ic_node, R.drawable.ic_launcher };
	private ImageView mRouter, mNode;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		add = (Button) findViewById(R.id.add);
		add.setOnClickListener(this);
		delete = (Button) findViewById(R.id.del);
		delete.setOnClickListener(this);
		about = (Button) findViewById(R.id.about);
		about.setOnClickListener(this);
		mWorkspace = (WorkSpace) findViewById(R.id.workspace);
		// mRouter.findViewById(imageArray[0]);
		// mRouter.setOnLongClickListener((OnLongClickListener) this);
		// mNode.findViewById(imageArray[1]);
		// mNode.setOnLongClickListener((OnLongClickListener) this);
	}

	/*
	 * 1 public boolean onLongClick(View v) { Log.d("LongPressed", "cClicked");
	 * switch(v.getId()) { case R.id.radio_router:
	 * Log.d("Router_Menu_Selected","cClicked"); showPopupMenu_router(v); return
	 * true;
	 * 
	 * case R.id.radio_user: Log.d("User_Menu_Selected","cClicked");
	 * showPopupMenu_node(v); return true; } return false; } private void
	 * showPopupMenu_router(View v) { PopupMenu popupMenu = new
	 * PopupMenu(MainActivity.this, v);
	 * popupMenu.getMenuInflater().inflate(R.menu.menu_main,
	 * popupMenu.getMenu()); popupMenu.setOnMenuItemClickListener( new
	 * PopupMenu.OnMenuItemClickListener() {
	 * 
	 * @Override public boolean onMenuItemClick(MenuItem item) { // switch(item)
	 * { //case R.id.start: Log.d("Start_Connection","cClicked"); // break;
	 * //case R.id.end: Log.d("End_Connection","cClicked"); // break; //case
	 * R.id.prop: Log.d("Properties_Selected","cClicked"); // break; } return
	 * true; } }); popupMenu.show();
	 * 
	 * 
	 * } private void showPopupMenu_node(View v) { PopupMenu popupMenu = new
	 * PopupMenu(MainActivity.this, v);
	 * popupMenu.getMenuInflater().inflate(R.menu.menu_main,
	 * popupMenu.getMenu()); popupMenu.setOnMenuItemClickListener(new
	 * PopupMenu.OnMenuItemClickListener() {
	 * 
	 * @Override public boolean onMenuItemClick(MenuItem item) {
	 * 
	 * return true; } }); popupMenu.show();
	 * 
	 * 
	 * }
	 */
	@Override
	public void onClick(View v) {
		Log.d("Clicked", "cClick");
		switch (v.getId()) {
		case R.id.add:
			Log.d("Clicked", "Add button");
			if (mOpenDialog != null)
				mOpenDialog.dismiss();
			mOpenDialog = new AddNodeDialog();
			mOpenDialog.show(getFragmentManager(), "dialog");
			break;
		case R.id.del:
			break;
		case R.id.about:
			Intent myIntent = new Intent(MainActivity.this, About.class);
			startActivity(myIntent);
			break;
		default:
			break;

		}
	}

	@Override
	public void onDialogNegativeClick(View view,
			NodeManagerDialog.DialogType type) {

	}

	@Override
	public void onDialogPositiveClick(View view,
			NodeManagerDialog.DialogType type) {
		switch (type) {
		case ADD_NODE_DIALOG:
			addNode(view);
			break;
		case DELTE_NODE_DIALOG:
			break;
		default:
			break;
		}
	}

	private void addNode(View view) {
		if (mWorkspace == null)
			return;
		DrawableNetworkComponent.Type type = DrawableNetworkComponent.Type.UNKNOWN;
		RadioGroup radio = (RadioGroup) view.findViewById(R.id.radio_node_type);
		switch (radio.getCheckedRadioButtonId()) {
		case R.id.radio_user:
			type = DrawableNetworkComponent.Type.USER;
			break;
		case R.id.radio_router:
			type = DrawableNetworkComponent.Type.ROUTER;
			break;
		default:
			break;
		}
		mWorkspace.addNetworkComponent(type);

	}

}