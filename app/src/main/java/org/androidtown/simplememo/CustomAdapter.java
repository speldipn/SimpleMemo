package org.androidtown.simplememo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.DESedeKeySpec;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Holder> {

  List<String> list = new ArrayList<>();
  static int count = 0;
  private CallBack callback;
  private MainActivity activity;

  public CustomAdapter(CallBack callBack) {
    this.callback = callBack;
  }

  public CustomAdapter(MainActivity activiy) {
    this.activity = activiy;
  }

  public void setDataAndRefresh(List<String> list) {
    this.list = list;
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.list_item, parent, false);

    Log.d(MainActivity.LOG_TAG, "onCreateViewHolder: " + (++count));
    return new Holder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    // 7.1 데이터 목록에 이쓴 데이터를 개별로 한개씩 꺼낸다.
    String memo = list.get(position);
    holder.no.setText(position + 1 + "");
    String[] contents = memo.split(Preference.DELIMETER);
    holder.memo.setText(contents[0]);
    holder.curTime.setText(contents[1]);
    holder.key = contents[2]; // key;

    Log.d(MainActivity.LOG_TAG, "onBind() position: " + position);
  }

  @Override
  public int getItemCount() {
    return list.size();
  }

  // 20180514 TODO: Interface를 이용한 방식은 사용안됨.
  // MainActivity에서 사용할 인터페이스를 만들어준다.
  public interface CallBack {
    public void goEdit(String memoKey);
  }

  // 1. Holder를 마는다.
  public class Holder extends RecyclerView.ViewHolder {
    TextView no;
    TextView memo;
    TextView curTime;
    ConstraintLayout item;
    Button btnDel;
    Button btnModify;
    String key;

    public Holder(View itemView) {
      super(itemView);
      no = itemView.findViewById(R.id.textNo);
      memo = itemView.findViewById(R.id.textMemo);
      curTime = itemView.findViewById(R.id.textDate);
      item = itemView.findViewById(R.id.item);
      item.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          goEdit(key, memo.getText().toString());
        }
      });

      btnDel = itemView.findViewById(R.id.btnDel);
      btnDel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          int position = Integer.parseInt(no.getText().toString()) - 1;
          Preference.remove(key, item.getContext());
          list.remove(position);
          CustomAdapter.this.notifyItemRemoved(position);
        }
      });

//      btnModify = itemView.findViewById(R.id.btnModify);
//      btnModify.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//          int position = Integer.parseInt(no.getText().toString()) - 1;
//          String memo = list.get(position);
//          Intent intent = new Intent(v.getContext(), DetailActivity.class);
//          intent.putExtra(DetailActivity.MEMO_KEY, memo.toString());
//          v.getContext().startActivity(intent);
//        }
//      });
    }
  }

  public void goEdit(String key, String memo) {
    Intent intent = new Intent(activity.getBaseContext(), DetailActivity.class);
    intent.putExtra(DetailActivity.MEMO_KEY, key + Preference.DELIMETER + memo);
    activity.startActivityForResult(intent, MainActivity.REQUEST_CODE);
  }
}