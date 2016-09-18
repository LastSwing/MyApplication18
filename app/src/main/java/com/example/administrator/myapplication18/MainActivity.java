package com.example.administrator.myapplication18;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private final static String MyFileName = "myFile";

    private Button write,read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        write = (Button)findViewById(R.id.btn_write);
        read = (Button)findViewById(R.id.btn_read);

        write.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                OutputStream out = null;
                try {
                    FileOutputStream fileOutputStream=openFileOutput(MyFileName,MODE_PRIVATE);
                    out=new BufferedOutputStream(fileOutputStream);
                    String content="2013011370 liyang";
                    try {
                        out.write(content.getBytes(StandardCharsets.UTF_8));
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

        read.setOnClickListener(new View.OnClickListener() {
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
                            stringBuilder.append((char)c);
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