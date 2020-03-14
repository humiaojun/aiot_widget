package edu.ecjtu.aiot_widget;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FancyWidgetActivity extends AppCompatActivity {

    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.seekbar)
    SeekBar seekBar;
    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.swth)
    Switch swth;

    @BindView(R.id.chkbox)
    CheckBox chkbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fancy_widget);
        ButterKnife.bind(this);

        progressBar.setIndeterminate(true);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Log.i("changed to ", Integer.toString(i));
            }
        });

        chkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.i("changed to ", Boolean.toString(b));
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("changed to", Integer.toString(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.i("start seeking", "");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i("stop seeking", "");
            }
        });
    }
}
