package com.niharinfo.anyservice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by chaitanya on 22/7/15.
 */
public class FragmentPersonalLoan extends Fragment{

    public static FragmentPersonalLoan newInstance(int page, String title) {

        FragmentPersonalLoan fragmentFirst = new FragmentPersonalLoan();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_personal_loan,container,false);
        return v;
    }
}
