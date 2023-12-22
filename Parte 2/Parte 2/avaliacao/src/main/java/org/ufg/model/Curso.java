package org.ufg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Curso {
    private int iden;
    private int ano;
    private String nome;
    private Disciplina disciplina;

    public Curso(int iden, int ano, String nome) {
        this.iden = iden;
        this.ano = ano;
        this.nome = nome;
    }
}
