package model;

<<<<<<< Updated upstream
import com.fasterxml.jackson.annotation.JsonProperty;
=======
>>>>>>> Stashed changes

public class Usuario {
	
	private Integer codigo;
	
    private String email;
    private String nome;
    
    //@JsonIgnore
    private String senha;
<<<<<<< Updated upstream
    private String setor;
    private String email;
    private boolean ativo;
    
    public Usuario() {};
=======
>>>>>>> Stashed changes
    
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
    
<<<<<<< Updated upstream
	public void resetarSenha(String newSenha){
		
	}
	
}
=======
    
    
}
>>>>>>> Stashed changes
