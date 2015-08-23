package com.example.c.t30_criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by c on 2015-08-22.
 */
public class Crime {
    private String mTitle;
    private UUID mld;
    private Date mDate;
    private boolean mSolved;





    public Crime(){
        mld = UUID.randomUUID();
        mDate = new Date();
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public UUID getld() {
        return mld;
    }

    public void setld(UUID ld) {
        this.mld = ld;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }


    @Override
    public String toString() {
        return mTitle;
    }
}
