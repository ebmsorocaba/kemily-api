package model;

import DTO.UsuarioSenha;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Usuario {

    private String nome;
    private String senha;
    private String setor;
    private String email;
    private boolean ativo;
    private UsuarioSenha usuarioSenha;

    public Usuario() {};
    
    public Usuario(
    		@JsonProperty("nome")String nome, 
    		@JsonProperty("senha")String senha, 
    		@JsonProperty("setor")String setor,
    		@JsonProperty("email")String email, 
    		@JsonProperty("ativo")boolean ativo) {
    	
        this.nome = nome;
        this.senha = senha;
        this.setor = setor;
        this.email = email;
        this.ativo = ativo;
    } //JsonProperty no constructor serve para dar apoio ao Jackson Object Mapper

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

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UsuarioSenha getUsuarioSenha() {
		return usuarioSenha;
	}

	public void setUsuarioSenha(UsuarioSenha usuarioSenha) {
		this.usuarioSenha = usuarioSenha;
	}
}