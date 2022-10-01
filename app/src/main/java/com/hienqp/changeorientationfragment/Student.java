package com.hienqp.changeorientationfragment;

public class Student {
    private String mFullname;
    private int mBirthyear;
    private String mAddress;
    private String mEmail;

    public Student(String mFullname, int mBirthyear, String mAddress, String mEmail) {
        this.mFullname = mFullname;
        this.mBirthyear = mBirthyear;
        this.mAddress = mAddress;
        this.mEmail = mEmail;
    }

    public String getmFullname() {
        return mFullname;
    }

    public void setmFullname(String mFullname) {
        this.mFullname = mFullname;
    }

    public int getmBirthyear() {
        return mBirthyear;
    }

    public void setmBirthyear(int mBirthyear) {
        this.mBirthyear = mBirthyear;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }
}
