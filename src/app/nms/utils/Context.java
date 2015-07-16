package app.nms.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import app.nms.R.id;
import app.nms.R.layout;
import app.nms.canvas.WorkSpace;
public class Context extends Activity
{
    private WorkSpace mWorkspace;
    private String[] items;
    private List<String> list;
    private List<String> adapter;
    private int position;

    @Override
    public void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        setContentView(layout.listitems);
        fillData();
//        registerForContextMenu(getListView());
    }

    private void fillData() {
        items = new String[]{"Start  Connection", "Delete  Connection","Element Properties"};
        list = new ArrayList<String>();
        Collections.addAll(list, items);
        adapter = (List<String>) new ArrayAdapter<String>(this, layout.listitems, id.r_text, list);
        setListAdapter(adapter);
    }



    public void setListAdapter(List<String> listAdapter) {
        //    this.listAdapter = listAdapter;
    }

 //   public View getListView() {
        //return;
   // }
}

