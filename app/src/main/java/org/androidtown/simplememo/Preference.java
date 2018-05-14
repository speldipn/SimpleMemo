package org.androidtown.simplememo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Preference {
  public static final String FILENAME = "memo.txt";
  public static final String MEMO_COUNT = "memo_count";
  public static final String MEMO_TOTAL = "memo_total";
  public static final String DELIMETER = ":::";
  private static int MEMOCOUNT = 0;

  public static int increaseCount(Context context) {
    SharedPreferences sharedPref = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
    int no = sharedPref.getInt(MEMO_TOTAL, 0) + 1;
    sharedPref.edit().commit();
    MEMOCOUNT = no;
    return no;
  }

  public static String getTimeToString() {
    Calendar c = Calendar.getInstance();
    String curTime = c.getTime() + "";
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String result = df.format(c.getTime());
    return result;
  }

  public static void write(String memo, Context context) {
    int no = increaseCount(context);
    String key = MEMO_COUNT + no;
    Calendar c = Calendar.getInstance();
    memo = memo + Preference.DELIMETER + getTimeToString() + Preference.DELIMETER + key;
    putString(key, memo, context);
    setCount(context);
  }

  public static void modify(String key, String memo, Context context) {
    Calendar c = Calendar.getInstance();
    memo = memo + Preference.DELIMETER + getTimeToString() + Preference.DELIMETER + key;
    putString(key, memo, context);
  }

  public static void putString(String key, String memo, Context context) {
    SharedPreferences sharedPref = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
    sharedPref.edit().putString(key, memo).commit();
  }

  public static void setCount(Context context) {
    SharedPreferences sharedPref = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
    sharedPref.edit().putInt(MEMO_TOTAL, MEMOCOUNT).commit();
  }

  public static int getCount(Context context) {
    SharedPreferences sharedPref = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
    MEMOCOUNT = sharedPref.getInt(MEMO_TOTAL, 0);
    return MEMOCOUNT;
  }

  public static String read(String key, Context context) {
    SharedPreferences sharedPref = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
    return sharedPref.getString(key, "Empty Memo");
  }

  public static List<String> getList(Context context) {
    String memo = null;
    ArrayList<String> list = new ArrayList<>();
    int count = Preference.getCount(context);
    for (int i = count; i >= 1; --i) {
      memo = Preference.read(MEMO_COUNT + i, context);
      if(memo != null && !"Empty Memo".equals(memo)) {
        list.add(memo);
      }
    }
    return list;
  }

  // 삭제
  public static void remove(String key, Context context) {
    SharedPreferences sharedPref = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
    sharedPref.edit().remove(key).commit();
  }

  // 수정
  public static void modify(String key, Context context) {

  }
}
