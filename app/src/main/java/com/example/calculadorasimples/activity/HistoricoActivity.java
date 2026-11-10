package com.example.calculadorasimples.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.calculadorasimples.R;
import com.example.calculadorasimples.DAO.PartidaDAO;
import com.example.calculadorasimples.model.Partida;

import java.util.List;

public class HistoricoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_historico);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_historico), (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        ListView lista = findViewById(R.id.listaPartidas);
        TextView vazio = findViewById(R.id.txtVazio);
        lista.setEmptyView(vazio);

        List<Partida> partidas = new PartidaDAO(this).listarTodas();
        lista.setAdapter(new PartidaAdapter(partidas));

        findViewById(R.id.btnVoltar).setOnClickListener(v -> finish());
    }

    private static class PartidaAdapter extends BaseAdapter {

        private final List<Partida> partidas;

        PartidaAdapter(List<Partida> partidas) {
            this.partidas = partidas;
        }

        @Override public int getCount() { return partidas.size(); }
        @Override public Object getItem(int position) { return partidas.get(position); }
        @Override public long getItemId(int position) { return partidas.get(position).getId(); }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_partida, parent, false);
            }

            Partida p = partidas.get(position);

            ((TextView) convertView.findViewById(R.id.itemData)).setText(p.getData());
            ((TextView) convertView.findViewById(R.id.itemPlacar)).setText(
                    p.getJogador1() + "  " + p.getPontos1() + " x " + p.getPontos2() + "  " + p.getJogador2());
            ((TextView) convertView.findViewById(R.id.itemVencedor)).setText(
                    parent.getContext().getString(R.string.vencedor_formato, p.getVencedor()));

            return convertView;
        }
    }
}