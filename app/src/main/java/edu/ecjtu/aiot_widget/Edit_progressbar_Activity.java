package edu.ecjtu.aiot_widget;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Edit_progressbar_Activity extends AppCompatActivity {
    @BindView(R.id.edit_progressbar)
    ProgressBar  progressBar;

    @BindView(R.id.increase)
    Button btn_increase;

    @BindView(R.id.reduce)
    Button btn_reduce;

    @BindView(R.id.submit)
    Button btn_submit;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressbar_edit);
//        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        progressBar.setProgress(getIntent().getIntExtra("progress",0));

        btn_increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = progressBar.getProgress();
                progressBar.setProgress(n + 5);
            }
        });
        btn_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = progressBar.getProgress();
                progressBar.setProgress(n - 5);
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("progress",progressBar.getProgress());
                intent.putExtra("pos", getIntent().getIntExtra("pos", 0));
                setResult(1111,intent);
                finish();
            }
        });

    }
}
