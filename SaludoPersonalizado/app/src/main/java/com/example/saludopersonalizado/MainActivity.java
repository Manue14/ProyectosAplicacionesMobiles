package com.example.saludopersonalizado;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText txtNombre, txtNacimiento;
    private Button btnSaludar;
    private TextView txtView;
    private RadioGroup rdGroup, rdGroupDespedidas;
    private CheckBox chkDespedida;
    private LinearLayout layoutDespedida;

    private int selectedIdGender;
    private boolean hasDespedida;
    private int selectedIdDespedida;

    private final String SRA_TEXT = "Sra.";
    private final String SR_TEXT = "Sr.";
    private HashMap<Integer, String> despedidas = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        this.selectedIdGender = rdGroup.getCheckedRadioButtonId();
        this.hasDespedida = chkDespedida.isChecked();
        this.despedidas.put(R.id.rd_adios, getResources().getString(R.string.adios_text));
        this.despedidas.put(R.id.rd_pronto, getResources().getString(R.string.pronto_text));
        this.selectedIdDespedida = rdGroupDespedidas.getCheckedRadioButtonId();

        btnSaludar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkNombre(txtNombre.getText().toString()) &&
                checkNacimiento(txtNacimiento.getText().toString())) {
                    int nacimiento = Integer.parseInt(txtNacimiento.getText().toString());
                    String nombre = capitalize(txtNombre.getText().toString());

                    txtView.setText(buildMessage(nombre, nacimiento));
                } else {
                    Toast.makeText(getApplicationContext(), "Faltan valores o no son válidos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                selectedIdGender = i;
            }
        });

        chkDespedida.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                hasDespedida = chkDespedida.isChecked();

                if (hasDespedida) {
                    layoutDespedida.setVisibility(View.VISIBLE);
                } else {
                    layoutDespedida.setVisibility(View.GONE);
                }
            }
        });

        rdGroupDespedidas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                selectedIdDespedida = i;
            }
        });
    }

    private void initializeViews() {
        this.txtNombre = findViewById(R.id.txt_nombre);
        this.txtNacimiento = findViewById(R.id.txt_nacimiento);
        this.btnSaludar = findViewById(R.id.btn_saludar);
        this.txtView = findViewById(R.id.txt_view);
        this.rdGroup = findViewById(R.id.rd_group);
        this.chkDespedida = findViewById(R.id.chk_despedida);
        this.rdGroupDespedidas = findViewById(R.id.rd_group_despedidas);
        this.layoutDespedida = findViewById(R.id.despedidas_layout);
    }

    private boolean checkNombre(String nombre) {
        if (nombre.trim() == null || nombre.trim().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    private boolean checkNacimiento(String nacimiento) {
        if (nacimiento.trim() == null || nacimiento.trim().isEmpty()) {
            return false;
        }

        Pattern pattern = Pattern.compile("^[1-9][0-9]{0,3}$");
        Matcher matcher = pattern.matcher(nacimiento);

        return matcher.matches();
    }

    private String capitalize(String nombre) {
        String[] myArray = nombre.trim().split("\\s+");
        String capitalizedName = "";

        for (String string : myArray) {
            String s = string.toLowerCase();
            capitalizedName = capitalizedName + Character.toUpperCase(s.charAt(0)) + s.substring(1) + " ";
        }
        return capitalizedName;
    }

    private String buildMessage(String nombre, int nacimiento) {
        String msg = "Hola, ";
        int year = Calendar.getInstance().get(Calendar.YEAR);

        if (this.selectedIdGender == R.id.rd_sra) {
            msg = msg + SRA_TEXT + " ";
        } else if (this.selectedIdGender == R.id.rd_sr) {
            msg = msg + SR_TEXT + " ";
        }

        msg = msg + nombre;

        if (year - nacimiento >= 18) {
            msg = msg + "\nEres mayor de edad";
        } else {
            msg = msg + "\nNo eres mayor de edad";
        }

        if (this.hasDespedida) {
            msg = msg + "\n" + this.despedidas.get(this.selectedIdDespedida);
        }

        return msg;
    }
}