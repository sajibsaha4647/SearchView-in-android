 package com.example.searchviewinmenubar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

 public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private String[] Countrynames;
    ArrayAdapter<String>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Country names");

        listView = findViewById(R.id.listid);

        Countrynames = getResources().getStringArray(R.array.country_names);

        adapter = new ArrayAdapter<String>(MainActivity.this,R.layout.sample_view,R.id.textid,Countrynames);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String value = adapter.getItem(position);

                Toast.makeText(MainActivity.this,value,Toast.LENGTH_LONG).show();

            }
        });



    }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {

         MenuInflater menuInflater  = getMenuInflater();
         menuInflater.inflate(R.menu.menu_layout,menu);

         MenuItem menuItem = menu.findItem(R.id.searchmenu);

         SearchView searchView = (SearchView) menuItem.getActionView();

         searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
             @Override
             public boolean onQueryTextSubmit(String query) {
                 return false;
             }

             @Override
             public boolean onQueryTextChange(String newText) {

                 adapter.getFilter().filter(newText);
                 return false;
             }
         });

         return super.onCreateOptionsMenu(menu);
     }
 }