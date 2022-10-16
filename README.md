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

- __XỬ LÝ ĐỐI VỚI FRAGMENT LIST (FRAGMENT BÊN TRÁI)__

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

___

__MÀN HÌNH NGANG - 1 ACTIVITY QUẢN LÝ 2 FRAGMENT__

- sau khi có ListFragment hiển thị danh sách tên các đối tượng Student, bây giờ để ListFragment này nằm trên Activity có orientation là horizontal ta sẽ thiết kế 1 layout cho MainActivity mặc định orientation là horizontal
- chuột phải __layout/New/Layout Resource File__
    - đặt tên cho layout nằm ngang trùng tên với layout mặc định của MainActivity
    - trong __Available qualifiers__ mục __Orientation__ chọn __landscape__
    - OK, ta sẽ được layout mặc định hiển thị màn hình ngang
- sau khi có layout cho màn hình ngang, ta thử gán cứng ListFragment vào MainActivity với layout landscape
- chú ý:
    - id của Fragment được gán cứng phải có
    - name của Fragment chính là tên của file quản trị Fragment
- __activity_main.xml__ kiểu lanscape
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <fragment
        android:id="@+id/fragment_list"
        android:name="com.hienqp.changeorientationfragment.ListStudentFragment"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
</LinearLayout>
```

- tiến hành run chương trình, ở layout mặc định ta sẽ thấy có 1 textview Hello World
- nhưng khi cho thiết bị xoay màn hình ngang, thì ListFragment sẽ được hiển thị

<img src="https://github.com/hienqp/ngay044-Fragment-ChangeOrientationFragment/blob/main/Screenshot_20221001_225127.png">

___

__FRAGMENT HIỂN THỊ THÔNG TIN CHI TIẾT__

- ở màn hình ngang ta sẽ có 1 Activity quản lý 2 Fragment, Fragment List quản lý danh sách tên các đối tượng Student ta đã hoàn thành
- trong phần này ta sẽ thực hiện thiết kế Fragment hiển thị thông tin chi tiết khi user click vào item trên Fragment List ở màn hình ngang
- Layout cho Fragment hiển thị thông tin chi tiết
- __fragment_student_info.xml__
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:background="@color/teal_200"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="10dp">

    <TextView
        android:id="@+id/textview_student_fullname"
        android:textSize="40sp"
        android:textColor="#E80909"
        android:text="Student Fullname"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>

    <TextView
        android:id="@+id/textview_student_birthyear"
        android:text="Student Birthyear"
        android:textSize="30sp"
        android:textColor="#1C2450"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>

    <TextView
        android:id="@+id/textview_student_address"
        android:text="Student Address"
        android:textSize="30sp"
        android:textColor="#1C2450"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>

    <TextView
        android:id="@+id/textview_student_email"
        android:layout_width="wrap_content"
        android:text="Student Email"
        android:textSize="30sp"
        android:textColor="#1C2450"
        android:layout_height="wrap_content"/>
</LinearLayout>
```
- File Java Logic Code của Fragment hiển thị thông tin chi tiết, vì đây chỉ là 1 Fragment hiển thị các TextView bình thường nên ta sẽ extends Fragment.app
- __InfoStudentFragment.java__
```java
package com.hienqp.changeorientationfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
```

___

__BỐ CỤC GIAO DIỆN CỦA MÀN HÌNH NGANG CHỨA 2 FRAGMENT__

- ở layout màn hình ngang ta để mặc định orientation là horizontal và ta đã gán cứng 1 Fragment List vào layout màn hình ngang
- bây giờ ta thêm 1 Fragment hiển thị thông tin chi tiết nằm bên phải của Fragment List
- để bố cục của 2 Fragment này hợp lý ta gán thuộc tính __weightSum__ cho toàn layout, và __layout_weight__ của mỗi Fragment
- vì sử dụng __weightSum__ và __layout_weight__ ở __orientation là horizontal__ nên __layout_width__ của mỗi Fragment sẽ là __0dp__
- __activity_main.xml__ (landscape)
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:weightSum="10">

    <fragment
        android:id="@+id/fragment_list"
        android:name="com.hienqp.changeorientationfragment.ListStudentFragment"
        android:layout_width="0dp"
        android:layout_height="match_parent"
        android:layout_weight="4" />

    <fragment
        android:id="@+id/fragment_info"
        android:name="com.hienqp.changeorientationfragment.InfoStudentFragment"
        android:layout_width="0dp"
        android:layout_height="match_parent"
        android:layout_weight="6" />
