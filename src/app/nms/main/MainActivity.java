package com.example.ben.myapplication;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import org.w3c.dom.Node;

public class MainActivity extends Activity implements OnClickListener, NodeManagerDialog.NodeManagerDialogListener
{

        //   final int DEL_DIALOG = 1;
        Button add, delete,about_btn;
        NodeManagerDialog mOpenDialog;
        WorkSpace mWorkspace;
        private static final int DIALOG_CHOICE=20;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            add = (Button) findViewById(R.id.add);
            add.setOnClickListener(this);
            delete = (Button) findViewById(R.id.del);
            delete.setOnClickListener(this);
            about_btn =(Button) findViewById(R.id.about);
            about_btn.setOnClickListener(this);
            mWorkspace = (WorkSpace) findViewById(R.id.workspace);

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
                case R.id.about:Intent myIntent =new Intent(MainActivity.this,About.class);
                                startActivity(myIntent);
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