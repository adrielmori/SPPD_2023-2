package negocio;

public class Disciplina {
	private int codigo;
	private String nome;
	private int ch;
	private Curso curso;

	public Disciplina(int codigo, String nome, int ch, Curso curso) {
		this.codigo = codigo;
		this.nome = nome;
		this.ch = ch;
		this.curso = curso;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public int getCh() {
		return ch;
	}

	public Curso getCurso() {
		return curso;
	}
	
	
	
}
