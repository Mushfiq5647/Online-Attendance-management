package com.example.user.attendancemanagement;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class cse1101 extends AppCompatActivity {

    int preSelectedIndex = -1;
    int num ;
    ListView listView;

    final ArrayList<UserModel> list = new ArrayList<>();
    final List<UserModel> users = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cse1101);
        final String Coursename = "CSE3202";

        listView = (ListView) findViewById(R.id.listview9);
        Button insert = (Button) findViewById(R.id.submit);




       /* users.add(new UserModel(false, "Dharm"));
        users.add(new UserModel(false, "Mark"));
        users.add(new UserModel(false, "Singh"));
        users.add(new UserModel(false, "The Mobile Era")); */

        new Connection().execute();
        final CustomAdapter adapter = new CustomAdapter(this, users);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                /*  final ArrayList<UserModel> list = new ArrayList<>();*/
                UserModel model = users.get(i);

                if (model.isSelected())
                    model.setSelected(false);

                else
                    model.setSelected(true);

                users.set(i, model);
                list.add(model);
                Log.d("edede","eded"+list);
               // Toast.makeText(getApplicationContext(), "position" + list.add(model), Toast.LENGTH_SHORT).show();

                Log.d("vutu", "i:" + i);
                //now update adapter
                adapter.updateRecords(users);


            }
        });

//new added

        insert.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Submitted",Toast.LENGTH_SHORT).show();
                final JSONObject JSONcontacts = new JSONObject();
                final JSONObject rolls = new JSONObject();
                for (int i = 0; i < list.size(); i++) {
                    try {                       rolls.put("Count:" + String.valueOf(i + 1), list.get(i).getRollNum());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    JSONcontacts.put("Coursename", Coursename);
                    JSONcontacts.put("counts", rolls);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("edede","ded"+JSONcontacts.toString());
                // {"Count:1":"1507002","Count:2":"1507002","Count:3":"1507001","Count:4":"1507001","Count:5":"1507030","Count:6":"1507030"}
                new AsyncTask<Object, Object, Object>() {
                    @Override
                    protected Object doInBackground(Object[] objects) {

                        try {
                            URL url = new URL("http://192.168.68.222/a/insertapp2.php");
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("POST");
                            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                            conn.setRequestProperty("Accept","application/json");
                            conn.setDoOutput(true);
                            conn.setDoInput(true);


                            Log.i("JSON", JSONcontacts.toString());
                            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                            //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                            os.writeBytes(JSONcontacts.toString());

                            os.flush();
                            os.close();

                            Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                            Log.i("MSG" , conn.getResponseMessage());

                            conn.disconnect();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        return null;
                    }
                }.execute(null, null, null);

            }
        });

//new added end
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
            String Courseno =intent.getStringExtra("Coursenumber");
            //textViewCourse.setText(Courseno); */
         //   String courseno = "CSE3202" ;
            String host = "http://192.168.68.222/fetch.php?course="+Courseno;
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
                    JSONArray students = jsonResult.getJSONArray("Courseno");
                    for (int i=0; i< students.length(); i++)
                    {
                        JSONObject student= students.getJSONObject(i);
                        Integer rollno = student.getInt("Rollno");
                      /*  String firstname =student.getString("firstname;");
                        String lastname =student.getString("lastname;");
                        String line = rollno + "-" + firstname + "-" + lastname; */
                        String line = rollno + "";
                        users.add(new UserModel(false, line));


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

