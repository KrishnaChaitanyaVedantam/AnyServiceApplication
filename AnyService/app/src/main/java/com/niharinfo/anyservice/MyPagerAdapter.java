package com.niharinfo.anyservice;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.niharinfo.anyservice.FragmentHelper.FragmentFixedDepositCalculater;
import com.niharinfo.anyservice.FragmentHelper.LoanAmountCalculatorFragment;
import com.niharinfo.anyservice.FragmentHelper.Prepaymentpenaltycal;
import com.niharinfo.anyservice.FragmentHelper.RecurringDepositCalculator;

/**
 * Created by chaitanya on 20/7/15.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    private final String[] TITLES = {"Emi Calculator","Loan Amount Calculator","Fixed Deposit Calculator","Recurring Deposit Calculator","Prepayment Penalty Charge Calculator"};

    public MyPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return FragmentEmiCalculator.newInstance(0, "Emi Calculator");
            case 1:
                return LoanAmountCalculatorFragment.newInstance(1, "Loan Amount Calculator");
            case 2:
                return FragmentFixedDepositCalculater.newInstance(position, "Fixed Deposit Calculator");
            case 3:
                return RecurringDepositCalculator.newInstance(position, "Recurring Deposit Calculator");
            case 4:
                return Prepaymentpenaltycal.newInstance(position, "Prepayment Penalty Charge Calculator");
            case 5:
                return HomeFragment.newInstance(position,"Home Loan");
        }

        return null;
    }
}
