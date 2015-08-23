package com.example.c.t30_criminalintent;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by c on 2015-08-22.
 * ��̸���Ʈ�� ������ �ִ� Ŭ����  �̱���... ����ƽƯ���̿��ؼ� ����(���α׷����� �ϳ��� ����...
 * ���α׷� ����ɶ� �̸� �ѹ��� �ε�
 */
public class CrimeLab {
    private ArrayList<Crime> mCrimes;
    private static CrimeLab sCrimeLab;

    public static CrimeLab get(){
        if(sCrimeLab == null){
            sCrimeLab = new CrimeLab();
        }
        return sCrimeLab;
    }

    public CrimeLab(){
        mCrimes = new ArrayList<Crime>();
         for(int i =0; i<100; i++){
             Crime c = new Crime();
             c.setTitle("���� " + i);
             c.setSolved(i%2==0);
             mCrimes.add(c);
         }
    }

    public ArrayList<Crime> getCrimes(){
        return mCrimes;
    }

    public Crime getCrime(UUID id){
        for(Crime c : mCrimes){
            if(c.getld().equals(id))
                return c;
        }
        return null;
    }
}
