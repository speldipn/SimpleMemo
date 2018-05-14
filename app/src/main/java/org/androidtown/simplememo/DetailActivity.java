package org.androidtown.simplememo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
  public static final String MEMO_KEY = "555";
  private static boolean isModify = false;
  private static String modifyKey = "";
  EditText et;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    et = findViewById(R.id.editTextMemo);

    Intent intent = getIntent();
    String memo = intent.getStringExtra(MEMO_KEY);
    if(memo != null) {
      String[] contents = memo.split(Preference.DELIMETER);
      et.setText(contents[1].toString());
      modifyKey = contents[0];
      isModify = true;
    }
  }

  public void save(View v) {
    int result = RESULT_OK;
    // 1. 화면에서 입력된 글자를 가져오고
    String memo = et.getText().toString();
    // 2. 핸드폰에 글자를 저장한다.
    if(!isModify) {
      Preference.write(memo, this);
    } else {
      Preference.modify(modifyKey, memo, this);
      isModify = !isModify;
    }
    setResult(result);
    finish();
  }
}
