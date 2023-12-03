package com.atividade5;

import java.util.Date;

public class Candidato {
    private int codigo;
    private String nome;
    private char sexo;
    private Date data_nasc;
    private String cargo_pretendido;
    private String texto_curriculo;

    public Candidato(){

    }
    public Candidato(int codigo, String nome, char sexo, Date dataNascimento, String cargoPretendido, String textoCurriculo) {
        this.codigo = codigo;
        this.nome = nome;
        this.sexo = sexo;
        this.data_nasc = dataNascimento;
        this.cargo_pretendido = cargoPretendido;
        this.texto_curriculo = textoCurriculo;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public char getSexo() {
        return sexo;
    }

    public Date getData_nasc() {
        return data_nasc;
    }

    public String getCargo_pretendido() {
        return cargo_pretendido;
    }

    public String getTexto_curriculo() {
        return texto_curriculo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public void setData_nasc(java.sql.Date dataNasc) {
        this.data_nasc = dataNasc;
    }

    public void setCargo_pretendido(String cargoPretendido) {
        this.cargo_pretendido = cargoPretendido;
    }

    public void setTexto_curriculo(String textoCurriculo) {
        this.texto_curriculo = textoCurriculo;
    }

}

