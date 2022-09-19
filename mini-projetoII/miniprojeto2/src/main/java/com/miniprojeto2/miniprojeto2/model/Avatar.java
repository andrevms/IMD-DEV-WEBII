package com.miniprojeto2.miniprojeto2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "avatar")
public class Avatar {

    @Id
    @Column(name="nomeFantasia", length = 50)
    private String nomeFantasia;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudante_id")
    private Estudante estudante;


    public Avatar() {
    }

    public Avatar(String nomeFantasia, Estudante estudante) {
        this.nomeFantasia = nomeFantasia;
        this.estudante = estudante;
    }


    @Override
    public String toString() {
        return "Avatar [nomeFantasia=" + nomeFantasia + estudante + "]";
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

}
