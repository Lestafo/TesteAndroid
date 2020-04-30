package com.example.deiteltwitter;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends ListActivity {
    private static final String SEARCHES = "searches";

    private EditText queryEditText;
    private EditText tagEditText;
    private SharedPreferences savedSearches;
    private ArrayList<String> tags;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queryEditText = (EditText) findViewById(R.id.queryEditText);
        tagEditText = (EditText) findViewById(R.id.tagEditText);

        savedSearches = getSharedPreferences(SEARCHES, MODE_PRIVATE);

        tags = new ArrayList<String>(savedSearches.getAll().keySet());
        Collections.sort(tags,String.CASE_INSENSITIVE_ORDER);

        adapter = new ArrayAdapter<String>(this,R.layout.list_item,tags);
        setListAdapter(adapter);

        ImageButton saveButton = (ImageButton) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(saveButtonListener);

        getListView().setOnItemClickListener(itemClickListener);
        getListView().setOnItemLongClickListener(itemLongClickListener);


    }
    public View.OnClickListener saveButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(queryEditText.getText().length() > 0 && tagEditText.getText().length() >0){
                addTaggedSearch(queryEditText.getText().toString(),tagEditText.getText().toString());
                
            }
        }
    }
}
