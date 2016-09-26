package com.example.wper_smile.file;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
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
                    String name=stuName.getText().toString();
                    String num=stuNum.getText().toString();
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
                InputStream in=null;
                try {
                    FileInputStream fileInputStream = openFileInput(MyFileName);
                    in=new BufferedInputStream(fileInputStream);

                    int c;
                    StringBuilder stringBuilder=new StringBuilder("");
                    try{
                        while ((c=in.read())!=-1) {
                            stringBuilder.append((char) c);
                        }
                        Toast.makeText(MainActivity.this,stringBuilder.toString(),Toast.LENGTH_LONG).show();
                    }
                    finally {
                        if(in!=null)
                            in.close();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }


            }
        });
    }
}
