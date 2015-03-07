package com.ohyea.simpletodo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private ArrayList<String> todoitems;
    private ArrayAdapter<String> todoAdapter;
    private ListView lvitem;
    private EditText etitem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvitem = (ListView) findViewById(R.id.lvitem);
        etitem = (EditText) findViewById(R.id.etitem);
        populateArrayItems();
        todoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,todoitems);
        lvitem.setAdapter(todoAdapter);
        setupListViewListener();
    }

    private void setupListViewListener() {
        lvitem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
                todoitems.remove(pos);
                todoAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    public void onAddedItem(View v){
        String itemText = etitem.getText().toString();
        todoAdapter.add(itemText);
        etitem.setText("");
    }

    private void readItem(){
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try{
            todoitems = new ArrayList<String>(FileUtils.readLines(todoFile));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeItem(){

    }

    private void populateArrayItems() {
        todoitems = new ArrayList<>();
        todoitems.add("Item1");
        todoitems.add("Item2");
        todoitems.add("Item3");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
