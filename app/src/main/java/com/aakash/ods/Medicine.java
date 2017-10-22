package com.aakash.ods;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;



public class Medicine extends AppCompatActivity {

    String names[] = {"Dr. Aakash Mahajan","Dr. Sarthak Gupta","Dr. Abhishek Singh"};
    String about[] = {"MBBS,MD(MED)","MBBS,MD(MED)","MBBS,MD(MED)"};
    String addr[]={"Viman Nagar,pune","Narhe,pune","Dhayari,pune"};
    String phone[]={"7898476761","9424835385","9765260259"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_listview);

        ListView listView = (ListView) findViewById(R.id.listview);

        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);


    }

    class CustomAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.medicine_listview, null);


            TextView textView_name = (TextView) view.findViewById(R.id.textView_name);
            TextView textView_about = (TextView) view.findViewById(R.id.textView_about);
            TextView textView_addr=(TextView)view.findViewById(R.id.textView_addr);
            TextView textView_phone=(TextView)view.findViewById(R.id.textView_phone);
            Button but = (Button) view.findViewById(R.id.button);
            textView_name.setText(names[i]);
            textView_about.setText(about[i]);
            textView_addr.setText(addr[i]);
            textView_phone.setText(phone[i]);
            but.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch(i){

                        case 0:
                        {
                            Intent intent = new Intent(Medicine.this,BookAppoint.class);
                            intent.putExtra("doc","7898476761");
                            startActivity(intent);
                            break;
                        }

                        case 1:
                        {
                            Intent intent = new Intent(Medicine.this,BookAppoint.class);
                            intent.putExtra("doc","9424835385");
                            startActivity(intent);
                            break;
                        }
                        case 2:
                        {
                            Intent intent = new Intent(Medicine.this,BookAppoint.class);
                            intent.putExtra("doc","9765260259");
                            startActivity(intent);
                            break;
                        }


                    }
                }
            });



            return view;
        }
    }


   /* public void onAppoint(View view)
    {
        Intent intent = new Intent(this,BookAppoint.class);
        Bundle bundle = new Bundle();
        bundle.putString("doc","7898476761");
        //bundle.putString("doc","Dr. Abhay Somani");
        intent.putExtras(bundle);
        startActivity(intent);
    }*/

}

