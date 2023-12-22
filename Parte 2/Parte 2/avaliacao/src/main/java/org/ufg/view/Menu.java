package org.ufg.view;

import org.ufg.dao.CursoDAO;
import org.ufg.dao.DisciplinaDAO;
import org.ufg.dao.factory.MariaDbFactory;
import org.ufg.dto.CursoDTO;
import org.ufg.model.Curso;
import org.ufg.service.CursoService;
import org.ufg.util.XmlFormatter;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private static void menuInicial() {
        System.out.println("1. Pesquisar por Texto\n" +
                        "2. Pesquisar por ano e texto\n" +
                        "3. Listar todos os cursos\n" +
                        "4. Exportar para um banco de dados (MariaDB)\n" +
                        "5. Sair\n"
        );
    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            CursoService cursoService = new CursoService(new CursoDAO(new MariaDbFactory()), new DisciplinaDAO(new MariaDbFactory()));
            List<CursoDTO> cursoDTOList = XmlFormatter.transformaXmlUniversidadeEmObjeto("dados.xml");
            menuInicial();
            int opcaoMenu = scanner.nextInt();
            switch (opcaoMenu) {
                case 1 -> {
                    System.out.println("Escreva o nome do curso ou o nome da disciplina:");
                    scanner.nextLine();
                    String curso_disciplina = scanner.nextLine();
                    List<Curso> listaCursos = cursoService.listarCursosPorNomeOuDisciplina(curso_disciplina);
                    listaCursos.forEach(System.out::println);
                }
                case 2 -> {
                    System.out.println("Informe o ano:");
                    Integer ano = scanner.nextInt();
                    List<Curso> listaCursos = cursoService.listarCursosDeUmAno(ano);
                    listaCursos.forEach(System.out::println);
                }
                case 3 -> cursoService.listarCursos(cursoDTOList).forEach(System.out::println);
                case 4 -> {
                    cursoService.salvarCursosXMLNoBanco(cursoDTOList);
                    System.out.println("Salvado com sucesso!");
                }
                case 5 -> {
                    System.out.println("Obrigado por utilizar!!!");
                    System.exit(0);
                }
            }
            System.out.println();
        }

    }
}
