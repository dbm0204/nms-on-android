//package com.example.ben.myapplication;
package app.nms.main;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Vector;

import app.nms.R;
import app.nms.canvas.WorkSpace;
import app.nms.graphics.AddNodeDialog;
import app.nms.graphics.DrawableNetworkComponent;
import app.nms.graphics.NodeManagerDialog;

public class MainActivity extends Activity implements OnClickListener, NodeManagerDialog.NodeManagerDialogListener
{
    ViewGroup mTopPanel;
    NodeManagerDialog mOpenDialog;
    WorkSpace mWorkspace;
    private static final int DIALOG_CHOICE=20;

    private void showPopup( int contentId, View anchor)
    {
        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext()
                .getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(contentId, null);
        final PopupWindow popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        Button btnDismiss = (Button) popupView.findViewById(R.id.dismiss);
        btnDismiss.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                popupWindow.dismiss();
            }
        });
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(anchor, 50, -30);
    }

    private void resetButtonPanel()
    {
        mTopPanel.removeAllViews();
        mTopPanel.addView(View.inflate(this, R.layout.button_panel, null));
        final Button add = (Button) mTopPanel.findViewById(R.id.add);
        add.setOnClickListener(this);
        final Button delete = (Button) mTopPanel.findViewById(R.id.del);
        delete.setOnClickListener(this);
        final Button about_btn =(Button) mTopPanel.findViewById(R.id.about);
        about_btn.setOnClickListener(this);
        final Button route = (Button) mTopPanel.findViewById(R.id.route);
        route.setOnClickListener(this);
    }

    private void setMessagePanel(int msgId)
    {
        mTopPanel.removeAllViews(   );
        mTopPanel.addView(View.inflate(this, R.layout.message_panel, null));
        final TextView text = (TextView) mTopPanel.findViewById(R.id.user_message);
        text.setText(msgId);
        final ImageButton noButton = (ImageButton) mTopPanel.findViewById(R.id.select_no);
        noButton.setOnClickListener(this);
        final Button yesButton = (Button) mTopPanel.findViewById(R.id.select_yes);
        yesButton.setOnClickListener(this);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWorkspace = (WorkSpace) findViewById(R.id.workspace);
        mTopPanel = (ViewGroup) findViewById(R.id.top_panel);
        resetButtonPanel();
    }

    private NodeManagerDialog.DialogType mSelectionType= NodeManagerDialog.DialogType.UNKNOWN_DIALOG;
    @Override
    public void onClick(View v)
    {
        Log.d("Clicked","cClick");
        switch (v.getId())
        {
            case R.id.add:
                Log.d("Clicked","Add button");
                if (mOpenDialog != null)
                    mOpenDialog.dismiss();
                mSelectionType = NodeManagerDialog.DialogType.ADD_NODE_DIALOG;
                mOpenDialog = new AddNodeDialog();
                mOpenDialog.show(getFragmentManager(), "dialog");

                break;
            case R.id.del:
                if (mOpenDialog != null)
                    mOpenDialog.dismiss();
                mWorkspace.setSelectionMode(true);
                mSelectionType = NodeManagerDialog.DialogType.DELETE_NODE_DIALOG;
                setMessagePanel(R.string.choose_to_delete);
                //mOpenDialog = new SelectNodeDialog();//R.string.choose_to_delete);
                break;
            case R.id.route:
                if (mOpenDialog != null)
                    mOpenDialog.dismiss();
                mWorkspace.setSelectionMode(true);
                mSelectionType = NodeManagerDialog.DialogType.SOURCE_NODE_DIALOG;
                setMessagePanel(R.string.choose_source);
                break;
                //mOpenDialog = new SelectNodeDialog();//R.string.choose_source);
            case R.id.about:
                if (mOpenDialog != null)
                    mOpenDialog.dismiss();
                showPopup(R.layout.about, v );
            case R.id.select_no:
                onDialogNegativeClick();
                break;
            case R.id.select_yes:
                onDialogPositiveClick(v);
            default:
                break;
        }
    }
    @Override
    public void onDialogNegativeClick()
    {
        mSelectionType = NodeManagerDialog.DialogType.UNKNOWN_DIALOG;
        mWorkspace.setSelectionMode(false);
        mSourceComponent = -1;
        mDestComponent = -1;
        resetButtonPanel();
    }

    private int mSourceComponent = -1;
    private int mDestComponent = -1;
    @Override
    public void onDialogPositiveClick(View view)
    {
        switch (mSelectionType)
        {
            case ADD_NODE_DIALOG:
                addNode(view);
                break;
            case DELETE_NODE_DIALOG:
                int toDelete = mWorkspace.getChosenComponent();
                if (toDelete==-1)
                {
                    setEasterMessage();
                    break;
                }
                mWorkspace.deleteNode(toDelete);
                mWorkspace.setSelectionMode(false);
                resetButtonPanel();
                break;
            case SOURCE_NODE_DIALOG:
                mSourceComponent = mWorkspace.getChosenComponent();
                if (mSourceComponent==-1) {
                    setEasterMessage();
                    break;
                }
                mWorkspace.setSelectionMode(true);
                mSelectionType = NodeManagerDialog.DialogType.DEST_NODE_DIALOG;
                setMessagePanel(R.string.choose_destination);
                break;
            case DEST_NODE_DIALOG:
                mDestComponent = mWorkspace.getChosenComponent();
                if (mDestComponent == -1)
                {
                    setEasterMessage();
                    break;
                }
                mWorkspace.setSelectionMode(false);
                mSelectionType = NodeManagerDialog.DialogType.SHORTEST_PATH_RESULT_DIALOG;
                Log.d("ShortestPath", "Track shortest path between " + mSourceComponent + " and " + mDestComponent);
                Vector<Integer> path = mWorkspace.findShortestPath(mSourceComponent, mDestComponent);
                if (path.isEmpty())
                    setMessagePanel(R.string.path_not_found);
                else
                    setMessagePanel(R.string.path_found);
                mWorkspace.highlightPath(path);
                break;
            case  SHORTEST_PATH_RESULT_DIALOG:
                mWorkspace.clearHighlightedPath();
                onDialogNegativeClick();
                break;
            default:
                break;
        }

    }

    private void setEasterMessage()
    {
        TextView text = (TextView) findViewById(R.id.user_message);
        text.setText("Oh no you didn't Sneaky !!!");
    }

    private void addNode(View view)
    {
        if (mWorkspace == null)
            return;
        DrawableNetworkComponent.Type type = DrawableNetworkComponent.Type.UNKNOWN;
        RadioGroup radio = (RadioGroup) view.findViewById(R.id.radio_node_type);
        switch(radio.getCheckedRadioButtonId())
        {
            case R.id.radio_user    :
                type = DrawableNetworkComponent.Type.USER;
                break;
            case R.id.radio_router  :
                type = DrawableNetworkComponent.Type.ROUTER;
                break;
            default                 :
                break;
        }
        mWorkspace.addNetworkComponent(type);
    }
}