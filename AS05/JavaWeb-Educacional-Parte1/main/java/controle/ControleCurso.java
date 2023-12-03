package controle;

import java.io.IOException;

import biblioteca.CursoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import negocio.Curso;

@WebServlet("/controlecurso")
public class ControleCurso extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//doGet(request, response);
		String tipoAcao = request.getParameter("acao");
		if (tipoAcao.equals("incluir")) {
			incluirCurso(request, response);
		} else if (tipoAcao.equals("alterar")) {
			//alterarCurso(request, response);
		}
		
	}

	public void incluirCurso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int num = Integer.parseInt(request.getParameter("txtNumero"));
		String nome = request.getParameter("txtNome");
		String tipo = request.getParameter("selTipo");
		
		Curso cur = new Curso(num, nome, tipo);
		CursoDAO cdao = new CursoDAO();
		cdao.incluir(cur);
		
		//response.getWriter().append("Gravado com sucesso: ").append(nome);
		response.sendRedirect("menu.html");
		
	}
}
