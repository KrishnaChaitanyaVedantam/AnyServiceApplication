package com.niharinfo.anyservice;

import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;

import com.niharinfo.anyservice.Helper.DecimalDigitsInputFilter;

import java.text.DecimalFormat;

/**
 * Created by chaitanya on 27/7/15.
 */
public class FragmentEmiCalculator extends Fragment {

    SeekBar seekBar,seekBarPercentage;
    //FloatSeekBar seekBarPercentage;
    EditText edtEmiLoanAmount,edtRateOfInterest;
    String i;
    String r;

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
        seekBarPercentage = (SeekBar)v.findViewById(R.id.seekBarROI);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        seekBarPercentage.setOnSeekBarChangeListener(seekBarChangeListenerROI);
        edtRateOfInterest = (EditText)v.findViewById(R.id.edtEmiLoanROI);
        edtEmiLoanAmount = (EditText)v.findViewById(R.id.edtEmiLoanAmount);
        edtRateOfInterest.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(3,1)});
        edtRateOfInterest.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edtRateOfInterest.setSelection(edtRateOfInterest.length());
                if(edtRateOfInterest.length()==0){
                    r="0";
                }else{
                    r = s.toString();
                }
                float amt = Float.parseFloat(r);
                if(amt>20.0f){
                    seekBarPercentage.setProgress(20);
                    edtRateOfInterest.setText("20");
                }else{
                    float a = 10*amt;
                    int m = Math.round(a);
                    seekBarPercentage.setProgress(((int) a));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
                   // onProgressChanged(seekBar,progress,fromUser);
                    edtEmiLoanAmount.setText(String.valueOf(progress));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };

    private SeekBar.OnSeekBarChangeListener seekBarChangeListenerROI =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    float val = (float)progress / 10;
                    String v = String.valueOf(val);
                    edtRateOfInterest.setText(v);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        // get quantity for plurals string
        int quantity = setQuantity(progress);

        // convert the actual progress to float
        float floatProgress = convertProgress(progress);

        // get the actual string and replace formatting with the float value
        String currentProgress = getResources()
                .getQuantityString(R.plurals.seekBarProgress, // get the plurals
                        quantity, // set the quantity
                        floatProgress); // format arguments

        // set text to display
        edtRateOfInterest.setText(currentProgress);

    }

    private Float convertProgress(int progress){
        return ((Float.valueOf(String.valueOf(progress)))/(float)10);
    }

    private int setQuantity(int progress){
        int quantity;

        if (((progress%10) == 0) && ((progress/10) == 1)){
            quantity = 1;
        } else {
            quantity = 2;
        }

        return quantity;
    }
}
