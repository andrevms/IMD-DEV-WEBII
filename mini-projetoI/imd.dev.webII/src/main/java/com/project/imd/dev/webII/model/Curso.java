package com.project.imd.dev.webII.model;

public class Curso {

    private int id;
    private String nome;
    private String linguagem;

    public Curso(String nome, String linguagem) {
        this.nome = nome;
        this.linguagem = linguagem;
    }

    public Curso() {

    }

    @Override
    public String toString() {
        return "Curso " + id + " [" + nome + "] : linguagem ["+ linguagem +"]";
    }

    // Get's e Set's
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLinguagem() {
        return linguagem;
    }

    public void setLinguagem(String linguagem) {
        this.linguagem = linguagem;
    }
}
