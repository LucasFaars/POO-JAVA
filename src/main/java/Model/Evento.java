package Model;

public class Evento {
	private int id;
	private String nome;
	private String descricao;
	private String local;
	private String data;
	private String horario;

	public Evento() {

	}

	public Evento(String nome, String descricao, String local, String data, String horario) {
		this.nome = nome;
		this.descricao = descricao;
		this.local = local;
		this.data = data;
		this.horario = horario;
	}

	public Evento(int id, String nome, String descricao, String local, String data, String horario) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.local = local;
		this.data = data;
		this.horario = horario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHorario() {
		return horario;
	}

	public void setHora(String horario) {
		this.horario = horario;
	}

}