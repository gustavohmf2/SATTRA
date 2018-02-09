package models;

/**
 * Classe que representa os dados do contato de uma pessoa
 *
 * @author Gustavo Henrique Miguel Ferreira
 */
public class Contatos {

    private String telfone;
    private String email;

    public Contatos(String telfone, String email) {
        this.telfone = telfone;
        this.email = email;
    }


    /**
     * gettes and setters ...
     *
     */
    public String getTelfone() {
        return telfone;
    }

    public void setTelfone(String telfone) {
        this.telfone = telfone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
