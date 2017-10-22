package com.aakash.ods;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;



public class Cardiology extends AppCompatActivity {

    String names[] = {"Dr. Abhay Somani","Dr. Sourabh Sawant","Dr. Bhavika Patel"};
    String about[]={"MD,DNB(CARDIOLOGY)","MD,DNB(CARDIOLOGY)","MD,DNB(CARDIOLOGY)"};
    String addr[]={"Narhe,pune","Katraj,pune","Kothrud,pune"};
    String phone[]={"8698985928","1234567890","7709418427"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_listview);

        ListView listView =(ListView) findViewById(R.id.listview);

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
            view = getLayoutInflater().inflate(R.layout.cardiology_listview,null);


            TextView textView_name = (TextView)view.findViewById(R.id.textView_name);
            TextView textView_about=(TextView)view. findViewById(R.id.textView_about);
            TextView textView_addr=(TextView)view.findViewById(R.id.textView_addr);
            TextView textView_phone=(TextView)view.findViewById(R.id.textView_phone);
            Button but=(Button)view.findViewById(R.id.button);
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
                            Intent intent = new Intent(Cardiology.this,BookAppoint.class);
                            intent.putExtra("doc","8698985928");
                            startActivity(intent);
                            break;
                        }

                        case 1:
                        {
                            Intent intent = new Intent(Cardiology.this,BookAppoint.class);
                            intent.putExtra("doc","1234567890");
                            startActivity(intent);
                            break;
                        }
                        case 2:
                        {
                            Intent intent = new Intent(Cardiology.this,BookAppoint.class);
                            intent.putExtra("doc","7709418427");
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
       intent.putExtra("doc","8698985928");
        //bundle.putString("doc","8698985928");
        //bundle.putString("doc","Dr. Abhay Somani");
        //intent.putExtras(bundle);
        startActivity(intent);
    }*/
}