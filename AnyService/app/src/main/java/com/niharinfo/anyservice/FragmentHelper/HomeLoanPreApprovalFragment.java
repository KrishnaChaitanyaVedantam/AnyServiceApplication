package com.niharinfo.anyservice.FragmentHelper;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import com.niharinfo.anyservice.R;
import com.niharinfo.anyservice.Utilities.NiceSpinner;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by chaitanya on 6/8/15.
 */
public class HomeLoanPreApprovalFragment extends Fragment implements View.OnClickListener{

    NiceSpinner niceSpinner;
    Button btnYes,btnNo,btnProceedf;
    EditText edtCity,edtRequired,edtLoanPaymentPeriod;
    RelativeLayout rel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_loan_approval,container,false);
        niceSpinner = (NiceSpinner)v.findViewById(R.id.spnLoanType);
        ArrayList<String> dataset = new ArrayList<>(Arrays.asList(
                "--Select--",
                "Transfer Your Existing Home Loan",
                "Home/Flat Ready To Move-in",
                "Home/Flat Under Construction",
                "Purchase Of House"
        ));
        niceSpinner.attachDataSource(dataset);
        rel = (RelativeLayout)v.findViewById(R.id.rel);
        btnYes = (Button)v.findViewById(R.id.btnProceedFYes);
        btnNo = (Button)v.findViewById(R.id.btnProceedFNo);
        btnProceedf = (Button)v.findViewById(R.id.btnProceedF);
        edtCity = (EditText)v.findViewById(R.id.edtHouseLocatedCity);
        edtRequired = (EditText)v.findViewById(R.id.edtRequired);
        edtLoanPaymentPeriod = (EditText)v.findViewById(R.id.edtLoanRepaymentPeriod);
        btnYes.getBackground().setColorFilter(0xFF8BC343, PorterDuff.Mode.MULTIPLY);
        btnYes.setOnClickListener(this);
        btnNo.setOnClickListener(this);
        btnProceedf.setOnClickListener(this);
        return v;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnProceedFYes :
                btnYes.getBackground().setColorFilter(0xFF8BC343, PorterDuff.Mode.MULTIPLY);
                btnNo.getBackground().setColorFilter(0xFF9E9E9E, PorterDuff.Mode.MULTIPLY);
                niceSpinner.setVisibility(View.VISIBLE);
                break;
            case R.id.btnProceedFNo:
                btnYes.getBackground().setColorFilter(0xFF9E9E9E, PorterDuff.Mode.MULTIPLY);
                btnNo.getBackground().setColorFilter(0xFF8BC343, PorterDuff.Mode.MULTIPLY);
                niceSpinner.setVisibility(View.GONE);
                break;
            case R.id.btnProceedF:
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                FragmentHLPreApprovalThird homeLoanPreApprovalFragment = new FragmentHLPreApprovalThird();
                transaction.setCustomAnimations(R.anim.slide_left_in,R.anim.slide_right_out,
                        R.anim.slide_left_in,
                        R.anim.slide_right_out);
                transaction.replace(R.id.frmHomeLoanApprovalContainer, homeLoanPreApprovalFragment,
                        "Homeloan PreApproval Second");
                transaction.commit();
                break;
            default:
                break;
        }
    }
}