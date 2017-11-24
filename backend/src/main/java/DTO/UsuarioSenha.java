package DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioSenha {
    private String senhaAntiga;
    private String senhaNova;

    public UsuarioSenha() {}

    public UsuarioSenha(@JsonProperty("senhaAntiga") String senhaAntiga, @JsonProperty("senhaNova") String senhaNova) {
        this.senhaAntiga = senhaAntiga;
        this.senhaNova = senhaNova;
    }

    public String getSenhaAntiga() {
        return senhaAntiga;
    }

    public void setSenhaAntiga(String senhaAntiga) {
        this.senhaAntiga = senhaAntiga;
    }

    public String getSenhaNova() {
        return senhaNova;
    }

    public void setSenhaNova(String senhaNova) {
        this.senhaNova = senhaNova;
    }
}
