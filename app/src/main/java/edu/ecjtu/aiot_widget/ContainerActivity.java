package edu.ecjtu.aiot_widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ContainerActivity extends AppCompatActivity {
    @BindView(R.id.card)
    CardView card;

//    @BindView(R.id.page_inflate)
    LinearLayout page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        ButterKnife.bind(this);

        LayoutInflater inflater = getLayoutInflater();
        page.addView(inflater.inflate(R.layout.page_inflate, null));
    }
}
