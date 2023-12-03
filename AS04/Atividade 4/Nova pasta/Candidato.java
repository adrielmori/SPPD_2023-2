import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Candidato {
    Integer codigo;
    String nome;
    char sexo;
    Date dataNascimento;
    String cargoPretendido;
    String textoCurriculo;

    public Candidato(String nome, char sexo, Date dataNascimento, String cargoPretendido, String textoCurriculo) {
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.cargoPretendido = cargoPretendido;
        this.textoCurriculo = textoCurriculo;
    }
}
