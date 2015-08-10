package com.niharinfo.anyservice;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.niharinfo.anyservice.FragmentHelper.FragmentFixedDepositCalculater;
import com.niharinfo.anyservice.FragmentHelper.HomeLoanPreApprovalFragment;
import com.niharinfo.anyservice.Utilities.NiceSpinner;

import java.util.ArrayList;
import java.util.Arrays;


public class HomeLoanPreApprovalActivity extends ActionBarActivity {

    Toolbar toolbarHomeLoan;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_loan_pre_approval);
        toolbarHomeLoan = (Toolbar)findViewById(R.id.toolbarHomeLoan);
        frameLayout = (FrameLayout)findViewById(R.id.frmHomeLoanApprovalContainer);
        setSupportActionBar(toolbarHomeLoan);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.nav);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);
        getSupportFragmentManager().beginTransaction().add(R.id.frmHomeLoanApprovalContainer,new HomeLoanPreApprovalFragment(),"Home Loan Approval").commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_loan_pre_approval, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == android.R.id.home){
            Toast.makeText(HomeLoanPreApprovalActivity.this,"Nav",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
