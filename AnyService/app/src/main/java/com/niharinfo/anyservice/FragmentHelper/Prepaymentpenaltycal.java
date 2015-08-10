package com.niharinfo.anyservice.FragmentHelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import com.niharinfo.anyservice.R;

import com.niharinfo.anyservice.Helper.DecimalDigitsInputFilter;

import java.math.BigDecimal;

/**
 * Created by chaitanya on 5/8/15.
 */
public class Prepaymentpenaltycal extends Fragment {

    SeekBar seekBarPPCC,seekBarPercentage;
    EditText edtEmiLoanAmount,edtRateOfInterest;
    TextView txtEmiAmount;
    String i;
    String r;
    Double principalAmount,rateOfInterest,loanTenure,numerator,denominator,powerValue,emiAmount,originalRoi;
    BigDecimal finalEmiAmount;

    public static Prepaymentpenaltycal newInstance(int page, String title) {

        Prepaymentpenaltycal penalty = new Prepaymentpenaltycal();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        penalty.setArguments(args);
        return penalty;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.prepaymentpenaltychargecalculator,container,false);
        seekBarPPCC = (SeekBar)v.findViewById(R.id.sB_Depositamount);
        seekBarPercentage = (SeekBar)v.findViewById(R.id.seekBarROI);
        seekBarPPCC.setOnSeekBarChangeListener(seekBarChangeListener);
        seekBarPercentage.setOnSeekBarChangeListener(seekBarChangeListenerROI);
        edtRateOfInterest = (EditText)v.findViewById(R.id.edtPPCCLoanROI);
        edtEmiLoanAmount = (EditText)v.findViewById(R.id.edtPPCCDepositAmount);
        txtEmiAmount = (TextView)v.findViewById(R.id.txtEmiAmount);
        //Calculation
       /* double amount=1000.0,Rate;
        int duration1=2;
        Rate = (2.0/12.0)/100;
        double powerVal = Math.pow((1+Rate),duration1);
        double numerator = amount*Rate*powerVal;
        double denom = powerVal-1;
        double total =  numerator/denom;
        double t = total;
        BigDecimal b = new BigDecimal(t).setScale(2,BigDecimal.ROUND_HALF_UP);*/

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
                //isValid();
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
                if(amt>5000000){
                    seekBarPPCC.setProgress(5000000);
                    edtEmiLoanAmount.setText("5000000");
                }else{
                    seekBarPPCC.setProgress(Integer.parseInt(i));
                }
                //isValid();
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
        return v;
    }

    private boolean isValid(){

        if(edtEmiLoanAmount.getText().toString().equalsIgnoreCase("0")||edtRateOfInterest.getText().toString().equalsIgnoreCase("0.0")||edtEmiLoanAmount.getText().toString().equalsIgnoreCase("")||edtRateOfInterest.getText().toString().equalsIgnoreCase("")){
            //return false;
        }else{
            /*  var $i = ($duration/400);
        var $n =($duration1/3);
        var $total1 = '0.00';

         $total = ($amount*(Math.pow(1+$i,$n)-1))/(1-(1/Math.pow(1+$i,1.0/3.0)));
         M = ( R * [(1+r)n - 1 ] ) / (1-(1+r)-1/3) */
            /*         $total = (($amount * $duration)/100);*/

            principalAmount = Double.parseDouble(edtEmiLoanAmount.getText().toString());
            rateOfInterest = Double.parseDouble(edtRateOfInterest.getText().toString());
            numerator=principalAmount*rateOfInterest;
            denominator=100.00;
            emiAmount =  numerator/denominator;
            double t = emiAmount;
            if(Double.isNaN(t)){
                txtEmiAmount.setText("0");
            }else if(Double.isInfinite(t)){
                txtEmiAmount.setText("0");
            }else{
                BigDecimal b = new BigDecimal(t).setScale(2,BigDecimal.ROUND_HALF_UP);
                txtEmiAmount.setText(b.toString());
            }

            return true;
        }
        return false;
    }

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    // onProgressChanged(seekBar,progress,fromUser);
                    edtEmiLoanAmount.setText(String.valueOf(progress));
                    isValid();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };

    private SeekBar.OnSeekBarChangeListener seekBarChangeListenerMonths =
            new SeekBar.OnSeekBarChangeListener(){

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    isValid();
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
                    isValid();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };



}
