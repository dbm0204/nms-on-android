package com.example.ben.myapplication;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;
import com.example.ben.myapplication.R;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.ViewGroup.LayoutParams;
import android.view.View.OnTouchListener;

public class MainActivity extends Activity implements OnTouchListener {

    private ImageView image;

    private final static int START_DRAGGING = 0;
    private final static int STOP_DRAGGING = 1;

    private LinearLayout layout=(LinearLayout)findViewById(R.id.topb);
    private int status;

    private LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

    private Button b1 = (Button) findViewById(R.id.node1);
    private Button b2 = (Button) findViewById(R.id.node2);
    private Button b3 = (Button) findViewById(R.id.node3);
    private Button br1 = (Button) findViewById(R.id.Router1);
    private Button br2 = (Button) findViewById(R.id.Router2);
    private Button br3 = (Button) findViewById(R.id.Router3);


    @Override
    public void onCreate(Bundle savedInstanceState) {

        registerForContextMenu(b1);
        registerForContextMenu(b2);
        registerForContextMenu(b3);
        registerForContextMenu(br1);
        registerForContextMenu(br2);
        registerForContextMenu(br3);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                openContextMenu(arg0);
                return true;
            }
        });

        b1.setDrawingCacheEnabled(true);
        b1.setOnTouchListener(this);

        b2.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                openContextMenu(arg0);
                return true;
            }
        });
        b2.setDrawingCacheEnabled(true);
        b2.setOnTouchListener(this);

        b3.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                openContextMenu(arg0);
                return true;
            }
        });
        b3.setDrawingCacheEnabled(true);
        b3.setOnTouchListener(this);


        br1.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                openContextMenu(arg0);
                ;
                return true;
            }
        });
        br1.setDrawingCacheEnabled(true);
        br1.setOnTouchListener(this);


        br2.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                openContextMenu(arg0);
                return true;
            }
        });
        br2.setDrawingCacheEnabled(true);
        br2.setOnTouchListener(this);


        br3.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                openContextMenu(arg0);
                return true;
            }
        });
        br3.setDrawingCacheEnabled(true);
        br3.setOnTouchListener(this);

    }

    @Override
    public boolean onTouch(View view, MotionEvent me) {
        if (me.getAction() == MotionEvent.ACTION_DOWN) {
            status = START_DRAGGING;
            image = new ImageView(this);
            image.setImageBitmap(b1.getDrawingCache());
            layout.addView(image, params);

        }
        if (me.getAction() == MotionEvent.ACTION_UP) {
            status = STOP_DRAGGING;
            Log.i("Drag", "Stopped Dragging");

        }
        if (me.getAction() == MotionEvent.ACTION_DOWN) {
            status = START_DRAGGING;
            image = new ImageView(this);
            image.setImageBitmap(b1.getDrawingCache());
            layout.addView(image, params);
        }
        if (me.getAction() == MotionEvent.ACTION_UP) {
            status = STOP_DRAGGING;
            Log.i("Drag", "Stopped Dragging");
        } else if (me.getAction() == MotionEvent.ACTION_MOVE) {
            if (status == START_DRAGGING) {
                System.out.println("Dragging");
                image.setPadding((int) me.getRawX(), (int) me.getRawY(), 0, 0);
                image.invalidate();
            }
        }
        return false;


    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Setting_NODE")
            Toast.makeText(this, "Action 1 invoked", Toast.LENGTH_SHORT).show();
        else if (item.getTitle() == "About_Node")
            Toast.makeText(this, "Action 2 invoked", Toast.LENGTH_SHORT).show();
        else if (item.getTitle() == "About_Router")
            Toast.makeText(this, "Action 3 invoked", Toast.LENGTH_SHORT).show();
        else if (item.getTitle() == "Setting_Router")
            Toast.makeText(this, "Action 3 invoked", Toast.LENGTH_SHORT).show();
        else
            return false;
        return true;
    }

    public void OnCreateContextMenu(ContextMenu menu, ContextMenu.ContextMenuInfo menuInfo, View v) {
        if (v.getId() == R.id.node1 || v.getId() == R.id.node2 || v.getId() == R.id.node3) {
            super.onCreateContextMenu(menu, v, menuInfo);
            menu.setHeaderTitle("Select an Action:");
            menu.add(0, v.getId(), 0, "Setting_NODE");
            menu.add(0, v.getId(), 0, "About Node");


        } else if (v.getId()==R.id.Router1||v.getId()==R.id.Router2||v.getId()==R.id.Router3)
        {
            super.onCreateContextMenu(menu, v, menuInfo);
            menu.setHeaderTitle("Select an Action:");
            menu.add(0, v.getId(), 0, "Setting_Router");
            menu.add(0, v.getId(), 0, "About Router");

        }

    }
}






























