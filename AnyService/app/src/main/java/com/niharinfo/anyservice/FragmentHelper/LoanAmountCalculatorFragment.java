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

import com.niharinfo.anyservice.Helper.DecimalDigitsInputFilter;
import com.niharinfo.anyservice.R;

import java.math.BigDecimal;

/**
 * Created by chaitanya on 31/7/15.
 */
public class LoanAmountCalculatorFragment extends Fragment {

    SeekBar seekBar,seekBarPercentage,seekbarTimer;
    EditText edtEmiLoanAmount,edtRateOfInterest,edtLoanTenure;
    TextView txtEmiAmount;
    String i;
    String r;
    Double principalAmount,rateOfInterest,loanTenure,numerator,denominator,powerValue,emiAmount,originalRoi;
    BigDecimal finalEmiAmount;

    public static LoanAmountCalculatorFragment newInstance(int page, String title) {
        LoanAmountCalculatorFragment fragmentFirst = new LoanAmountCalculatorFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_loan_amount,container,false);
        seekBar = (SeekBar)v.findViewById(R.id.seekBarLoan1);
        seekBarPercentage = (SeekBar)v.findViewById(R.id.seekBarLoanROI);
        seekbarTimer = (SeekBar)v.findViewById(R.id.seekBarLoanTimer);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        seekBarPercentage.setOnSeekBarChangeListener(seekBarChangeListenerROI);
        seekbarTimer.setOnSeekBarChangeListener(seekBarChangeListenerMonths);
        edtRateOfInterest = (EditText)v.findViewById(R.id.edtLoanlROI);
        edtEmiLoanAmount = (EditText)v.findViewById(R.id.edtLoanAmount);
        edtLoanTenure = (EditText)v.findViewById(R.id.edtLoanlTenure);
        txtEmiAmount = (TextView)v.findViewById(R.id.txtLoanEmiAmount);
        edtRateOfInterest.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(3,1)});
        edtLoanTenure.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edtLoanTenure.setSelection(count);
                if (count == 0) {
                    i = "0";
                } else {
                    i = s.toString();
                }
                int amt = Integer.parseInt(i);
                if (amt > 300) {
                    seekbarTimer.setProgress(300);
                    edtLoanTenure.setText("300");
                } else {
                    seekbarTimer.setProgress(Integer.parseInt(i));
                }
                isValid();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtRateOfInterest.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edtRateOfInterest.setSelection(edtRateOfInterest.length());
                if (edtRateOfInterest.length() == 0) {
                    r = "0";
                } else {
                    r = s.toString();
                }
                float amt = Float.parseFloat(r);
                if (amt > 20.0f) {
                    seekBarPercentage.setProgress(20);
                    edtRateOfInterest.setText("20");
                } else {
                    float a = 10 * amt;
                    int m = Math.round(a);
                    seekBarPercentage.setProgress(((int) a));
                }
                isValid();
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
                if (count == 0) {
                    i = "0";
                } else {
                    i = s.toString();
                }
                int amt = Integer.parseInt(i);
                if (amt > 500000) {
                    seekBar.setProgress(500000);
                    edtEmiLoanAmount.setText("500000");
                } else {
                    seekBar.setProgress(Integer.parseInt(i));
                }
                isValid();
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
        return v;
    }

    private boolean isValid(){

        if(edtEmiLoanAmount.getText().toString().equalsIgnoreCase("0")||edtLoanTenure.getText().toString().equalsIgnoreCase("0")||edtRateOfInterest.getText().toString().equalsIgnoreCase("0.0")||edtEmiLoanAmount.getText().toString().equalsIgnoreCase("")||edtRateOfInterest.getText().toString().equalsIgnoreCase("")||edtLoanTenure.getText().toString().equalsIgnoreCase("")){
            //return false;
        }else{
            principalAmount = Double.parseDouble(edtEmiLoanAmount.getText().toString());
            rateOfInterest = Double.parseDouble(edtRateOfInterest.getText().toString());
            loanTenure = Double.parseDouble(edtLoanTenure.getText().toString());
            originalRoi = (rateOfInterest/12.0)/100;
           /* powerValue = Math.pow((1 + originalRoi), loanTenure);
            numerator = principalAmount*originalRoi*powerValue;
            denominator = powerValue-1;*/
            /*double amount=1000;
        double dueration=2;
        double timer=2;
        double Rate=dueration/12)/100);
        double total;
        total=(principalAmount*(1-(1/Math.pow(1+originalRoi,originalRoi))))/originalRoi;*/
            emiAmount =(principalAmount*(1-(1/Math.pow(1+originalRoi,loanTenure))))/originalRoi;
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
                    //isValid();
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
                    edtLoanTenure.setText(String.valueOf(progress));
                    //isValid();
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
                    //isValid();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };
}
