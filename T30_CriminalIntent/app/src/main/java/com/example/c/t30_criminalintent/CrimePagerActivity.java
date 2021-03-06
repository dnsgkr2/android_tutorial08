package com.example.c.t30_criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by c on 2015-08-23.
 */
public class CrimePagerActivity extends ActionBarActivity {

    private ViewPager mViewPager;
    private ArrayList<Crime> mCrimes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.myViewPager);
        setContentView(mViewPager);


        mCrimes = CrimeLab.get().getCrimes();

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Crime c = mCrimes.get(position);

                return CrimeFragment.newInstance(c.getld());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });


        UUID crimeId = (UUID) getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
        for(int i=0; i<mCrimes.size(); i++){
            if(mCrimes.get(i).getld().equals(crimeId)){
                mViewPager.setCurrentItem(i); // 현재 눌러진 아이템 확인
                break;
            }
        }



        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                Crime c = mCrimes.get(position);
                if(c.getTitle() != null){
                    setTitle(c.getTitle());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

}
