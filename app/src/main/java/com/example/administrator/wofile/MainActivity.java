package com.example.administrator.wofile;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1=(Button)findViewById(R.id.button1);
        Button btn2=(Button)findViewById(R.id.button2);
        final String MyFileName="wofile.txt";
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et1=(EditText)findViewById(R.id.ET1);
                EditText et2=(EditText)findViewById(R.id.ET2);
                EditText et3=(EditText)findViewById(R.id.ET3);
                EditText et4=(EditText)findViewById(R.id.ET4);
                OutputStream out=null;
                try{
                    FileOutputStream fos=openFileOutput(MyFileName,MODE_PRIVATE);
                    out=new BufferedOutputStream(fos);
                    String content="Name:"+et1.getText().toString()+
                                   " Sex:"+et2.getText().toString()+
                                   " ID:"+et3.getText().toString()+
                                   " Age:"+et4.getText().toString();
                 try{
                     out.write(content.getBytes(StandardCharsets.UTF_8));
                 }
                 finally {
                     if(out!=null)
                         out.close();;
                 }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputStream in=null;
                try{
                    FileInputStream fis=openFileInput(MyFileName);
                    in=new BufferedInputStream(fis);
                    int c;
                    StringBuilder stb=new StringBuilder("");
                    try{
                        while((c=in.read())!=-1){
                            stb.append((char)c);
                        }
                        Toast.makeText(MainActivity.this,stb.toString(),Toast.LENGTH_LONG).show();;
                    }finally {
                        if(in!=null)
                            in.close();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}