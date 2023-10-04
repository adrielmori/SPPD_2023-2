import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App_1 {

    static String regiao(int number) {
        String[] regioes = { "Norte", "Nordeste", "Sudeste", "Sul", "Centro Oeste" };
        return regioes[number - 1];
    }

    public static void main(String[] args) {
        List<String[]> states = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                String sigla = scanner.nextLine();
                String separator = "--------------------------------------------";

                try (FileReader reader = new FileReader("..\\UF.csv");
                        BufferedReader bufferedReader = new BufferedReader(reader)) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] state = line.split(",");
                        if (state[1].equals(sigla)) {
                            states.add(state);
                        }
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException("Arquivo não encontrado", e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                if (states.isEmpty()) {
                    System.out.println("Nenhum resultado encontrado para a sigla: " + sigla);
                } else {
                    System.out.println("\n" + separator);
                    for (String[] state : states) {
                        System.out.println(state[2] + " - " + regiao(Integer.parseInt(state[3])) + " - " + state[4]);
                    }
                    System.out.println(separator + "\n");
                }
                states.clear();
            }
        } finally {
            scanner.close();
        }
    }
}