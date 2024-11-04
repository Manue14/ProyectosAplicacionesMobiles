package com.example.componentesbasicos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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
    EditText etSumando1, etSumando2;
    Button btnSumar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);;
        setContentView(R.layout.activity_main);
        inicializar_vistas();

        //listener del btnSumar
        btnSumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkIfInt(etSumando1.getText().toString()) &&
                checkIfInt(etSumando2.getText().toString())) {
                    int sumando1 = Integer.parseInt(etSumando1.getText().toString());
                    int sumando2 = Integer.parseInt(etSumando2.getText().toString());
                    tvRespuesta.setText("La suma es:" + (sumando1 + sumando2));
                } else {
                    Toast.makeText(getApplicationContext(), "Introduzca valores válidos", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
        btnSumar = findViewById(R.id.btn_sumar);
        etSumando1 = findViewById(R.id.et_sumando1);
        etSumando2 = findViewById(R.id.et_sumando2);
    }

    private boolean checkIfInt(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}//end MainActivity