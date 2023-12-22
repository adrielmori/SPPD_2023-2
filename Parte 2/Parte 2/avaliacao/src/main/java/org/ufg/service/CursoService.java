package org.ufg.service;

import org.ufg.dao.CursoDAO;
import org.ufg.dao.DisciplinaDAO;
import org.ufg.dto.CursoDTO;
import org.ufg.model.Curso;
import org.ufg.model.Disciplina;
import org.ufg.util.XmlFormatter;

import java.util.ArrayList;
import java.util.List;

public class CursoService {

    private final CursoDAO cursoDAO;
    private final DisciplinaDAO disciplinaDAO;

    public CursoService(CursoDAO cursoDAO, DisciplinaDAO disciplinaDAO) {
        this.cursoDAO = cursoDAO;
        this.disciplinaDAO = disciplinaDAO;
    }

    public void salvarCursosXMLNoBanco(List<CursoDTO> cursosDTO) {
        List<Curso> cursos = listarCursos(cursosDTO);
        cursos.forEach(curso -> {
            if (!cursoDAO.existeCurso(curso.getIden())) {
                cursoDAO.salvarCurso(curso);
                disciplinaDAO.salvarDisciplina(curso.getDisciplina(), curso.getIden());
            }
        });
    }

    public List<Curso> listarCursos(List<CursoDTO> cursosDTO) {
        if (cursosDTO.isEmpty()) {
            System.out.println("Lista cursoDTO vazia!");
            return new ArrayList<>();
        }

        List<Curso> cursos = new ArrayList<>();
        for (CursoDTO cursoDTO : cursosDTO) {
            Disciplina disciplina = new Disciplina(cursoDTO.getDisciplina(), cursoDTO.getCh());
            Curso curso = new Curso(cursoDTO.getIden(), cursoDTO.getAno(), cursoDTO.getNome(), disciplina);
            cursos.add(curso);
        }

        return cursos;
    }

    public List<Curso> listarCursosDeUmAno(Integer ano) {
        List<Curso> cursos = listarCursos(XmlFormatter.transformaXmlUniversidadeEmObjeto("dados.xml"));
        List<Curso> cursosFiltrados = new ArrayList<>();

        for (Curso curso : cursos) {
            if (curso.getAno() == ano) {
                cursosFiltrados.add(curso);
            }
        }

        return cursosFiltrados;
    }

    public List<Curso> listarCursosPorNomeOuDisciplina(String texto) {
        List<Curso> cursos = listarCursos(XmlFormatter.transformaXmlUniversidadeEmObjeto("dados.xml"));
        List<Curso> cursosFiltrados = new ArrayList<>();

        for (Curso curso : cursos) {
            if (curso.getNome().contains(texto) || curso.getDisciplina().getNome().contains(texto)) {
                cursosFiltrados.add(curso);
            }
        }

        return cursosFiltrados;
    }
}
