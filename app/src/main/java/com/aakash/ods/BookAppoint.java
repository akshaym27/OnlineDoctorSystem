

package com.aakash.ods;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

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
import java.util.Calendar;
import java.util.Date;

public class BookAppoint extends AppCompatActivity {


    Date selectedDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookappoint);
        //setSupportActionBar(toolbar);
        Button datebut=(Button)findViewById(R.id.date_select_button);
        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                //updateLabel();
                selectedDate=myCalendar.getTime();
                Log.d("BookAppoint",  selectedDate.toString());
            }

        };

        datebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(BookAppoint.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
    public void app1(View view)
    {

        Button fab = (Button) findViewById(R.id.btnAppoint1);

        final String time = fab.getText().toString();
        final String name = ODS2.name;
        final String docNo = getIntent().getStringExtra("doc");

        //Toast.makeText(BookAppoint.this,docNo+name+time,Toast.LENGTH_SHORT).show();

        final Context context = this;
        final Handler handler = new Handler();
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {

                String login_url = "https://rockstarhariom.000webhostapp.com/Appoint.php";
                try {
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("D_ID","UTF-8")+"="+URLEncoder.encode(docNo,"UTF-8")+"&"
                            +URLEncoder.encode("P_ID","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                            +URLEncoder.encode("Time","UTF-8")+"="+URLEncoder.encode(time,"UTF-8");

                /*String post_data = URLEncoder.encode("D_ID","UTF-8")+"="+URLEncoder.encode(DocID,"UTF-8")+"&"
                        +URLEncoder.encode("P_ID","UTF-8")+"="+URLEncoder.encode(PatID,"UTF-8")+"&"
                        +URLEncoder.encode("Time","UTF-8")+"="+URLEncoder.encode(times,"UTF-8");*/
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String result="";
                    String line;
                    while((line = bufferedReader.readLine())!= null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            public void onPostExecute(final String result){
                if(result.contains("Appointment booked!")) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(BookAppoint.this,"Appointment booked",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(context,"Appointment failed!",Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

    public void app2(View view)
    {

        Button fab = (Button) findViewById(R.id.btnAppoint2);

        final String time = fab.getText().toString();
        final String name = ODS2.name;
        final String docNo = getIntent().getStringExtra("doc");

        //Toast.makeText(BookAppoint.this,docNo+name+time,Toast.LENGTH_SHORT).show();

        final Context context = this;
        final Handler handler = new Handler();
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {

                String login_url = "https://rockstarhariom.000webhostapp.com/Appoint.php";
                try {

                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("D_ID","UTF-8")+"="+URLEncoder.encode(docNo,"UTF-8")+"&"
                            +URLEncoder.encode("P_ID","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                            +URLEncoder.encode("Time","UTF-8")+"="+URLEncoder.encode(time,"UTF-8");

                /*String post_data = URLEncoder.encode("D_ID","UTF-8")+"="+URLEncoder.encode(DocID,"UTF-8")+"&"
                        +URLEncoder.encode("P_ID","UTF-8")+"="+URLEncoder.encode(PatID,"UTF-8")+"&"
                        +URLEncoder.encode("Time","UTF-8")+"="+URLEncoder.encode(times,"UTF-8");*/
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String result="";
                    String line;
                    while((line = bufferedReader.readLine())!= null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            public void onPostExecute(final String result){
                if(result.contains("Appointment booked!")) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(BookAppoint.this,"Appointment booked",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(context,"Appointment failed!",Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

    public void app3(View view)
    {

        Button fab = (Button) findViewById(R.id.btnAppoint3);

        final String time = fab.getText().toString();
        final String name = ODS2.name;
        final String docNo = getIntent().getStringExtra("doc");

        //Toast.makeText(BookAppoint.this,docNo+name+time,Toast.LENGTH_SHORT).show();

        final Context context = this;
        final Handler handler = new Handler();
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {

                String login_url = "https://rockstarhariom.000webhostapp.com/Appoint.php";
                try {

                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("D_ID","UTF-8")+"="+URLEncoder.encode(docNo,"UTF-8")+"&"
                            +URLEncoder.encode("P_ID","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                            +URLEncoder.encode("Time","UTF-8")+"="+URLEncoder.encode(time,"UTF-8");

                /*String post_data = URLEncoder.encode("D_ID","UTF-8")+"="+URLEncoder.encode(DocID,"UTF-8")+"&"
                        +URLEncoder.encode("P_ID","UTF-8")+"="+URLEncoder.encode(PatID,"UTF-8")+"&"
                        +URLEncoder.encode("Time","UTF-8")+"="+URLEncoder.encode(times,"UTF-8");*/
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String result="";
                    String line;
                    while((line = bufferedReader.readLine())!= null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            public void onPostExecute(final String result){
                if(result.contains("Appointment booked!")) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(BookAppoint.this,"Appointment booked",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(context,"Appointment failed!",Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

    public void app4(View view)
    {

        Button fab = (Button) findViewById(R.id.btnAppoint4);

        final String time = fab.getText().toString();
        final String name = ODS2.name;
        final String docNo = getIntent().getStringExtra("doc");

        //Toast.makeText(BookAppoint.this,docNo+name+time,Toast.LENGTH_SHORT).show();

        final Context context = this;
        final Handler handler = new Handler();
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {

                String login_url = "https://rockstarhariom.000webhostapp.com/Appoint.php";
                try {

                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("D_ID","UTF-8")+"="+URLEncoder.encode(docNo,"UTF-8")+"&"
                            +URLEncoder.encode("P_ID","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                            +URLEncoder.encode("Time","UTF-8")+"="+URLEncoder.encode(time,"UTF-8");

                /*String post_data = URLEncoder.encode("D_ID","UTF-8")+"="+URLEncoder.encode(DocID,"UTF-8")+"&"
                        +URLEncoder.encode("P_ID","UTF-8")+"="+URLEncoder.encode(PatID,"UTF-8")+"&"
                        +URLEncoder.encode("Time","UTF-8")+"="+URLEncoder.encode(times,"UTF-8");*/
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String result="";
                    String line;
                    while((line = bufferedReader.readLine())!= null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            public void onPostExecute(final String result){
                if(result.contains("Appointment booked!")) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(BookAppoint.this,"Appointment booked",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(context,"Appointment failed!",Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

    public void app5(View view)
    {

        Button fab = (Button) findViewById(R.id.btnAppoint5);

        final String time = fab.getText().toString();
        final String name = ODS2.name;
        final String docNo = getIntent().getStringExtra("doc");

        //Toast.makeText(BookAppoint.this,docNo+name+time,Toast.LENGTH_SHORT).show();

        final Context context = this;
        final Handler handler = new Handler();
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {

                String login_url = "https://rockstarhariom.000webhostapp.com/Appoint.php";
                try {

                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("D_ID","UTF-8")+"="+URLEncoder.encode(docNo,"UTF-8")+"&"
                            +URLEncoder.encode("P_ID","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                            +URLEncoder.encode("Time","UTF-8")+"="+URLEncoder.encode(time,"UTF-8");

                /*String post_data = URLEncoder.encode("D_ID","UTF-8")+"="+URLEncoder.encode(DocID,"UTF-8")+"&"
                        +URLEncoder.encode("P_ID","UTF-8")+"="+URLEncoder.encode(PatID,"UTF-8")+"&"
                        +URLEncoder.encode("Time","UTF-8")+"="+URLEncoder.encode(times,"UTF-8");*/
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String result="";
                    String line;
                    while((line = bufferedReader.readLine())!= null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            public void onPostExecute(final String result){
                if(result.contains("Appointment booked!")) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(BookAppoint.this,"Appointment booked",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(context,"Appointment failed!",Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

    public void app6(View view)
    {

        Button fab = (Button) findViewById(R.id.btnAppoint6);

        final String time = fab.getText().toString();
        final String name = ODS2.name;
        final String docNo = getIntent().getStringExtra("doc");

        //Toast.makeText(BookAppoint.this,docNo+name+time,Toast.LENGTH_SHORT).show();

        final Context context = this;
        final Handler handler = new Handler();
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {

                String login_url = "https://rockstarhariom.000webhostapp.com/Appoint.php";
                try {

                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("D_ID","UTF-8")+"="+URLEncoder.encode(docNo,"UTF-8")+"&"
                            +URLEncoder.encode("P_ID","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                            +URLEncoder.encode("Time","UTF-8")+"="+URLEncoder.encode(time,"UTF-8");

                /*String post_data = URLEncoder.encode("D_ID","UTF-8")+"="+URLEncoder.encode(DocID,"UTF-8")+"&"
                        +URLEncoder.encode("P_ID","UTF-8")+"="+URLEncoder.encode(PatID,"UTF-8")+"&"
                        +URLEncoder.encode("Time","UTF-8")+"="+URLEncoder.encode(times,"UTF-8");*/
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String result="";
                    String line;
                    while((line = bufferedReader.readLine())!= null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            public void onPostExecute(final String result){
                if(result.contains("Appointment booked!")) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(BookAppoint.this,"Appointment booked",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(context,"Appointment failed!",Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

    public void app7(View view)
    {

        Button fab = (Button) findViewById(R.id.btnAppoint7);

        final String time = fab.getText().toString();
        final String name = ODS2.name;
        final String docNo = getIntent().getStringExtra("doc");

        //Toast.makeText(BookAppoint.this,docNo+name+time,Toast.LENGTH_SHORT).show();

        final Context context = this;
        final Handler handler = new Handler();
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {

                String login_url = "https://rockstarhariom.000webhostapp.com/Appoint.php";
                try {

                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("D_ID","UTF-8")+"="+URLEncoder.encode(docNo,"UTF-8")+"&"
                            +URLEncoder.encode("P_ID","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                            +URLEncoder.encode("Time","UTF-8")+"="+URLEncoder.encode(time,"UTF-8");

                /*String post_data = URLEncoder.encode("D_ID","UTF-8")+"="+URLEncoder.encode(DocID,"UTF-8")+"&"
                        +URLEncoder.encode("P_ID","UTF-8")+"="+URLEncoder.encode(PatID,"UTF-8")+"&"
                        +URLEncoder.encode("Time","UTF-8")+"="+URLEncoder.encode(times,"UTF-8");*/
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String result="";
                    String line;
                    while((line = bufferedReader.readLine())!= null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            public void onPostExecute(final String result){
                if(result.contains("Appointment booked!")) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(BookAppoint.this,"Appointment booked",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(context,"Appointment failed!",Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

    public void app8(View view)
    {

        Button fab = (Button) findViewById(R.id.btnAppoint8);

        final String time = fab.getText().toString();
        final String name = ODS2.name;
        final String docNo = getIntent().getStringExtra("doc");

        //Toast.makeText(BookAppoint.this,docNo+name+time,Toast.LENGTH_SHORT).show();

        final Context context = this;
        final Handler handler = new Handler();
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {

                String login_url = "https://rockstarhariom.000webhostapp.com/Appoint.php";
                try {

                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("D_ID","UTF-8")+"="+URLEncoder.encode(docNo,"UTF-8")+"&"
                            +URLEncoder.encode("P_ID","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                            +URLEncoder.encode("Time","UTF-8")+"="+URLEncoder.encode(time,"UTF-8");

                /*String post_data = URLEncoder.encode("D_ID","UTF-8")+"="+URLEncoder.encode(DocID,"UTF-8")+"&"
                        +URLEncoder.encode("P_ID","UTF-8")+"="+URLEncoder.encode(PatID,"UTF-8")+"&"
                        +URLEncoder.encode("Time","UTF-8")+"="+URLEncoder.encode(times,"UTF-8");*/
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String result="";
                    String line;
                    while((line = bufferedReader.readLine())!= null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            public void onPostExecute(final String result){
                if(result.contains("Appointment booked!")) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(BookAppoint.this,"Appointment booked",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(context,"Appointment failed!",Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

    public void app9(View view)
    {

        Button fab = (Button) findViewById(R.id.btnAppoint9);

        final String time = fab.getText().toString();
        final String name = ODS2.name;
        final String docNo = getIntent().getStringExtra("doc");

        //Toast.makeText(BookAppoint.this,docNo+name+time,Toast.LENGTH_SHORT).show();

        final Context context = this;
        final Handler handler = new Handler();
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {

                String login_url = "https://rockstarhariom.000webhostapp.com/Appoint.php";
                try {

                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("D_ID","UTF-8")+"="+URLEncoder.encode(docNo,"UTF-8")+"&"
                            +URLEncoder.encode("P_ID","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                            +URLEncoder.encode("Time","UTF-8")+"="+URLEncoder.encode(time,"UTF-8");

                /*String post_data = URLEncoder.encode("D_ID","UTF-8")+"="+URLEncoder.encode(DocID,"UTF-8")+"&"
                        +URLEncoder.encode("P_ID","UTF-8")+"="+URLEncoder.encode(PatID,"UTF-8")+"&"
                        +URLEncoder.encode("Time","UTF-8")+"="+URLEncoder.encode(times,"UTF-8");*/
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String result="";
                    String line;
                    while((line = bufferedReader.readLine())!= null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            public void onPostExecute(final String result){
                if(result.contains("Appointment booked!")) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(BookAppoint.this,"Appointment booked",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(context,"Appointment failed!",Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

    public void app10(View view)
    {

        Button fab = (Button) findViewById(R.id.btnAppoint10);

        final String time = fab.getText().toString();
        final String name = ODS2.name;
        final String docNo = getIntent().getStringExtra("doc");

        //Toast.makeText(BookAppoint.this,docNo+name+time,Toast.LENGTH_SHORT).show();

        final Context context = this;
        final Handler handler = new Handler();
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {

                String login_url = "https://rockstarhariom.000webhostapp.com/Appoint.php";
                try {

                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("D_ID","UTF-8")+"="+URLEncoder.encode(docNo,"UTF-8")+"&"
                            +URLEncoder.encode("P_ID","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                            +URLEncoder.encode("Time","UTF-8")+"="+URLEncoder.encode(time,"UTF-8");

                /*String post_data = URLEncoder.encode("D_ID","UTF-8")+"="+URLEncoder.encode(DocID,"UTF-8")+"&"
                        +URLEncoder.encode("P_ID","UTF-8")+"="+URLEncoder.encode(PatID,"UTF-8")+"&"
                        +URLEncoder.encode("Time","UTF-8")+"="+URLEncoder.encode(times,"UTF-8");*/
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String result="";
                    String line;
                    while((line = bufferedReader.readLine())!= null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            public void onPostExecute(final String result){
                if(result.contains("Appointment booked!")) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(BookAppoint.this,"Appointment booked",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(context,"Appointment failed!",Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

    public void app11(View view)
    {

        Button fab = (Button) findViewById(R.id.btnAppoint11);

        final String time = fab.getText().toString();
        final String name = ODS2.name;
        final String docNo = getIntent().getStringExtra("doc");

        //Toast.makeText(BookAppoint.this,docNo+name+time,Toast.LENGTH_SHORT).show();

        final Context context = this;
        final Handler handler = new Handler();
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {

                String login_url = "https://rockstarhariom.000webhostapp.com/Appoint.php";
                try {

                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("D_ID","UTF-8")+"="+URLEncoder.encode(docNo,"UTF-8")+"&"
                            +URLEncoder.encode("P_ID","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                            +URLEncoder.encode("Time","UTF-8")+"="+URLEncoder.encode(time,"UTF-8");

                /*String post_data = URLEncoder.encode("D_ID","UTF-8")+"="+URLEncoder.encode(DocID,"UTF-8")+"&"
                        +URLEncoder.encode("P_ID","UTF-8")+"="+URLEncoder.encode(PatID,"UTF-8")+"&"
                        +URLEncoder.encode("Time","UTF-8")+"="+URLEncoder.encode(times,"UTF-8");*/
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String result="";
                    String line;
                    while((line = bufferedReader.readLine())!= null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            public void onPostExecute(final String result){
                if(result.contains("Appointment booked!")) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(BookAppoint.this,"Appointment booked",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(context,"Appointment failed!",Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

    public void app12(View view)
    {

        Button fab = (Button) findViewById(R.id.btnAppoint12);

        final String time = fab.getText().toString();
        final String name = ODS2.name;
        final String docNo = getIntent().getStringExtra("doc");

        //Toast.makeText(BookAppoint.this,docNo+name+time,Toast.LENGTH_SHORT).show();

        final Context context = this;
        final Handler handler = new Handler();
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {

                String login_url = "https://rockstarhariom.000webhostapp.com/Appoint.php";
                try {

                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("D_ID","UTF-8")+"="+URLEncoder.encode(docNo,"UTF-8")+"&"
                            +URLEncoder.encode("P_ID","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                            +URLEncoder.encode("Time","UTF-8")+"="+URLEncoder.encode(time,"UTF-8");

                /*String post_data = URLEncoder.encode("D_ID","UTF-8")+"="+URLEncoder.encode(DocID,"UTF-8")+"&"
                        +URLEncoder.encode("P_ID","UTF-8")+"="+URLEncoder.encode(PatID,"UTF-8")+"&"
                        +URLEncoder.encode("Time","UTF-8")+"="+URLEncoder.encode(times,"UTF-8");*/
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String result="";
                    String line;
                    while((line = bufferedReader.readLine())!= null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            public void onPostExecute(final String result){
                if(result.contains("Appointment booked!")) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(BookAppoint.this,"Appointment booked",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(context,"Appointment failed!",Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }
/*    public void app1(View view)
    {
        Bundle b = getIntent().getExtras();

        Button fab1 = (Button) findViewById(R.id.btnAppoint1);
        String time = fab1.getText().toString();
        final HelperClass hc = new HelperClass(this);
        hc.execute(LoginRequest.name,b.getString("doc"),time);
        final Context context = this;
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(hc.flag)
                {
                    Toast.makeText(BookAppoint.this,"Appointment booked",Toast.LENGTH_SHORT).show();
                }
                if(hc.flag1)
                {
                    Toast.makeText(BookAppoint.this,"error",Toast.LENGTH_SHORT).show();
                }
            }
        }, 2000);
    }*/
}

