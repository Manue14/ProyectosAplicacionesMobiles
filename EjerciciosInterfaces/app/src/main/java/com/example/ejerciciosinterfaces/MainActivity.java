package com.example.ejerciciosinterfaces;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textViewRespuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta);
        textViewRespuesta = findViewById(R.id.view_respuesta);
    }//end onCreate
     /*
    public void onClickBtn(View view) {
        if (view.getId() == R.id.btn_si) {
            textViewRespuesta.setText("Has pulsado el botón Sí");
        } else if (view.getId() == R.id.btn_no) {
            textViewRespuesta.setText("Has pulsado el botón No");
        } else if (view.getId() == R.id.btn_aveces) {
            textViewRespuesta.setText("Has pulsado el botón A veces");
        }
    }//end onClickBtn
    */

    public void onClickBtn(View view) {
        switch (view.getId()) {
            case R.id.btn_si:
                textViewRespuesta.setText("Has pulsado el botón Sí");
            break;

            case R.id.btn_no:
                textViewRespuesta.setText("Has pulsado el botón No");
            break;

            case R.id.btn_aveces:
                textViewRespuesta.setText("Has pulsado el botón A veces");
            break;

        }
    }//end onClickBtn

    /*
    public void onClickBtnSi(View view) {
        textViewRespuesta.setText("Has pulsado el botón Sí");
    }

    public void onClickBtnNo(View view) {
        textViewRespuesta.setText("Has pulsado el botón No");
    }
    */
}//end MainActivity