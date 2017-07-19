package br.ufrpe.my_pigeon_study.negocio.beans;

import java.util.ArrayList;

public class Usuario {
	private String nome;
	private Data dataNasc;
	private String usuario;
	private String senha;
	private int sexo;//1-feminino, 2-masculino e 3-outro
	private String email;
	private ArrayList<Atividade> atividades;
	private ArrayList<Task> tasks;
	private ArrayList<Amigo> amigos;
	private ArrayList<Amigo> amigosPendentes;

	public Usuario() {
		super();
	}
	public Usuario(String usuario, String senha){
		this.senha=senha;
		this.usuario=usuario;
	}
	public Usuario(String nome, Data dataNasc, String usuario, String senha, int sexo, String email) {
		super();
		this.nome = nome;
		this.dataNasc = dataNasc;
		this.usuario = usuario;
		this.senha = senha;
		this.sexo = sexo;
		this.email = email;
		
		this.atividades = new ArrayList<Atividade>();
		this.tasks = new ArrayList<Task>();
		this.amigos= new ArrayList<Amigo>();
		this.amigosPendentes= new ArrayList<Amigo>();

	}
	
	//METODO PARA ADICIONAR AMIGO
	public boolean addAmigo(Amigo amg){
		if(!this.amigos.contains(amg)){
			if(this.amigosPendentes.contains(amg) && amg.getConvite() == 1){ 
				this.amigos.add(amg);
				this.amigosPendentes.remove(amg);	
			}
			else{
				this.amigosPendentes.add(amg);
			}
			return true;
		}
		return false;
	}
	public boolean delAmigo(Amigo amg){
		if(!this.amigos.contains(amg)){
			if(this.amigosPendentes.contains(amg)){
				this.amigosPendentes.remove(amg);
				return true;	
			}
		}
		else{
			this.amigos.remove(amg);
			return(true);
		}
		return false;
	}
	
	//GETTERS AND SETTERS
	public ArrayList<Atividade> getAtividades() {
		return atividades;
	}
	public void setAtividades(ArrayList<Atividade> atividades) {
		this.atividades = atividades;
	}
	public ArrayList<Task> getTasks() {
		return tasks;
	}
	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;

	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Data getDataNasc() {
		return dataNasc;
	}


	public void setDataNasc(Data dataNasc) {
		this.dataNasc = dataNasc;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public int getSexo() {
		return sexo;
	}


	public void setSexo(int sexo) {
		this.sexo = sexo;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public ArrayList<Amigo> getAmigos() {
		return amigos;
	}
	public void setAmigos(ArrayList<Amigo> amigos) {
		this.amigos = amigos;
	}
	public ArrayList<Amigo> getAmigosPendentes() {
		return amigosPendentes;
	}
	public void setAmigosPendentes(ArrayList<Amigo> amigosPendentes) {
		this.amigosPendentes = amigosPendentes;
	}
	
	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", dataNasc=" + dataNasc + ", usuario=" + usuario + ", senha=" + senha
				+ ", sexo=" + sexo + ", email=" + email + ", atividades=" + atividades.toString() + ", tasks="
				+ tasks.toString() + "]";
	}


	
}
