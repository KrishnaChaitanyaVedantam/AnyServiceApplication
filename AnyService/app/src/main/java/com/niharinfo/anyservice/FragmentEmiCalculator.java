package com.niharinfo.anyservice;

import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;

/**
 * Created by chaitanya on 27/7/15.
 */
public class FragmentEmiCalculator extends Fragment {

    SeekBar seekBar;
    EditText edtEmiLoanAmount;
    String i;

    public static FragmentEmiCalculator newInstance(int page, String title) {
        FragmentEmiCalculator fragmentFirst = new FragmentEmiCalculator();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_emi_calculator,container,false);
        seekBar = (SeekBar)v.findViewById(R.id.seekBar1);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        edtEmiLoanAmount = (EditText)v.findViewById(R.id.edtEmiLoanAmount);
        edtEmiLoanAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               // seekBar.setProgress(Integer.parseInt(s.toString()));
                edtEmiLoanAmount.setSelection(count);
                if(count==0){
                    i="0";
                }else{
                    i = s.toString();
                }
                int amt = Integer.parseInt(i);
                if(amt>2000000){
                    seekBar.setProgress(2000000);
                    edtEmiLoanAmount.setText("2000000");
                }else{
                    seekBar.setProgress(Integer.parseInt(i));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
        return v;
    }

    /*private void updateBackground()
    {
        seekBar = seekBar.getProgress();
        *//**//*seekG = greenSeekBar.getProgress();
        //seekB = blueSeekBar.getProgress();*//**//*
        mScreen.setBackgroundColor(
                0xff000000
                        + seekR * 0x10000
                        + seekG * 0x100
                        + seekB
        );
    }*/

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    edtEmiLoanAmount.setText(String.valueOf(progress));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };
}
