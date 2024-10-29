package com.example.componentesbasicos;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView tvRespuesta;
    CheckBox chkWindows, chkLinux, chkOtros;
    ImageButton imgButton;
    RadioGroup rgRespuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);;
        setContentView(R.layout.activity_main);
        inicializar_vistas();

        //listener para la chkWindows
        chkWindows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chkWindows.isChecked()) {
                    tvRespuesta.setText("Checkbox Windows seleccionada");
                } else {
                    tvRespuesta.setText("Checkbox Windows no seleccionada");
                }
            }
        });

        //listener para la chkLinux
        chkLinux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox)view).isChecked()) {
                    tvRespuesta.setText("Checkbox Linux seleccionada");
                } else {
                    tvRespuesta.setText("Checkbox Linux no seleccionada");
                }
            }
        });

        //listener para el evento onCheckedChange (sobre la chkOtros)
        chkOtros.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    imgButton.setVisibility(View.VISIBLE);
                } else {
                    imgButton.setVisibility(View.GONE);
                }
            }
        });

        //listener para el evento onCheckedChange (sobre el radioGroup)
        rgRespuesta.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.rb_si) {
                    tvRespuesta.setText("SI seleccionado");
                } else if (checkedId == R.id.rb_no) {
                    tvRespuesta.setText("NO seleccionado");
                } else if (checkedId == R.id.rb_nsnc) {
                    tvRespuesta.setText("NO SABE/NO CONTESTA seleccionado");
                }
            }
        });

    }//end onCreate

    private void inicializar_vistas() {
        tvRespuesta = findViewById(R.id.tv_respuesta);
        chkWindows = findViewById(R.id.chk_windows);
        chkLinux = findViewById(R.id.chk_linux);
        chkOtros = findViewById(R.id.chk_otros);
        imgButton = findViewById(R.id.img_btn);
        rgRespuesta = findViewById(R.id.rg_respuesta);
    }
}//end MainActivity