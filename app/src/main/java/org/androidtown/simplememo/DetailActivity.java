package org.androidtown.simplememo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DetailActivity extends AppCompatActivity {

  EditText et;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    et = findViewById(R.id.editTextMemo);
  }

  public void save(View v) {
    // 1. 화면에서 입력된 글자를 가져오고
    String memo = et.getText().toString();
    // 2. 핸드폰에 글자를 저장한다.
    Preference.write(memo, this);

    finish();
  }

  // 메모를 삭제
  public void remove(View v) {
    //
  }

  // 메모를 수정
  public void modify(View v) {

  }
}
