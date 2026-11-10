package com.example.calculadorasimples.model;

public class Partida {
    private long id;
    private String data;
    private String jogador1;
    private String jogador2;
    private int pontos1;
    private int pontos2;
    private String vencedor;

    public Partida(long id, String data, String jogador1, String jogador2, int pontos1, int pontos2, String vencedor) {
        this.id = id;
        this.data = data;
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.pontos1 = pontos1;
        this.pontos2 = pontos2;
        this.vencedor = vencedor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getJogador1() {
        return jogador1;
    }

    public void setJogador1(String jogador1) {
        this.jogador1 = jogador1;
    }

    public String getJogador2() {
        return jogador2;
    }

    public void setJogador2(String jogador2) {
        this.jogador2 = jogador2;
    }

    public int getPontos1() {
        return pontos1;
    }

    public void setPontos1(int pontos1) {
        this.pontos1 = pontos1;
    }

    public int getPontos2() {
        return pontos2;
    }

    public void setPontos2(int pontos2) {
        this.pontos2 = pontos2;
    }

    public String getVencedor() {
        return vencedor;
    }

    public void setVencedor(String vencedor) {
        this.vencedor = vencedor;
    }
}
