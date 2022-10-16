package com.hienqp.changeorientationfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

public class InfoStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_student);

        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra("infoStudent");

        InfoStudentFragment infoStudentFragment = (InfoStudentFragment) getFragmentManager().findFragmentById(R.id.fragment_chitiet);
        infoStudentFragment.setInfo(student);
    }
}