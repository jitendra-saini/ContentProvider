package com.example.contentprovider;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class CustomDialog extends AppCompatActivity {
    ArrayAdapter mAdapter;
    ListView mListView;
    TextView mEmptyView;
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        mListView = (ListView) findViewById(R.id.list);
      mEmptyView = (TextView) findViewById(R.id.emptyView);

       mAdapter = new ArrayAdapter(CustomDialog.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.months_array));
        mListView.setAdapter(mAdapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {

                showCustomDialog( adapterView.getItemAtPosition(i).toString());

                Toast.makeText(CustomDialog.this, adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });

       /* findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });*/
        mListView.setEmptyView(mEmptyView);

    }


    public void showCustomDialog(String position){


        final AlertDialog.Builder builder=new AlertDialog.Builder(CustomDialog.this);


        ViewGroup viewGroup=findViewById(android.R.id.content);
        View dialogview= LayoutInflater.from(this).inflate(R.layout.custom_layout,viewGroup,false);
             TextView textView= dialogview.findViewById(R.id.heading);
                textView.setText(position);
        Button button=dialogview.findViewById(R.id.okButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  alertDialog.dismiss();
            }


        });

        builder.setView(dialogview);
       alertDialog=builder.create();
        alertDialog.show();





    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

         getMenuInflater().inflate(R.menu.toolbar_menu,menu);

        MenuItem searchItem=menu.findItem(R.id.action_search);
        //SearchManager searchManager= (SearchManager) CustomDialog.this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView= (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                /*mAdapter.getFilter().filter(query);

                return true;*/
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);

                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
        /*if(searchItem!=null){

            searchView=(SearchView)searchItem.getActionView();

        }if(searchView!=null){

            searchView.setSearchableInfo(searchManager.getSearchableInfo(CustomDialog.this.getComponentName()));
        }

        return super.onCreateOptionsMenu(menu);
    }*/
}
