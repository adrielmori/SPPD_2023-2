package org.ufg.Service;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.ufg.Model.Funcionario;

import java.io.FileReader;
import java.io.FileWriter;

public class FuncionarioSevice {

    private final Funcionario funcionario;

    public FuncionarioSevice(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    private String converteEmXml() {
        XStream xStream = new XStream();
        xStream.alias("Funcionario", Funcionario.class);
        return xStream.toXML(funcionario);
    }

    public void criaUmArquivoXml() {
        try (FileWriter fileWriter = new FileWriter(funcionario.getCpf() + ".xml")) {
            fileWriter.write(converteEmXml());
            fileWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Funcionario lerArquivoXml(String cpf) {
        try (FileReader reader = new FileReader(cpf + ".xml")) {
            XStream xStream = new XStream();
            xStream.alias("Funcionario", Funcionario.class);
            xStream.addPermission(AnyTypePermission.ANY);
            return (Funcionario) xStream.fromXML(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
