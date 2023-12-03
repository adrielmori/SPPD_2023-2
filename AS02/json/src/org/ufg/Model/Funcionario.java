package org.ufg.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Funcionario {
    private String cpf;
    private String nome;
    private Integer idade;
    private String cargo;
    List<String> habilidades;
}
