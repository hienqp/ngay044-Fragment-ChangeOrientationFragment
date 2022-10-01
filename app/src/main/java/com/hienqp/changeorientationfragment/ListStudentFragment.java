package com.hienqp.changeorientationfragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ListStudentFragment extends ListFragment {
    StudentAdapter studentAdapter;
    ArrayList<Student> studentArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        studentArrayList = new ArrayList<>();
        addArrayStudent();
        studentAdapter = new StudentAdapter(getActivity(), R.layout.item_student, studentArrayList);
        setListAdapter(studentAdapter);

        return inflater.inflate(R.layout.fragment_student_list, container, false);
    }

    private void addArrayStudent() {
        studentArrayList.add(new Student("Nguyễn Văn A", 1990, "Hồ Chí Minh", "nva@gmail.com"));
        studentArrayList.add(new Student("Nguyễn Văn B", 1992, "Cà Mau", "nvb@gmail.com"));
        studentArrayList.add(new Student("Nguyễn Văn C", 1993, "Lạng Sơn", "nvc@gmail.com"));
        studentArrayList.add(new Student("Nguyễn Văn D", 1994, "Bắc Cạn", "nvd@gmail.com"));
        studentArrayList.add(new Student("Nguyễn Văn E", 1995, "Bình Thuận", "nve@gmail.com"));
        studentArrayList.add(new Student("Nguyễn Văn F", 1996, "Khánh Hòa", "nvf@gmail.com"));
        studentArrayList.add(new Student("Nguyễn Văn G", 1997, "Nghệ An", "nvg@gmail.com"));
        studentArrayList.add(new Student("Nguyễn Văn H", 1998, "Trà Vinh", "nvh@gmail.com"));
        studentArrayList.add(new Student("Nguyễn Văn I", 1999, "Cao Bằng", "nvi@gmail.com"));
        studentArrayList.add(new Student("Nguyễn Văn J", 2000, "Hà Nội", "nvj@gmail.com"));
        studentArrayList.add(new Student("Nguyễn Văn K", 2001, "Phú Yên", "nvk@gmail.com"));
    }
}
