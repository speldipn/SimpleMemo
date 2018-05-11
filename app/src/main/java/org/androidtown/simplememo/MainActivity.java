package org.androidtown.simplememo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

  public static final String LOG_TAG = "SimpleMemo";
  RecyclerView recycler;
  CustomAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    recycler = findViewById(R.id.recyclerView);
    List<String> list = Preference.getList(this);
    adapter = new CustomAdapter();
    recycler.setAdapter(adapter);
    recycler.setLayoutManager(new LinearLayoutManager(this));
    adapter.setDataAndRefresh(list);
  }

  @Override
  protected void onResume() {
    super.onResume();
    List<String> list = Preference.getList(this);
    adapter.setDataAndRefresh(list);
  }

  public void goPost(View v) {
    // 1. Intent 생성 : 명시적 인텐트, 묵시적(ACTION_CALL)
    // 시스템 메세지 클래스
    Intent intent = new Intent(getBaseContext(), DetailActivity.class);

    // 2. 시스템에 인텐트 전달
    startActivity(intent);

    // 3.

  }
}
