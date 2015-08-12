package com.niharinfo.anyservice.FragmentHelper;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.niharinfo.anyservice.R;

/**
 * Created by chaitanya on 8/8/15.
 */
public class HomeLoanPreApprovalSecond extends Fragment {

    Button btnBack;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_loan_pre_approval_second,container,false);
        btnBack = (Button)v.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack(
                        HomeLoanPreApprovalFragment.class.getName(),
                        FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_left_in,
                        R.anim.slide_right_out,R.anim.slide_left_in, R.anim.slide_right_out).replace(
                        R.id.frmHomeLoanApprovalContainer, new HomeLoanPreApprovalFragment(),
                        "Home Loan Approval").commit();
            }
        });
        return v;
    }
}
