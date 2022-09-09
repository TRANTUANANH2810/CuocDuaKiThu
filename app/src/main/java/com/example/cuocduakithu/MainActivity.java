package com.example.cuocduakithu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
         TextView txtDiem;
         CheckBox cbOne,cbTwo,cbThree;
         SeekBar sbOne,sbTwo,sbThree;
         ImageButton ibtnPlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Khai bao
        AnhXa();
//        thoi gian random
        CountDownTimer countDownTimer = new CountDownTimer(60000,300) {
            @Override
            public void onTick(long l) {
                int number = 5;
                Random randon = new Random();
                int one = randon.nextInt(number);
                int two = randon.nextInt(number);
                int three = randon.nextInt(number);

//                kiểm tra WIn one
                if (sbOne.getProgress() >= sbOne.getMax()){
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "One Win", Toast.LENGTH_SHORT).show();
                }
                //                kiểm tra WIn two

                if (sbTwo.getProgress() >= sbTwo.getMax())
                {
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Two WIn", Toast.LENGTH_SHORT).show();
                }
                if (sbThree.getProgress() >= sbThree.getMax())
                {
                    this.cancel();
//                    HIện Play
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Three WIn", Toast.LENGTH_SHORT).show();
                }

                sbOne.setProgress(sbOne.getProgress()+one);
                sbTwo.setProgress(sbTwo.getProgress()+two);
                sbThree.setProgress(sbThree.getProgress()+three);
            }

            @Override
            public void onFinish() {

            }
        };
        ibtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbOne.isChecked() || cbTwo.isChecked()|| cbThree.isChecked()){
                    //              set về vị trí ban đầu sau khi chơi xong  ( ấn nut play)
                    sbOne.setProgress(0);
                    sbTwo.setProgress(0);
                    sbThree.setProgress(0);

                    ibtnPlay.setVisibility(View.INVISIBLE);
                    countDownTimer.start();
                }else {
                    Toast.makeText(MainActivity.this, "Vui long dat cuoc truoc khi choi", Toast.LENGTH_SHORT).show();
                }

            }
        });

        cbOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    // bỏ check 2,3
                    cbTwo.setChecked(false);
                    cbThree.setChecked(false);
                }
            }
        });

        cbTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //bỏ check 1,3
                cbOne.setChecked(false);
                cbThree.setChecked(false);
            }
        });

        cbThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                cbOne.setChecked(false);
                cbTwo.setChecked(false);
            }
        });
    }
    private void AnhXa(){
        txtDiem     = (TextView) findViewById(R.id.textviewDiemso);
        ibtnPlay    = (ImageButton) findViewById(R.id.buttonPlay);
        cbOne       = (CheckBox) findViewById(R.id.checkboxOne);
        cbTwo       = (CheckBox) findViewById(R.id.checkboxTwo);
        cbThree     = (CheckBox) findViewById(R.id.checkboxThree);
        sbOne       = (SeekBar) findViewById(R.id.seebarOne);
        sbTwo       = (SeekBar) findViewById(R.id.seebarTwo);
        sbThree     = (SeekBar) findViewById(R.id.seebarThree);
    }
}