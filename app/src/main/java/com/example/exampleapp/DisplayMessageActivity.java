package com.example.exampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class DisplayMessageActivity extends AppCompatActivity {
    private ViewHolder nviewHolder = new ViewHolder();
    private double billAmount = 0.0;
    private double customPercent = 0.18;
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        nviewHolder.textView = findViewById(R.id.textView);
        nviewHolder.seekBar = findViewById(R.id.seekBar);
        nviewHolder.amountDisplay = findViewById(R.id.amountDisplay);
        nviewHolder.enterAmount = findViewById(R.id.enterAmount);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        //
        // Capture the layout's TextView and set the string as its text
        this.nviewHolder.enterAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    billAmount = Double.parseDouble(s.toString())/100;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        this.nviewHolder.textView.setText(message);
        this.nviewHolder.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                customPercent = progress / 100.0;
                updateCustom();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
    private void updateCustom(){
        double customAmount = billAmount * (1+customPercent);
        this.nviewHolder.amountDisplay.setText(currencyFormat.format(customAmount));
    }
    private static class ViewHolder{
        SeekBar seekBar;
        TextView textView;
        EditText enterAmount;
        TextView amountDisplay;
    }
}
