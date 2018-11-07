package model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import domain.enums.Perfil;

public class Usuario {
	
	private Integer codigo;
	
    private String email;
    private String nome;
    
    //@JsonIgnore
    private String senha;
    
    private Set<Integer> perfis = new HashSet<>();
    
    private String perguntasecreta;
    private String respostasecreta;
    
    public Usuario() {
    }

	public Usuario(Integer codigo, String email, String nome, String senha, String perguntasecreta, String respostasecreta) {
		super();
		this.codigo = codigo;
		this.email = email;
		this.nome = nome;
		this.senha = senha;
		this.perguntasecreta = perguntasecreta;
		this.respostasecreta = respostasecreta;
		addPerfil(Perfil.CLIENTE);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	// retorna em formato enumerado (ex: "ROLE_ADMIN") OBS: ele é armazenado como inteiro
	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
	
	// recebe no formato enumerado (ex: "ROLE_ADMIN") e guarda em uma coleção de inteiros.
	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}
	
	public String getPerguntasecreta() {
		return perguntasecreta;
	}

	public void setPerguntasecreta(String perguntasecreta) {
		this.perguntasecreta = perguntasecreta;
	}

	public String getRespostasecreta() {
		return respostasecreta;
	}

	public void setRespostasecreta(String respostasecreta) {
		this.respostasecreta = respostasecreta;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
    

    
    
}

