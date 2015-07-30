package com.niharinfo.anyservice.Helper;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chaitanya on 29/7/15.
 */
public class DecimalDigitsInputFilter implements InputFilter {
    Pattern mPattern;

    public DecimalDigitsInputFilter(int digitsBeforeZero, int digitsAfterZero)
    {
        mPattern = Pattern.compile("[0-9]{0,3}\\.[0-9]{0,1}||[0-9]{1,3}");
    }

    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend)
    {

        Matcher matcher = mPattern.matcher(dest);
        if(!matcher.matches())
            return "";
        return null;
    }
}
