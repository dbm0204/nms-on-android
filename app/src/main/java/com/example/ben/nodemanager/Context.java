package com.example.ben.nodemanager;

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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.ourcontextmenu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.radio_router:
                AdapterContextMenuInfo info;
                info = (AdapterContextMenuInfo) item.getMenuInfo();
                position = (int) info.id;
                list.remove(position);
                //this.adapter.notifyDataSetChanged();
                return true;
        }

        return false;
    }



    public void setListAdapter(List<String> listAdapter) {
        //    this.listAdapter = listAdapter;
    }

 //   public View getListView() {
        //return;
   // }
}

