package com.example.user.attendancemanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;



public class Main2Activity extends AppCompatActivity {
    String emailid;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.account:
                        Intent intent = new Intent(Main2Activity.this,Profile.class);
                        intent.putExtra("emailid",emailid);
                        startActivity(intent);


                        //Toast.makeText(Main2Activity.this, "My Account",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.settings:

                        Intent intent1 = new Intent(Main2Activity.this, Main2Activity.class);
                        startActivity(intent1);
                        //Toast.makeText(Main2Activity.this, "Settings",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.developer:
                        Intent intent3 = new Intent(Main2Activity.this, developer.class);
                        startActivity(intent3);
                        //Toast.makeText(Main2Activity.this, "My Cart",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.mycart:
                        Intent intent2 = new Intent(Main2Activity.this, MainActivity.class);
                        startActivity(intent2);
                        //Toast.makeText(Main2Activity.this, "My Cart",Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        return true;
                }
                return true;





            }
        });

        Intent intent =getIntent();
        emailid =intent.getStringExtra("emailid");

        String[] semester = { "1st semester", "2nd semester", "3rd semester", "4th semester","5th semester","6th semester","7th semester","8th semester"};
         ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,semester);
        listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position==0)
                {
                    Intent intent = new Intent(view.getContext(),Main3Activity.class);
                    intent.putExtra("emailid",emailid);
                    startActivityForResult(intent,0);

                }

                if(position==1)
                {
                    Intent intent = new Intent(view.getContext(),Main4Activity.class);
                    intent.putExtra("emailid",emailid);
                    startActivityForResult(intent,1);

                }

                if(position==2)
                {
                    Intent intent = new Intent(view.getContext(),Main5Activity.class);
                    intent.putExtra("emailid",emailid);
                    startActivityForResult(intent,2);

                }

                if(position==3)
                {
                    Intent intent = new Intent(view.getContext(),Main6Activity.class);
                    intent.putExtra("emailid",emailid);
                    startActivityForResult(intent,3);

                }

                if(position==4)
                {
                    Intent intent = new Intent(view.getContext(),Main7Activity.class);
                    intent.putExtra("emailid",emailid);
                    startActivityForResult(intent,4);

                }

                if(position==5)
                {
                    Intent intent = new Intent(view.getContext(),Main8Activity.class);
                    intent.putExtra("emailid",emailid);
                    startActivityForResult(intent,5);

                }

                if(position==6)
                {
                    Intent intent = new Intent(view.getContext(),Main9Activity.class);
                    intent.putExtra("emailid",emailid);
                    startActivityForResult(intent,6);

                }

                if(position==7)
                {
                    Intent intent = new Intent(view.getContext(),Main10Activity.class);
                    intent.putExtra("emailid",emailid);
                    startActivityForResult(intent,7);

                }
            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
