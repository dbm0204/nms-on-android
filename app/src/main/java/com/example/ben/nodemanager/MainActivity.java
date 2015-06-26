package com.example.ben.nodemanager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.admin.DeviceAdminReceiver;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.HashMap;
import java.util.Map;

    public class MainActivity extends Activity implements OnClickListener
    {

     //   final int DEL_DIALOG = 1;

        Button add, delete;
        AlertDialog mOpenDialog;
        WorkSpace mWorkspace;
        private static final int DIALOG_CHOICE=20;

        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            add = (Button) findViewById(R.id.add);
            add.setOnClickListener((OnClickListener) this);
            delete = (Button) findViewById(R.id.del);
            delete.setOnClickListener((OnClickListener) this);
            mWorkspace = (WorkSpace) findViewById(R.id.workspace);
        }

        private void showNodeManagerDialog( String filename)
        {
            FragmentManager fm = getFragmentManager();
            mOpenDialog.show(fm, filename);
        }

        private abstract class DialogInputProcessor
        {
            public abstract void process(View view);
        };

        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.add:
                    if (mOpenDialog != null)
                        mOpenDialog.dismiss();
                    mOpenDialog = new AddDialog();
                    showNodeManagerDialog("fragment_add_node");
                    // showDialog(DIALOG_CHOICE);
                    // createDummyNode();
                    break;
                case R.id.del:  break;
                default:        break;

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

       /*
        @Override
        protected Dialog onCreateDialog(int id)
        {
            switch (id)
            {
                case DIALOG_CHOICE:
                                    AlertDialog.Builder box = new AlertDialog.Builder(this);
                                    box.setTitle("Select An Element:");
                                    box.setMultiChoiceItems(ele, null,
                                            new DialogInterface.OnMultiChoiceClickListener()
                                            {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which, boolean isChecked)
                                                {
                                                    if (isChecked) {
                                                        select.add(which);
                                                    } else
                                                    {
                                                        if (select.contains(which))
                                                        {
                                                            select.remove(Integer.valueOf(which));
                                                        }
                                                    }
                                                }
                                            });
                                    box.setPositiveButton("OK", new DialogInterface.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which)
                                        {
                                            for (Integer ch : select)
                                            {
                                                if (ch.equals(1))
                                                {
                                                    mComponents.put("Dummy", new User());
                                                }
                                                else
                                                    if (ch.equals(2))
                                                    {
                                                        mComponents.put("Dummy", new Router());
                                                    }

                                            }
                                        }
                                    });
                                    box.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    AlertDialog choice = box.create();
                                    choice.show();
                }

                    return super.onCreateDialog(id);
            }
*/
        //public void createDummyNode()
        //{
          //  mComponents.put("Dummy", new User());
        //}

    }