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

        // có thể thay thế bằng điều kiện infoStudentFragment.isInLayout() để kiểm tra fragment thông tin có đang tồn tại trên layout hay không (ở landscape là có, portrait là không)
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Intent intent = new Intent(MainActivity.this, InfoStudentActivity.class);
            intent.putExtra("infoStudent", student);
            startActivity(intent);
        } else {
            infoStudentFragment.setInfo(student);
        }
    }
}