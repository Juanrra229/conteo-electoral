package com.example.conteoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEdad;
    private Button btnVotar1;
    private Button btnVotar2;
    private Button btnVotar3;
    private TextView textViewResultado;
    private EditText editTextNumElectors;

    private final int EDAD_MINIMA = 18;
    private int votosCandidatoA = 0;
    private int votosCandidatoB = 0;
    private int votosCandidatoC = 0;
    private int numeroVotos = 0;
    private int edad = 0;
    TextView textViewVotosTotales1;
    TextView textViewVotosTotales2;
    TextView textViewVotosTotales3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEdad = findViewById(R.id.editTextEdad);
        editTextNumElectors = findViewById(R.id.editTextNumElectors);
        btnVotar1 = findViewById(R.id.btnCantidatoA);
        btnVotar2 = findViewById(R.id.btnCantidatoB);
        btnVotar3 = findViewById(R.id.btnCantidatoC);
        textViewResultado = findViewById(R.id.textViewResultado1);

        textViewVotosTotales1 = findViewById(R.id.textViewVotosTotales1);
        textViewVotosTotales2 = findViewById(R.id.textViewVotosTotales2);
        textViewVotosTotales3 = findViewById(R.id.textViewVotosTotales3);

        btnVotar1.setOnClickListener(v -> votar("CANDIDATO_A"));

        btnVotar2.setOnClickListener(v -> votar("CANDIDATO_B"));

        btnVotar3.setOnClickListener(v -> votar("CANDIDATO_C"));

        editTextNumElectors.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                capturarDatos();
            }
        });

    }

    private void capturarDatos(){

        if(TextUtils.isEmpty(editTextNumElectors.getText().toString()) || TextUtils.isEmpty(editTextEdad.getText().toString())){
            return;
        }

        numeroVotos = Integer.parseInt(editTextNumElectors.getText().toString());
        edad = Integer.parseInt(editTextEdad.getText().toString());

        textViewVotosTotales1.setText("0");
        textViewVotosTotales2.setText("0");
        textViewVotosTotales3.setText("0");

        textViewResultado.setText("");
    }

    private void votar(String candidatoVotado) {

        if (edad >= EDAD_MINIMA && numeroVotos > 0) {
            registrarVoto(candidatoVotado);
            mostrarResultadosParciales();
            numeroVotos--;

            if(numeroVotos == 0){
                mostrarGanador();
                resetEntradas();
            }

        } else if(edad < 18){
            Toast.makeText(this, "No puedes votar", Toast.LENGTH_SHORT).show();
        }

    }

    private void resetEntradas() {
        editTextEdad.setText("");
        editTextNumElectors.setText("");
        votosCandidatoA = 0;
        votosCandidatoB = 0;
        votosCandidatoC = 0;
        numeroVotos = 0;
        edad = 0;
    }

    private void mostrarGanador() {
        textViewResultado = findViewById(R.id.textViewResultado1);

        if(votosCandidatoA > votosCandidatoB && votosCandidatoA > votosCandidatoC){
            textViewResultado.setText("El ganador es el candidato A");
        }else if(votosCandidatoB > votosCandidatoA && votosCandidatoB > votosCandidatoC){
            textViewResultado.setText("El ganador es el candidato B");
        }else if(votosCandidatoC > votosCandidatoA && votosCandidatoC > votosCandidatoA){
            textViewResultado.setText("El ganador es el candidato C");
        }else {
            textViewResultado.setText("Hay un empate tecnico");
        }
    }


    private void registrarVoto(String candidatoVotado) {

        switch(candidatoVotado){
            case "CANDIDATO_A":
                votosCandidatoA++;
                break;
            case "CANDIDATO_B":
                votosCandidatoB++;
                break;
            case "CANDIDATO_C":
                votosCandidatoC++;
                break;
        }

        Toast.makeText(this, "Voto registrado con éxito.", Toast.LENGTH_SHORT).show();
    }

    private void mostrarResultadosParciales() {

        textViewVotosTotales1.setText(String.valueOf(votosCandidatoA));
        textViewVotosTotales2.setText(String.valueOf(votosCandidatoB));
        textViewVotosTotales3.setText(String.valueOf(votosCandidatoC));

    }
}