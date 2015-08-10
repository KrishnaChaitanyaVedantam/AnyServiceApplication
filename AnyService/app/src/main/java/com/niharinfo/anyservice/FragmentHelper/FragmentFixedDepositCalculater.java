package com.niharinfo.anyservice.FragmentHelper;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.niharinfo.anyservice.Helper.DecimalDigitsInputFilter;
import com.niharinfo.anyservice.R;

import java.math.BigDecimal;


public class FragmentFixedDepositCalculater extends Fragment {
    public static FragmentFixedDepositCalculater newInstance(int page, String title) {

        FragmentFixedDepositCalculater fixeddeposit = new FragmentFixedDepositCalculater();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fixeddeposit.setArguments(args);
        return fixeddeposit;
    }
    SeekBar FDseekBar,FDseekBarPercentage,FDseekbarTimer;
    EditText edtFDLoanAmount,edtFDRateOfInterest,edtFDLoanTenure;
    TextView txtFDAmount;
    String i;
    String r;
    Double FDprincipalAmount,FDrateOfInterest,FDloanTenure,FDnumerator,FDdenominator,FDpowerValue,FDAmount,FDoriginalRoi;
    BigDecimal finalFDAmount;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fixedeposit,container,false);
        FDseekBar = (SeekBar)v.findViewById(R.id.sB_Depositamount);
        FDseekBarPercentage = (SeekBar)v.findViewById(R.id.seekBarROI);
        FDseekbarTimer = (SeekBar)v.findViewById(R.id.seekBarDTIY);
        FDseekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        FDseekBarPercentage.setOnSeekBarChangeListener(seekBarChangeListenerROI);
        FDseekbarTimer.setOnSeekBarChangeListener(seekBarChangeListenerMonths);
        edtFDRateOfInterest = (EditText)v.findViewById(R.id.edtFDLoanROI);
        edtFDLoanAmount = (EditText)v.findViewById(R.id.edtFDDepositAmount);
        edtFDLoanTenure = (EditText)v.findViewById(R.id.edtFDdepositetenure);
        txtFDAmount = (TextView)v.findViewById(R.id.txtEmiAmount);

        edtFDRateOfInterest.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(3,1)});
        edtFDLoanTenure.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edtFDLoanTenure.setSelection(count);
                if(count==0){
                    i="0";
                }else{
                    i = s.toString();
                }
                int amt = Integer.parseInt(i);
                if(amt>10){
                    FDseekbarTimer.setProgress(10);
                    edtFDLoanTenure.setText("10");
                }else{
                    FDseekbarTimer.setProgress(Integer.parseInt(i));
                }
                isValid();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtFDRateOfInterest.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edtFDRateOfInterest.setSelection(edtFDRateOfInterest.length());
                if (edtFDRateOfInterest.length() == 0) {
                    r = "0";
                } else {
                    r = s.toString();
                }
                float amt = Float.parseFloat(r);
                if (amt > 20.0f) {
                    FDseekBarPercentage.setProgress(20);
                    edtFDRateOfInterest.setText("20");
                } else {
                    float a = 10 * amt;
                    int m = Math.round(a);
                    FDseekBarPercentage.setProgress(((int) a));
                }
                isValid();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtFDLoanAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // seekBar.setProgress(Integer.parseInt(s.toString()));
                edtFDLoanAmount.setSelection(count);
                if(count==0){
                    i="0";
                }else{
                    i = s.toString();
                }
                int amt = Integer.parseInt(i);
                if(amt>5000000){
                    FDseekBar.setProgress(5000000);
                    edtFDLoanAmount.setText("5000000");
                }else{
                    FDseekBar.setProgress(Integer.parseInt(i));
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

        if(edtFDLoanAmount.getText().toString().equalsIgnoreCase("0")||edtFDLoanTenure.getText().toString().equalsIgnoreCase("0")||edtFDRateOfInterest.getText().toString().equalsIgnoreCase("0.0")||edtFDLoanAmount.getText().toString().equalsIgnoreCase("")||edtFDRateOfInterest.getText().toString().equalsIgnoreCase("")||edtFDLoanTenure.getText().toString().equalsIgnoreCase("")){
            //return false;
        }else{


            FDprincipalAmount = Double.parseDouble(edtFDLoanAmount.getText().toString());
            FDrateOfInterest = Double.parseDouble(edtFDRateOfInterest.getText().toString());
            FDloanTenure = Double.parseDouble(edtFDLoanTenure.getText().toString());

            FDoriginalRoi=FDrateOfInterest/100;
            Double n=4.00;
            Double temp=(1+(FDoriginalRoi/n));
            FDAmount=FDprincipalAmount*Math.pow(temp,(n*FDloanTenure));

            double t = FDAmount;
            if(Double.isNaN(t)){
                txtFDAmount.setText("0");
            }else if(Double.isInfinite(t)){
                txtFDAmount.setText("0");
            }else{
                BigDecimal b = new BigDecimal(t).setScale(2,BigDecimal.ROUND_HALF_UP);
                txtFDAmount.setText(b.toString());
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
                    edtFDLoanAmount.setText(String.valueOf(progress));
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
                    edtFDLoanTenure.setText(String.valueOf(progress));
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
                    edtFDRateOfInterest.setText(v);
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
