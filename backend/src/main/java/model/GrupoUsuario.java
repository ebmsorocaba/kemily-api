/*Talvez utilize*/

package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GrupoUsuario {

    private int id;
    private String nome;

    public GrupoUsuario(@JsonProperty("id")int id, @JsonProperty("nome")String nome) {
      this.id = id;
      this.nome = nome;
    } //JsonProperty no constructor serve para dar apoio ao Jackson Object Mapper

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

}
