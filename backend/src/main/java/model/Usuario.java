package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Usuario {

    private String nome;
    private String senha;
    private String grupo;
    private boolean ativo;
    
    public Usuario(
    		@JsonProperty("nome")String nome, 
    		@JsonProperty("senha")String senha, 
    		@JsonProperty("grupo")String grupo, 
    		@JsonProperty("ativo")boolean ativo) {
    	
        this.nome = nome;
        this.senha = senha;
        this.grupo = grupo;
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

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public void resetarSenha(String newSenha){
		
	}
    
}