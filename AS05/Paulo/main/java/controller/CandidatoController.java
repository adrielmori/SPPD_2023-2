package controller;

import com.atividade5.Candidato;
import com.atividade5.CandidatoDAO;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "/candidato", urlPatterns = {"/candidato/*"})
public class CandidatoController extends HttpServlet {
    String jdbcURL = "jdbc:mariadb://localhost:3306/candidatos";
    String jdbcUsername = "root";
    String jdbcPassword = "prv123";
    CandidatoDAO candidatoDAO = new CandidatoDAO(jdbcURL, jdbcUsername, jdbcPassword);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            candidatoDAO.conectar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (request.getPathInfo() != null) {
            final Integer id = Integer.valueOf(request.getPathInfo().substring(1));
            Candidato candidato = candidatoDAO.buscar(id);
            Gson gson = new Gson();
            String json = gson.toJson(candidato);
            response.getWriter().print(json);
            return;
        }

        List<Candidato> candidatos = candidatoDAO.listarCandidatos();
        Gson gson = new Gson();
        String json = gson.toJson(candidatos);
        response.getWriter().print(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            candidatoDAO.conectar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String acao = request.getParameter("acao");

        String nome = request.getParameter("txtNome");
        char sexo = request.getParameter("selSexo").charAt(0);
        String dataNascimento = request.getParameter("txtDataNascimento");
        String cargoPretendido = request.getParameter("txtCargoPretendido");
        String textoCurriculo = request.getParameter("txtCurriculo");

        if (acao.equals("incluir")) {
            Candidato candidato = new Candidato(4, nome, sexo, formataData(dataNascimento), cargoPretendido, textoCurriculo);
            candidatoDAO.incluirCandidato(candidato);
        } else if (acao.equals("alterar")) {
            System.out.println("aqui");
            int id = Integer.parseInt(request.getParameter("txtCodigo"));
            Candidato candidato = new Candidato(id, nome, sexo, formataData(dataNascimento), cargoPretendido, textoCurriculo);
            candidatoDAO.alterarCandidato(candidato);
        }

        response.sendRedirect("listarCandidato.html");
    }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            candidatoDAO.conectar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        final Integer id = Integer.valueOf(request.getPathInfo().substring(1));
        candidatoDAO.exluirCandidato(id);
    }

    private Date formataData(String tempTerm) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date data_termino = new Date();
        try {
            data_termino = formatter.parse(tempTerm);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return data_termino;
    }

}
