package com.example.c.t30_criminalintent;

import android.os.Bundle;
import android.support.v4.app.ListFragment;

import java.util.ArrayList;

/**
 * Created by c on 2015-08-23.
 * ����Ʈ�並 ����ִ� �����׸�Ʈ
 */
public class CrimeListFragment extends ListFragment{
    private ArrayList<Crime> mCrimes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().setTitle("���� ����Ʈ");
        mCrimes = CrimeLab.get().getCrimes();

    }
}
