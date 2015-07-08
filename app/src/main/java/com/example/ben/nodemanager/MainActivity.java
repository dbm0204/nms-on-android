package com.example.ben.nodemanager;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.internal.widget.TintImageView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import org.w3c.dom.Node;
import android.widget.ImageView;
import android.view.View.OnLongClickListener;

public class MainActivity extends Activity implements OnClickListener, NodeManagerDialog.NodeManagerDialogListener
{
        Button add, delete,about;
        NodeManagerDialog mOpenDialog;
        WorkSpace mWorkspace;
        private static final int DIALOG_CHOICE=20;
        private static final int  imageArray[]={R.drawable.ic_router,
                                                R.drawable.ic_node,
                                                R.drawable.ic_launcher};
        private ImageView mRouter,mNode;
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

            /*
            mRouter.findViewById(imageArray[1]);
            mRouter.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    showPopupMenu(v);
                    return false;
                }
            });
            mNode.findViewById(imageArray[2]);
            mNode.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    showPopupMenu(v);
                    return false;
                }
            });
        }
            private void showPopupMenu(View v) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_main, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                {

                    @Override
                    public boolean onMenuItemClick(MenuItem item)
                    {

                        return true;
                    }
                });
                popupMenu.show();
            }
            */

        }
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
                    mOpenDialog = new AddNodeDialog();
                    mOpenDialog.show(getFragmentManager(), "dialog");
                    break;
                case R.id.del:
                    break;
                case R.id.about:    Intent myIntent = new Intent(MainActivity.this,About.class);
                                    startActivity(myIntent);
                    break;
                default:
                    break;

            }
        }
        @Override
        public void onDialogNegativeClick(View view,NodeManagerDialog.DialogType type)
        {}

        @Override
        public void onDialogPositiveClick(View view, NodeManagerDialog.DialogType type)
        {
            switch (type)
            {
                case ADD_NODE_DIALOG    :   addNode(view);
                                            break;
                case DELTE_NODE_DIALOG  :   break;
                default                 :   break;
            }
        }

        private void addNode(View view)
        {
            if (mWorkspace == null)
                return;
            DrawableNetworkComponent.Type type = DrawableNetworkComponent.Type.UNKNOWN;
            RadioGroup radio = (RadioGroup) view.findViewById(R.id.radio_node_type);
            switch(radio.getCheckedRadioButtonId())
            {
                case R.id.radio_user    :   type = DrawableNetworkComponent.Type.USER;
                                            break;
                case R.id.radio_router  :   type = DrawableNetworkComponent.Type.ROUTER;
                                            break;
                default                 :   break;
            }
            mWorkspace.addNetworkComponent(type);

        }


    }