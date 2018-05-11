package org.androidtown.simplememo;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Holder> {

  List<String> list = new ArrayList<>();

  public void setDataAndRefresh(List<String> list) {
    this.list = list;
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.list_item, parent, false);
    return new Holder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    // 7.1 데이터 목록에 이쓴 데이터를 개별로 한개씩 꺼낸다.
    String memo = list.get(position);
    holder.memo.setText(memo);
    holder.no.setText(position + 1 + "");
  }

  @Override
  public int getItemCount() {
    return list.size();
  }

  // 1. Holder를 마는다.
  public class Holder extends RecyclerView.ViewHolder {
    TextView no;
    TextView memo;
    TextView date;
    ConstraintLayout item;

    public Holder(View itemView) {
      super(itemView);
      no = itemView.findViewById(R.id.textNo);
      memo = itemView.findViewById(R.id.textMemo);
      date = itemView.findViewById(R.id.textDate);
      item = itemView.findViewById(R.id.item);
    }
  }
}