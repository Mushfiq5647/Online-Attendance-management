package com.example.user.attendancemanagement;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Profile extends AppCompatActivity {

     String emailid;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    com.android.volley.RequestQueue rq;
    TextView nametext,emailtext,contacttext,gendertext;
    String tname,temail,contact,gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
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

                        Intent intent = new Intent(Profile.this,Profile.class);
                        intent.putExtra("emailid",emailid);
                        startActivity(intent);
                        //Toast.makeText(Profile.this, "My Account", Toast.LENGTH_SHORT).show();
                        break;


                    case R.id.settings:
                         Intent intent1 = new Intent(Profile.this,Main2Activity.class);
                        intent1.putExtra("emailid",emailid);
                        startActivity(intent1);

                        //Toast.makeText(Profile.this, "Settings",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.developer:
                        Intent intent3 = new Intent(Profile.this,developer.class);
                        startActivity(intent3);
                        //Toast.makeText(Profile.this, "My Cart",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.mycart:
                        Intent intent2 = new Intent(Profile.this,MainActivity.class);
                        startActivity(intent2);
                        //Toast.makeText(Profile.this, "My Cart",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        return true;
                }

                return true;

            }
        });






        rq = Volley.newRequestQueue(this);
        nametext = (TextView) findViewById(R.id.name);
        emailtext = (TextView) findViewById(R.id.email);
        contacttext = (TextView) findViewById(R.id.phone);
        gendertext = (TextView) findViewById(R.id.gender);


        new Connection().execute();


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    class Connection extends AsyncTask<String, String, String>
    {
        private String convertStreamToString(InputStream is) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append('\n');
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return sb.toString();
        }

        protected String doInBackground(String... params)
        {

            String result = "";
            Intent intent =getIntent();
               emailid =intent.getStringExtra("emailid");
             emailtext.setText("");
           // String courseno = "CSE3202" ;
            String host = "http://192.168.68.222/a/fetchuser.php?email="+emailid;
            try {
                URL url = new URL(host);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                InputStream in = new BufferedInputStream(conn.getInputStream());
                result = convertStreamToString(in);

                Log.d("wswsaaaa",result);
            }
            catch (Exception e)
            {
                return new String("Ther exception: " +e.getMessage());
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            try{
                Log.d("ededed","ee"+result);
                Log.d("ashe?","wsswsw");
                JSONObject jsonResult = new JSONObject(result);
                int success =jsonResult.getInt("success");

                if(success==1)
                {

                    ArrayList<Integer> arrayList = new ArrayList<Integer>();
                    JSONArray students = jsonResult.getJSONArray("email");
                    for (int i=0; i< students.length(); i++)
                    {
                        JSONObject student= students.getJSONObject(i);
                        String email = student.getString("email");
                        String firstname =student.getString("firstname");
                        String lastname =student.getString("lastname");
                        String contact = student.getString("Contact");
                        String gender = student.getString("Gender");
                        //String line = email + "-" + firstname + "-" + lastname;
                        String line = email + "";
                        String line1 = firstname + " "+ lastname + "";
                        String line2 = contact + "";
                        String line3 = gender + "";
                        Log.d("eded",line);
                        emailtext.setText(line);
                        nametext.setText(line1);
                        contacttext.setText(line2);
                        gendertext.setText(line3);


                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"There is no data",Toast.LENGTH_SHORT).show();
                }
            }
            catch (JSONException e)
            {
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

            }

        }
    }


}


