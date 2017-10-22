

package com.aakash.ods;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ODS1 extends AppCompatActivity {
    EditText username;
    EditText etpassword;
    Button btnlogin ;
    Button docloginbtn ;
    ListView lv;
    List<String> list;
    ArrayAdapter<String> stringArrayAdapter;
    String dname,pname,time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ods1);

        username = (EditText) findViewById(R.id.username);
        etpassword = (EditText) findViewById(R.id.password);
         btnlogin = (Button) findViewById(R.id.login1);
         docloginbtn = (Button) findViewById(R.id.docLogin);
        final Context context = this;






        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String name = username.getText().toString();
                final String password = etpassword.getText().toString();
                Response.Listener<String> responceListner = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jesonResponse = new JSONObject(response);
                            boolean success = jesonResponse.getBoolean("success");
                            if (success) {
                                String name = jesonResponse.getString("name");
                                //int age=jesonResponse.getInt("age");
                                String phone = jesonResponse.getString("phone");
                                Intent intent = new Intent(ODS1.this, ODS2.class);
                                intent.putExtra("name", name);

                               // intent.putExtra("phone",phone);
                                //intent.putExtra("username",name);
                                //intent.putExtra("age",age);

                                ODS1.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ODS1.this);
                                builder.setMessage("Login Failed");
                                builder.setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                                username.setText("");
                                etpassword.setText("");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                LoginRequest loginRequest = new LoginRequest(name, password, responceListner);
                RequestQueue queue = Volley.newRequestQueue(ODS1.this);
                queue.add(loginRequest);
            }
        });
    }




    public void Click_Signup(View view) {
        Intent intent = new Intent(ODS1.this, ODS3.class);
        startActivity(intent);
    }
}



