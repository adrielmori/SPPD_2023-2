package negocio;

public class Curso {
	private int numero;
	private String nome;
	private String tipo;

	public Curso(int numero, String nome, String tipo) {
		this.numero = numero;
		this.nome = nome;
		this.tipo = tipo;
	}
	public int getNumero() {
		return numero;
	}
	public String getNome() {
		return nome;
	}
	public String getTipo() {
		return tipo;
	}

	public String getTipoDescricao() {
		if (this.tipo.equals("B")) {
			return "Bacharelado";
		}else if (this.tipo.equals("L")) {
			return "Licenciatura";
		}else {
			return "Tecnologico";
		}
				
}
}
