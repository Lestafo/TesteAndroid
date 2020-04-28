package com.example.exampleapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity  {
    public static final String EXTRA_MESSAGE = "com.example.exampleapp.MESSAGE";
    private ViewHolder nViewHolder = new ViewHolder();


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.nViewHolder.butao = findViewById(R.id.button);
        this.nViewHolder.editValue = findViewById(R.id.editText);
        /*this.nViewHolder.butao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code
            }
        });*/ //or that for the buton
        //or even
        /*this.nViewHolder.butao.setOnClickListener(this);, and the mainActivity implementing View.OnClickListener*/
    }
    private static class ViewHolder{
        EditText editValue;
        Button butao;
    }


    public void sendMessage(View view){

        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = this.nViewHolder.editValue;
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);


    }

}
