package com.example.ben.nodemanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Arrays;
import static com.example.ben.nodemanager.R.*;

/**
 * Created by Ben on 7/4/2015.
 */
public class ListView extends Activity {
    private WorkSpace mWorkspace;
    private String[] mElements;
    private ListView list;

    @Override
    public void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        setContentView(layout.listlayout);
        //getting the elements of the NMS system and converting it to string Objects

        //mElements= getResources().getStringArray(R.array.mElements);
        Arrays.sort(mElements);
        // list = (ListView)findViewById(id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, layout.listitems, mElements);
        //list.setAdapter(adapter);
        //    registerForContextMenu(list);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        if (view.getId() == id.list) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            menu.setHeaderTitle(mElements[info.position]);
            string[] menuItems = new string[4];
            //String[] menuItems = getResources().getStringArray(R.array.menu);
            for (int i = 0; i < menuItems.length; i++) {
                // MenuItem menuItem = menu.add(Menu.NONE, i, i, menuItems[i]);

            }
        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        String[] menuItems = new String[0];
        // = getResources().getStringArray(R.array.menu);
        String menuItemName = menuItems[menuItemIndex];
        String listItemName = mElements[info.position];
        TextView text = (TextView)findViewById(R.id.footer);
        text.setText(String.format("Selected %s for item %s",menuItemName,listItemName));
        return true;


    }
}