</LinearLayout>
```

___

__TRUYỀN DỮ LIỆU TỪ FRAGMENT LIST SANG FRAGMENT HIỂN THỊ THÔNG TIN__

- ta đã hoàn tất xây dựng 
    - Fragment List
    - Fragment hiển thị chi tiết
    - Layout cho Activity màn hình ngang
- vấn đề bây giờ làm sao để khi user click vào item trên Fragment List thì sẽ hiển thị thông tin chi tiết của item đó trên Fragment hiển thị
- để giải quyết vấn đề truyền dữ liệu trước tiên ta sẽ thử nghiệm bắt sự kiện user click vào item trên Fragment List có hiển thị tên của item hay thuộc tính name của đối tượng item Student đó hay không
- trong __ListStudentFragment.java__ ta override method ``onListItemClick()``, method này sẽ truyền vào 1 đối số là position của item trên List đang được click, mà có position ta sẽ lấy được thông tin của đối tượng Student trong mảng Student, ta sẽ Toast 1 thông báo hiển thị tên của đối tượng đang được click nếu đúng với tên của item đang hiển thị trên list thì ta đã bắt sự kiện thành công
```java
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Toast.makeText(getActivity(), studentArrayList.get(position).getmFullname(), Toast.LENGTH_SHORT).show();
    }
```

<img src="https://github.com/hienqp/ngay044-Fragment-ChangeOrientationFragment/blob/main/Screenshot_20221005_231003.png">

- ở trên là ta mới chỉ bắt được sự kiện click vào item trên Fragment List
- để có thể truyền dữ liệu chính xác từ Fragment List sang Fragment hiển thị thì thông thường ta sẽ dùng ``Interface`` để tạo callback trả về 1 data nào đó thông qua 1 method ta xây dựng, data ở đây là 1 object Student
- __TransferStudent.java__
```java
package com.hienqp.changeorientationfragment;

