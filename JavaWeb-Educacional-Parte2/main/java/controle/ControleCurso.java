package controle;

import java.io.IOException;
import java.util.List;

import biblioteca.CursoDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import negocio.Curso;

@WebServlet("/controlecurso")
public class ControleCurso extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipoAcao = request.getParameter("acao");
		if (tipoAcao.equals("listar")) {
			listarCursos(request, response);
		}else if (tipoAcao.equals("excluir")) {
			excluirCurso(request, response);
		}else if (tipoAcao.equals("editar")) {
			buscarCurso(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//doGet(request, response);
		String tipoAcao = request.getParameter("acao");
		if (tipoAcao.equals("incluir")) {
			incluirCurso(request, response);
		} else if (tipoAcao.equals("alterar")) {
			alterarCurso(request, response);
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
		
		request.setAttribute("novocurso", nome);
		listarCursos(request, response);
	}
	
	protected void excluirCurso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int numero = Integer.parseInt(request.getParameter("numero"));
		
		CursoDAO cdao = new CursoDAO();
		Curso cur = cdao.getCurso(numero);
		cdao.excluir(cur);
		request.setAttribute("cursoexcluido", cur.getNome());
		listarCursos(request, response);
	}
	
	public void listarCursos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CursoDAO cdao = new CursoDAO();
		
		List<Curso> cursos = cdao.getCursos();
		request.setAttribute("cursos", cursos);
		
		RequestDispatcher rd = 
				request.getRequestDispatcher("listarCursos.jsp");
		rd.forward(request, response);
		
	}
	
	public void buscarCurso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CursoDAO cdao = new CursoDAO();
		
		int num = Integer.parseInt(request.getParameter("numero"));
		
		Curso cur = cdao.getCurso(num);
		request.setAttribute("curso", cur);
			
		RequestDispatcher rd = request.getRequestDispatcher("editar_curso.jsp");
		rd.forward(request, response);

	}

	
	public void alterarCurso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int num = Integer.parseInt(request.getParameter("txtNumero"));
		String nome = request.getParameter("txtNome");
		String tipo = request.getParameter("selTipo");
		
		Curso cur = new Curso(num, nome, tipo);
		CursoDAO cdao = new CursoDAO();
		
		cdao.alterar(cur);
		
		listarCursos(request, response);
	}
	
}

