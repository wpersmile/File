package com.example.wper_smile.file;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private final static String MyFileName="myfile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       final EditText stuName=(EditText)findViewById(R.id.stuName);
       final EditText stuNum=(EditText)findViewById(R.id.stuNum);
        Button writeDataBtn=(Button)findViewById(R.id.writeDataBtn);
        writeDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OutputStream out=null;
                try {
                    FileOutputStream fileOutputStream=openFileOutput(MyFileName,MODE_PRIVATE);
                    out=new BufferedOutputStream(fileOutputStream);
                    String name="姓名: "+stuName.getText().toString();
                    String num=" 学号: "+stuNum.getText().toString();
                    try {
                        out.write(name.getBytes(StandardCharsets.UTF_8));
                        out.write(num.getBytes(StandardCharsets.UTF_8));
                    }
                    finally {
                        if(out!=null)
                            out.close();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button showDataBtn=(Button)findViewById(R.id.showDataBtn);
        showDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    FileInputStream fis = openFileInput(MyFileName);
                    BufferedInputStream bis = new BufferedInputStream(fis);
                    BufferedReader reader = new BufferedReader (new InputStreamReader(bis));

                    StringBuilder stringBuilder=new StringBuilder("");
                    try{
                        while (reader.ready()) {
                            stringBuilder.append((char)reader.read());
                        }
                        Log.v("log",stringBuilder.toString());
                        Toast.makeText(MainActivity.this,
                                stringBuilder.toString(),Toast.LENGTH_LONG).show();
                    }
                    finally {
                        if(reader!=null)
                            reader.close();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }


            }
        });
    }
}
