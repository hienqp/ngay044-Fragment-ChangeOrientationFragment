 __FRAGMENT & THAY ĐỔI BỐ CỤC GIAO DIỆN__

 - đối với 1 số app, ta cần tối ưu thiết kế giao diện khi màn hình hiển thị dọc hoặc ngang, như hình ảnh dưới đây

 <img src="https://github.com/hienqp/ngay044-Fragment-ChangeOrientationFragment/blob/main/Screenshot%202022-09-28%20214654.png">

 - ở phần này ta cần thiết kế giao diện cho cả 2 loại màn hình hiển thị dọc và ngang
 - khi ở màn hình ngang:
    - ta có 1 Activity quản lý 2 Fragment
    - Fragment bên trái hiển thị danh sách tên Student
    - Fragment bên phải hiển thị thông tin chi tiết của item Student được chọn từ danh sách bên trái
- khi ở màn hình dọc:
    - ta có 1 Activity quản lý 1 Fragment danh sách Student
    - 1 Activity quản lý 1 Fragment hiển thị thông tin chi tiết của item được chọn từ Fragment danh sách từ Activity đầu tiên
- như vậy để thực hiện phần này ta sẽ sử dụng
    - Fragment làm ListView
    - Adpater
    - Data Student
 ___

__MÀN HÌNH NGANG - 1 ACTIVITY QUẢN LÝ 2 FRAGMENT__

XỬ LÝ ĐỐI VỚI FRAGMENT LIST (FRAGMENT BÊN TRÁI)

- vì Fragment List sẽ quản lý danh sách các item Student dựa và Data các Student, ta sẽ tạo 1 class model Student
-__Student.java__
```java
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
```

- thiết kế Layout cho Fragment bên trái làm ListView
- lưu ý id của ListView trong Fragment sẽ là id mặc định của android __"@android:id/list"__
- __fragment_student_list.xml__
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ListView
        android:id="@android:id/list"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
</LinearLayout>
```

- file Java cho Fragment bên trái làm ListView
- __ListStudentFragment.java__
```java
package com.hienqp.changeorientationfragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListStudentFragment extends ListFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_student_list, container, false);
    }
}
```

- tạo layout của từng item sẽ hiển thị trên Fragment List, và các item này sẽ được Adapter quản lý từ Data Student đổ data ra Fragment List
- __item_student.xml__
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ImageView
        android:id="@+id/iv_icon_student"
        android:layout_width="50dp"
        android:layout_height="50dp"
        android:layout_margin="5dp"
        android:src="@drawable/ic_student" />

    <TextView
        android:id="@+id/tv_fullname"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft="10dp"
        android:layout_marginTop="15dp"
        android:text="Họ tên Sinh viên"
        android:textColor="#E60909"
        android:textSize="20sp" />

</LinearLayout>
```
- icon của item này để cho đơn giản ta cho hình PNG download từ internet

- tạo Apdapter để quản lý đổ data ra ListView
- __StudentAdapter.java__
```java
package com.hienqp.changeorientationfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
    private Context mContext;
    private int mLayout;
    private List<Student> studentList;

    public StudentAdapter(Context mContext, int mLayout, List<Student> studentList) {
        this.mContext = mContext;
        this.mLayout = mLayout;
        this.studentList = studentList;
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView tvFullname;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mLayout, null);
            viewHolder.tvFullname = convertView.findViewById(R.id.tv_fullname);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Student student = studentList.get(position);
        viewHolder.tvFullname.setText(student.getmFullname());

        return convertView;
    }
}
```

> ta có 
>> class Student
>> class StudentAdapter
>> layout item_student

- sau khi có những phần trên ta quay trở lại chỉnh sửa file Fragment ListView
- __ListStudentFragment.java__
```java
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
```
