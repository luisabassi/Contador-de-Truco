package com.example.calculadorasimples.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.calculadorasimples.DAO.PartidaDAO;
import com.example.calculadorasimples.R;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int contador1 = 0;
    private int contador2 = 0;

    private TextView txtContador;
    private TextView txtContador1;

    private EditText qtd_maxima;
    private EditText nome1;
    private EditText nome2;

    private Button somar;
    private Button somar2;
    private Button subtracao;
    private Button subtracao2;

    private Button encerrar;
    private Button btnHistorico;


    private PartidaDAO partidaDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activiy_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            v.setPadding(
                    systemBars.left,
                    systemBars.top,
                    systemBars.right,
                    systemBars.bottom
            );

            return insets;
        });

        nome1 = findViewById(R.id.nome1);
        nome2 = findViewById(R.id.nome2);
        qtd_maxima = findViewById(R.id.qtd_maxima);

        txtContador = findViewById(R.id.txtContador);
        txtContador1 = findViewById(R.id.txtContador1);

        somar = findViewById(R.id.somar);
        somar2 = findViewById(R.id.somar2);

        subtracao = findViewById(R.id.subtracao);
        subtracao2 = findViewById(R.id.subtracao2);
        encerrar = findViewById(R.id.encerrar);
        btnHistorico = findViewById(R.id.btnHistorico);

        partidaDAO = new PartidaDAO(this);

        encerrar.setOnClickListener( v -> finalizarPartida());
        btnHistorico.setOnClickListener(v -> startActivity(
                new Intent(this, HistoricoActivity.class)));

        somar.setOnClickListener(v -> {

            int maximo = obterPontuacaoMaxima();
            if (maximo == -1) {
                return;
            }

            if (contador1 < maximo) {
                contador1++;
                atualizarContador1();
                verificarVitoria(1);
            }
        });

        subtracao.setOnClickListener(v -> {

            if (contador1 > 0) {
                contador1--;
                atualizarContador1();
            } else {
                Toast.makeText(
                        this,
                        "A pontuação não pode ser negativa!",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        somar2.setOnClickListener(v -> {

            int maximo = obterPontuacaoMaxima();
            if (maximo == -1) {
                return;
            }

            if (contador2 < maximo) {
                contador2++;
                atualizarContador2();
                verificarVitoria(2);
            }
        });

        subtracao2.setOnClickListener(v -> {
            if (contador2 > 0) {
                contador2--;
                atualizarContador2();
            } else {
                Toast.makeText(
                        this,
                        "A pontuação não pode ser negativa!",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    private int obterPontuacaoMaxima() {

        String texto = qtd_maxima.getText().toString();

        if (texto.isEmpty()) {
            Toast.makeText(
                    this,
                    "Digite a pontuação máxima!",
                    Toast.LENGTH_SHORT
            ).show();

            return -1;
        }

        int maximo = Integer.parseInt(texto);

        if (maximo <= 0) {
            Toast.makeText(
                    this,
                    "A pontuação máxima deve ser maior que 0!",
                    Toast.LENGTH_SHORT
            ).show();
            return -1;
        }

        return maximo;
    }

    private void atualizarContador1() {
        txtContador.setText(String.valueOf(contador1));
    }

    private void atualizarContador2() {
        txtContador1.setText(String.valueOf(contador2));
    }

    private void verificarVitoria(int jogador) {

        int maximo = obterPontuacaoMaxima();

        if (maximo == -1) {
            return;
        }

        if (jogador == 1 && contador1 == maximo) {

            String nome = nome1.getText().toString();

            if (nome.isEmpty()) {
                nome = "Jogador 1";
            }

            Toast.makeText(
                    this,
                    "Parabéns, " + nome + "! Você venceu!",
                    Toast.LENGTH_LONG
            ).show();

            desativarBotoes();
        }

        if (jogador == 2 && contador2 == maximo) {

            String nome = nome2.getText().toString();

            if (nome.isEmpty()) {
                nome = "Jogador 2";
            }

            Toast.makeText(
                    this,
                    "Parabéns, " + nome + "! Você venceu!",
                    Toast.LENGTH_LONG
            ).show();

            desativarBotoes();
        }
    }
    private void desativarBotoes() {

        somar.setEnabled(false);
        somar2.setEnabled(false);
        subtracao.setEnabled(false);
        subtracao2.setEnabled(false);
    }

    private void ativarBotoes(){
        somar.setEnabled(true);
        somar2.setEnabled(true);
        subtracao.setEnabled(true);
        subtracao2.setEnabled(true);
    }

    private void finalizarPartida(){
        if (contador1 == 0 && contador2 == 0){
            Toast.makeText(this, "Ninguém pontuou ainda", Toast.LENGTH_SHORT).show();

            return;
        }

        String jogador1 = nome1.getText().toString().trim();
        String jogador2 = nome2.getText().toString().trim();
        if(jogador1.isEmpty()) jogador1 = "Jogador 1";
        if(jogador2.isEmpty()) jogador2 = "Jogador 2";


        String vencedor;
        if(contador1> contador2){
            vencedor = jogador1;
        }else if(contador2> contador1){
            vencedor = jogador2;
        }else{
            vencedor = "Empate";
        }

        String data = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                .format(new Date());

        long id = partidaDAO.inserir(data, jogador1, jogador2, contador1, contador2, vencedor);

        if (id == -1) {
            Snackbar.make(findViewById(R.id.activiy_main),
                    "Erro ao salvar a partida.", Snackbar.LENGTH_LONG).show();
            return;
        }

        //Zera o placar e libera os botões
        contador1 = 0;
        contador2 = 0;
        atualizarContador1();
        atualizarContador2();
        ativarBotoes();


        Snackbar.make(findViewById(R.id.activiy_main),
                "Partida salva! Vencedor: " + vencedor, Snackbar.LENGTH_LONG).show();
    }
}