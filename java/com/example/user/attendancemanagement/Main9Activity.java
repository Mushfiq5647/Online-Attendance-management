package com.example.user.attendancemanagement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Main9Activity extends AppCompatActivity {

    String emailid;
    ListView listView;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
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

                        Intent intent = new Intent(Main9Activity.this,Profile.class);
                        intent.putExtra("emailid",emailid);
                        startActivity(intent);
                        //Toast.makeText(Main3Activity.this, "My Account", Toast.LENGTH_SHORT).show();
                        break;


                    case R.id.settings:
                        Intent intent1 = new Intent(Main9Activity.this,Main2Activity.class);
                        intent1.putExtra("emailid",emailid);
                        startActivity(intent1);
                        //Toast.makeText(Main3Activity.this, "Settings",Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.developer:
                        Intent intent3 = new Intent(Main9Activity.this,developer.class);
                        intent3.putExtra("emailid",emailid);
                        startActivity(intent3);
                        //Toast.makeText(Main3Activity.this, "My Cart",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.mycart:
                        Intent intent2 = new Intent(Main9Activity.this,MainActivity.class);
                        intent2.putExtra("emailid",emailid);
                        startActivity(intent2);
                        //Toast.makeText(Main3Activity.this, "My Cart",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        return true;
                }

                return true;

            }
        });

        Intent intent =getIntent();
        emailid =intent.getStringExtra("emailid");

        final String[] semester = { "CSE4101", "CSE4102", "CSE4103", "CSE4104","CSE4105","CSE4106","CSE4107","CSE4108"};
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,semester);
        listView = (ListView)findViewById(R.id.listview1);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position==0)
                {
                    String tem = semester[0];
                    Intent intent = new Intent(view.getContext(),cse1101.class);
                    intent.putExtra("Coursenumber",tem);
                    startActivityForResult(intent,0);

                }

                if(position==1)
                {
                    String tem = semester[1];
                    Intent intent = new Intent(view.getContext(),cse1101.class);
                    intent.putExtra("Coursenumber",tem);
                    startActivityForResult(intent,1);

                }

                if(position==2)
                {
                    String tem = semester[2];
                    Intent intent = new Intent(view.getContext(),cse1101.class);
                    intent.putExtra("Coursenumber",tem);
                    startActivityForResult(intent,2);

                }

                if(position==3)
                {
                    String tem = semester[3];
                    Intent intent = new Intent(view.getContext(),cse1101.class);
                    intent.putExtra("Coursenumber",tem);
                    startActivityForResult(intent,3);


                }

                if(position==4)
                {
                    String tem = semester[4];
                    Intent intent = new Intent(view.getContext(),cse1101.class);
                    intent.putExtra("Coursenumber",tem);
                    startActivityForResult(intent,4);


                }

                if(position==5)
                {
                    String tem = semester[5];
                    Intent intent = new Intent(view.getContext(),cse1101.class);
                    intent.putExtra("Coursenumber",tem);
                    startActivityForResult(intent,5);

                }

                if(position==6)
                {
                    String tem = semester[6];
                    Intent intent = new Intent(view.getContext(),cse1101.class);
                    intent.putExtra("Coursenumber",tem);
                    startActivityForResult(intent,6);

                }

                if(position==7)
                {
                    String tem = semester[7];
                    Intent intent = new Intent(view.getContext(),cse1101.class);
                    intent.putExtra("Coursenumber",tem);
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


