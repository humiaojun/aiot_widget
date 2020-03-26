package edu.ecjtu.aiot_widget;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class indexActivity extends AppCompatActivity {

    @BindView(R.id.page_list_view)
    RecyclerView list;

//    @BindView(R.id.list_view)
//    RecyclerView list;

    List<ContactInfo> contacList = new LinkedList<>();
    ListAdapater adapter;

    @BindView(R.id.btn_add_thing)
    Button btnAdd_thing;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent intent = getIntent();
        if (resultCode == 0000) {
            if (data != null) {
                ContactInfo contactInfo = (ContactInfo) data.getSerializableExtra("ContactInfo");
                contacList.add(contactInfo);
                Collections.sort(contacList);
                adapter.notifyDataSetChanged();
            }
        }
        if (resultCode == 1111){
            if (data != null){
                int pos = data.getIntExtra("pos",0);
                int pro = data.getIntExtra("progress",0);
                contacList.get(pos).setProgress(pro);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_index);
//        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        LinearLayoutManager listLayoutMgr = new LinearLayoutManager(this);
        listLayoutMgr.setOrientation(RecyclerView.VERTICAL);

        list.setLayoutManager(listLayoutMgr);

        adapter = new ListAdapater(contacList);
//
        list.setAdapter(adapter);
        list.setItemAnimator(new DefaultItemAnimator());


        //这里给RecyclerView设置一下就OK拉
        ItemTouchHelperCallback touchHelperCallback = new ItemTouchHelperCallback();
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(touchHelperCallback);
        itemTouchHelper.attachToRecyclerView(list);

//        Intent intent = getIntent();
//        if (intent != null) {
//            ContactInfo contactInfo = (ContactInfo) intent.getSerializableExtra("ContactInfo");
//            contacList.add(contactInfo);
//            Collections.sort(contacList);
//            adapter.notifyDataSetChanged();
//        }

        btnAdd_thing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(indexActivity.this, ListActivity.class);
                startActivityForResult(intent,0000);
//                adapter.notifyDataSetChanged();
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
                holder.progressBar.setProgress(info.getProgress());
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
        ProgressBar progressBar;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            v = itemView;
            name = itemView.findViewById(R.id.thing);
            tele = itemView.findViewById(R.id.date);
            btn = itemView.findViewById(R.id.btn_complete);
            progressBar = itemView.findViewById(R.id.item_progressbar);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(indexActivity.this,Edit_progressbar_Activity.class);
                    intent.putExtra("pos",pos);
                    intent.putExtra("progress",progressBar.getProgress());
                    startActivityForResult(intent,1111);
                }
            });
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





