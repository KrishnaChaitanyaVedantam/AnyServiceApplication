package com.niharinfo.anyservice;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by chaitanya on 20/7/15.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    private final String[] TITLES = {"Emi Calculator","Home Loan","Personal Loan","Top Paid","Top Free","Top Grossing",};

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
                return HomeFragment.newInstance(1,"Home Loan");
            case 2:
                return FragmentPersonalLoan.newInstance(position,"Personal Loan");
            case 3:
                return HomeFragment.newInstance(position,"Home Loan");
            case 4:
                return HomeFragment.newInstance(position,"Home Loan");
            case 5:
                return HomeFragment.newInstance(position,"Home Loan");
        }

        return null;
    }
}
