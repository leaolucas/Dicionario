package dominio;

public class Administrador {
    private String login;
    private String senha;

    /**
     * Atributos setados para que não seja necessário mantê-los
     */
    public Administrador() {
        this.login = "ADM";
        this.senha = "ADM";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
