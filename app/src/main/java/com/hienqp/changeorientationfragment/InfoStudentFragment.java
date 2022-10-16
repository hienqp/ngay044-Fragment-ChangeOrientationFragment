package com.hienqp.changeorientationfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class InfoStudentFragment extends Fragment {
    TextView tvFullname, tvBirthyear, tvAdress, tvEmail;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_student_info, container, false);
        AnhXa();
        return view;
    }

    public void setInfo(Student student) {
        tvFullname.setText(student.getmFullname());
        tvBirthyear.setText("Năm sinh: " + student.getmBirthyear());
        tvAdress.setText("Địa chỉ: " + student.getmAddress());
        tvEmail.setText("Email: " + student.getmEmail());
    }

    public void AnhXa() {
        tvFullname = view.findViewById(R.id.textview_student_fullname);
        tvBirthyear = view.findViewById(R.id.textview_student_birthyear);
        tvAdress = view.findViewById(R.id.textview_student_address);
        tvEmail = view.findViewById(R.id.textview_student_email);
    }
}
