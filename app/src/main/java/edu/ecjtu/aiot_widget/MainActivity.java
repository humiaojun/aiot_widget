package edu.ecjtu.aiot_widget;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.text_info)
    TextView info;

    @BindView(R.id.btn_plus)
    Button btnPlus;

    @BindView(R.id.btn_minus)
    Button btnMinus;
    @BindView(R.id.img)
    ImageView img;

    TextView test;
    int cnt = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test = this.findViewById(R.id.test);

        test.setText("Test");
        ButterKnife.bind(this);

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cnt ++;
               info.setText(Integer.toString(cnt));
            }
        });
        btnMinus.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    btnMinus.setScaleX(1.f);
                    btnMinus.setScaleY(1.f);
                }
                else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    btnMinus.setScaleX(0.8f);
                    btnMinus.setScaleY(0.8f);
                }
                return false;
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cnt --;
                info.setText(Integer.toString(cnt));
            }
        });

        img.setImageDrawable(getDrawable(R.mipmap.ic_launcher_round));



        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "This is a toast", Toast.LENGTH_SHORT).show();
                BottomSheetDialog btmDialog = new BottomSheetDialog(MainActivity.this);
                btmDialog.setContentView(R.layout.page_btm_dialog);
                btmDialog.setCancelable(true);
                btmDialog.show();
//                new AlertDialog.Builder(MainActivity.this)
//                        .setTitle("dialog")
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                info.setText("You chose NO");
//                            }
//                        })
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                info.setText("You chose YES");
//                            }
//                        })
//                        .create().show();

            }
        });

    }
}
