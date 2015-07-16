package nms.utils;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.internal.widget.AdapterViewCompat.AdapterContextMenuInfo;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.example.ben.nodemanager.R.*;
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

