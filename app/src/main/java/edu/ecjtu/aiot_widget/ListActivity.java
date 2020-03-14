package edu.ecjtu.aiot_widget;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListActivity extends AppCompatActivity {

    @BindView(R.id.list_view)
    RecyclerView list;

    List<ContactInfo> contacList = new LinkedList<>();
    ListAdapater adapter;

    @BindView(R.id.edit_name)
    EditText editName;

    @BindView(R.id.date)
    DatePicker editTele;

    @BindView(R.id.btn_add)
    Button btnAdd;

//    @BindView(R.id.btn_complete)
//    Button btncomplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        LinearLayoutManager listLayoutMgr = new LinearLayoutManager(this);
        listLayoutMgr.setOrientation(RecyclerView.VERTICAL);

        list.setLayoutManager(listLayoutMgr);

        adapter = new ListAdapater(contacList);

        list.setAdapter(adapter);
        list.setItemAnimator(new DefaultItemAnimator());

        adapter.notifyDataSetChanged();

        //这里给RecyclerView设置一下就OK拉
        ItemTouchHelperCallback touchHelperCallback = new ItemTouchHelperCallback();
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(touchHelperCallback);
        itemTouchHelper.attachToRecyclerView(list);

//        点击Add添加待办事项
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContactInfo info = new ContactInfo();
                info.setName(editName.getText().toString());
                info.setTele(editTele.getYear()+"-"+(editTele.getMonth()+1)+"-"+editTele.getDayOfMonth());
                contacList.add(info);
                Collections.sort(contacList);
                adapter.notifyDataSetChanged();
                editName.setText("");
            }
        });

        editName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                if(keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    editName.setText(editName.getText());
                    editTele.requestFocus();
                }
                return false;
            }
        });
    }

    private class ListAdapater extends RecyclerView.Adapter<ListViewHolder> {

        List<ContactInfo> contactInfos;
        public ListAdapater(List<ContactInfo> contactInfos) {
            this.contactInfos = contactInfos;
        }

        @NonNull
        @Override
        public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(R.layout.item_contactinfo, parent, false);
            ListViewHolder holder = new ListViewHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
            if (contacList != null) {
                final ContactInfo info = contacList.get(position);
                holder.name.setText(info.getName());
                holder.tele.setText(info.getTele());
                holder.pos = position;
            }
//            点击完成删除
            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contacList.remove(position);
                    adapter.notifyDataSetChanged();
                }
            });

        }

        @Override
        public int getItemCount() {
            return this.contactInfos.size();
        }

    }

    private class ListViewHolder extends RecyclerView.ViewHolder {
        View v;
        TextView name;
        TextView tele;
        int pos;
        Button btn;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            v = itemView;
            name = itemView.findViewById(R.id.thing);
            tele = itemView.findViewById(R.id.date);
            btn = itemView.findViewById(R.id.btn_complete);
        }
//        滑动删除
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            adapter.notifyItemRemoved(position);
            contacList.remove(position);
        }
    }

    public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {
        //是否支持侧滑
        @Override
        public boolean isItemViewSwipeEnabled() {
            return true;
        }

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(0, ItemTouchHelper.START);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            adapter.notifyItemRemoved(position);
            contacList.remove(position);
        }

//        @Override
//        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//            return;
//        }

        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);

        }
    }


}
