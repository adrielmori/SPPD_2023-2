package org.ufg.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
@XStreamAlias("curso")
public class CursoDTO {
    private Integer iden;
    private Integer ano;
    private String nome;
    private String disciplina;
    private Integer ch;
}
