package com.jdriven.ng2boot;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Aluno {

    private int id;
    private String name;
    private String gender;

    public Aluno(@JsonProperty("id")int id, @JsonProperty("name")String name, @JsonProperty("gender")String gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    } //JsonProperty no constructor serve para dar apoio ao Jackson Object Mapper

	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	/*
	public Aluno(@JsonProperty("id")int id, @JsonProperty("name")String name, @JsonProperty("gender")String gender){
		
    }
 	*/
    
}
