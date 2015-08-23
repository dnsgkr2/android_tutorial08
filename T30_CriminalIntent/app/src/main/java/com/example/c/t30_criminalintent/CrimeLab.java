package com.example.c.t30_criminalintent;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by c on 2015-08-22.
 * 어레이리스트를 담을수 있는 클래스  싱글턴... 스태틱특성이용해서 생성(프로그램에서 하나만 존재...
 * 프로그램 실행될때 미리 한번만 로딩
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
             c.setTitle("범죄 " + i);
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
