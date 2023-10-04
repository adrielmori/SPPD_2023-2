import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static String regiao(int number) {
        String[] regioes = { "Norte", "Nordeste", "Sudeste", "Sul", "Centro Oeste" };
        return regioes[number - 1];
    }

    public static void main(String[] args) {
        List<String[]> estados = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite a sigla do estado: ");
        String sigla = scanner.nextLine();

        try (FileReader reader = new FileReader(
                "C:\\Users\\usuario\\OneDrive\\Documentos\\UFG_Remoto\\8_periodo\\SPD\\UF.csv");
                BufferedReader bufferedReader = new BufferedReader(reader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] estado = line.split(",");
                if (estado[1].equals(sigla)) {
                    estados.add(estado);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Arquivo não encontrado", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Estados encontrados: ");
        for (String[] estado : estados) {
            System.out.println(estado[2] + " - " + regiao(Integer.parseInt(estado[3])) + " - " + estado[4]);
        }
    }

}