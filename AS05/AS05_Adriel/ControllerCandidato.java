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
public class CustomCandidatoController extends HttpServlet {
    String customJdbcURL = "jdbc:mariadb://localhost:3306/candidatos";
    String customJdbcUsername = "root";
    String customJdbcPassword = "prv123";
    CustomCandidatoDAO customCandidatoDAO = new CustomCandidatoDAO(customJdbcURL, customJdbcUsername, customJdbcPassword);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            customCandidatoDAO.customConectar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (request.getPathInfo() != null) {
            final Integer customId = Integer.valueOf(request.getPathInfo().substring(1));
            Candidato customCandidato = customCandidatoDAO.customBuscar(customId);
            Gson gson = new Gson();
            String json = gson.toJson(customCandidato);
            response.getWriter().print(json);
            return;
        }

        List<Candidato> customCandidatos = customCandidatoDAO.customListarCandidatos();
        Gson gson = new Gson();
        String json = gson.toJson(customCandidatos);
        response.getWriter().print(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            customCandidatoDAO.customConectar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String customAcao = request.getParameter("acao");

        String customNome = request.getParameter("txtNome");
        char customSexo = request.getParameter("selSexo").charAt(0);
        String customDataNascimento = request.getParameter("txtDataNascimento");
        String customCargoPretendido = request.getParameter("txtCargoPretendido");
        String customTextoCurriculo = request.getParameter("txtCurriculo");

        if (customAcao.equals("incluir")) {
            Candidato customCandidato = new Candidato(4, customNome, customSexo, formataData(customDataNascimento), customCargoPretendido, customTextoCurriculo);
            customCandidatoDAO.incluirCandidato(customCandidato);
        } else if (customAcao.equals("alterar")) {
            System.out.println("aqui");
            int customId = Integer.parseInt(request.getParameter("txtCodigo"));
            Candidato customCandidato = new Candidato(customId, customNome, customSexo, formataData(customDataNascimento), customCargoPretendido, customTextoCurriculo);
            customCandidatoDAO.customAlterarCandidato(customCandidato);
        }

        response.sendRedirect("listarCandidato.html");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            customCandidatoDAO.customConectar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        final Integer customId = Integer.valueOf(request.getPathInfo().substring(1));
        customCandidatoDAO.customExcluirCandidato(customId);
    }

    private Date forma