public interface TransferStudent {
    public void dataStudent(Student student);
}
```
- để sử dụng:
    - ở Fragment List sẽ gọi method ``TransferStudent.dataStudent(Student)`` và truyền vào đối số là object Student
    - ở MainActivity sẽ implements interface ``TransferStudent``, override method ``dataStudent()`` và nhận tham số là object Student được truyền vào bởi Fragment List
    - khi MainActivity có được object Student thì việc gọi Fragment hiển thị và truyền data object Student cho Fragment đó

- __XỬ LÝ Ở FRAGMENT LIST VỚI INTERFACE__
- ở Fragment List ta chỉnh sửa ở 3 vị trí sau
    - khai báo biến toàn cục interface
    - trong onCreateView gán biến interface cho ``getActivity()``
    - trong method sự kiện click item gọi method abstract của biến interface và truyền vào object Student cho method đó
```java
package com.hienqp.changeorientationfragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListStudentFragment extends ListFragment {
    StudentAdapter studentAdapter;
    ArrayList<Student> studentArrayList;
    
    TransferStudent transferStudent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        transferStudent = (TransferStudent) getActivity();
        
        studentArrayList = new ArrayList<>();
        addArrayStudent();
        studentAdapter = new StudentAdapter(getActivity(), R.layout.item_student, studentArrayList);
        setListAdapter(studentAdapter);

        return inflater.inflate(R.layout.fragment_student_list, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Toast.makeText(getActivity(), studentArrayList.get(position).getmFullname(), Toast.LENGTH_SHORT).show();

        transferStudent.dataStudent(studentArrayList.get(position));
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


- __MAIN ACTIVITY IMPLEMENTS INTERFACE__
- ở MainActivity ta thực hiện
    - implements interface đã dựng
    - override method abstract của interface
        - method này sẽ nhận data là object Student (Fragment List là bên truyền)
        - trong method override sẽ dựng Fragment hiển thị thông tin của object Student nhận được
- __MainActivity.java__
```java
package com.hienqp.changeorientationfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements TransferStudent {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void dataStudent(Student student) {
        InfoStudentFragment infoStudentFragment = (InfoStudentFragment) getFragmentManager().findFragmentById(R.id.fragment_info);
        infoStudentFragment.setInfo(student);
    }
}
```

___
___

__XỬ LÝ CHUYỂN ĐỔI ORIENTATION SANG MÀN HÌNH DỌC__

- ở màn hình dọc
    - đầu tiên Activity sẽ hiển thị 1 Fragment List
    - khi user click vào item trên Fragment List thì sẽ gọi Activity chứa Fragment thông tin chi tiết
- như vậy ta cần chuẩn bị những thành phần sau:
    - layout cho MainActivity ở __Orientation Portrait__
    - Java file và Layout của Activity dùng để quản lý Fragment thông tin chi tiết
- cách thức hoạt động giữa các thành phần ở màn hình Portrait
    - MainActivity sẽ hiển thị Fragment List
    - user click vào item trên Fragment List thì sẽ Start Activity quản lý Fragment thông tin chi tiết
    - dùng Intent để truyền object Student sang Activity được Start (Student sẽ implements Serializable)
    - Activity được Start sẽ nhận object Student thông qua Intent

> lưu ý:
>> ở orientation Landscape, layout của cả 2 fragment đều tồn tại trên activity, nên việc gọi method setInfo() không phát sinh lỗi
>> nhưng orientation Portrait, chỉ layout của fragment list được dựng, còn layout fragment thông tin chưa được dựng (null), nên việc gọi method setInfo() sẽ phát sinh lỗi, vì không có layout để setInfo() tác động lên các view của fragment thông tin
>>> để giải quyết, ta cần kiểm tra orientation hiện tại có phải là Landscape hay Portrait, nếu là Portrait thì sẽ truyền object Student cho Activity thông tin, Activity thông tin sẽ dựng fragment thông tin và gọi setInfo() để setup các giá trị tương ứng lên các view của fragment thông tin


IMPLEMENTS SERIALIZABLE CHO CLASS STUDENT

- để truyền 1 data kiểu object thông qua Intent, thì class của object đó phải được __implements Serializable__
- ở class Student ta thực hiện implements Serializable
- __Student.java__
```java
public class Student implements Serializable {
    // .....
}
```

ACTIVITY QUẢN LÝ FRAGMENT THÔNG TIN

- tiến hành tạo 1 Activity dùng để quản lý Fragment thông tin (gồm file java và xml)
- sau đó ta tiến hành thiết kế layout của Activity này chứa Fragment thông tin đã thiết kế trước đó
- __activity_info_student.xml__
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".InfoStudentActivity">

    <fragment
        android:id="@+id/fragment_chitiet"
        android:name="com.hienqp.changeorientationfragment.InfoStudentFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
</androidx.constraintlayout.widget.ConstraintLayout>
```

ACTIVITY MAIN (PORTRAIT) QUẢN LÝ FRAGMENT LIST

- Activity Main ta sẽ có 2 layout
    - 1 layout landscape chứa 2 Fragment list và thông tin (đã thiết kế xong)
    - 1 layout portrait chỉ chứa Fragment list
- tiến hành thiết kế layout portrait chứa Fragment list
- __activity_main.xml__ (portrait)
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <fragment
        android:id="@+id/fragment_danhsach"
        android:name="com.hienqp.changeorientationfragment.ListStudentFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

</androidx.constraintlayout.widget.ConstraintLayout>
```

THIẾT LẬP LOGIC CHO MAIN ACTIVITY KHI CHUYỂN ĐỔI ORIENTATION

- khi orientation là landscape
    - chỉ có 1 activity main (landscape)
    - activity quản lý 2 fragment list và thông tin
    - các logic diễn ra bình thường
- khi chuyển orientation sang portrait
    - thì activity main (portrait) chỉ quản lý fragment list
    - khi user click vào item trên fragment list, setInfo() sẽ được gọi
    - nhưng lúc này setInfo() không thể setup giá trị cho các view trên fragment thông tin, vì fragment thông tin lúc này chưa được dựng lên (null) sẽ phát sinh lỗi
    - để giải quyết, khi callback dataStudent() được gọi, sẽ kiểm tra orientation là portrait hay landscape
    - nếu là portrait thì sẽ truyền object Student cho Activity thông tin, để Activity thực hiện dựng fragment thông tin và gọi setInfo() setup các giá trị lên các view
- __MainActivity.java__
```java
package com.hienqp.changeorientationfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements TransferStudent {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void dataStudent(Student student) {
        InfoStudentFragment infoStudentFragment = (InfoStudentFragment) getFragmentManager().findFragmentById(R.id.fragment_info);

        Configuration configuration = getResources().getConfiguration();
        
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Intent intent = new Intent(MainActivity.this, InfoStudentActivity.class);
            intent.putExtra("infoStudent", student);
            startActivity(intent);
        } else {
            infoStudentFragment.setInfo(student);
        }
    }
}
```

THIẾT LẬP LOGIC CHO ACTIVITY THÔNG TIN KHI ĐƯỢC START BỞI MAIN ACTIVITY

- ở InfoStudentActivity, khi được start bởi Main Activity
    - sẽ getIntent()
    - có Intent, tiến hành lấy ra object Student được gửi qua bởi Main Activity
    - dựng Fragment thông tin và gọi setInfo() để setup các giá trị lên các view

- __InfoStudentActivity.java__
```java
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
```