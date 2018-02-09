package models;

/**
 * Classe que representa os dados do endereço de uma pessoa
 *
 * @author Gustavo Henrique Miguel Ferreira
 */
public class Endereco {

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String localidade;
    private String cep;
    private String uf;

    public Endereco(String logradouro, String numero, String complemento, String bairro, String localidade, String cep, String uf) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.localidade = localidade;
        this.cep = cep;
        this.uf = uf;
    }


    /**
     * gettes and setters ...
     */
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;

    }

    @Override
    public String toString() {
        return "Endereco{" +
                "logradouro='" + logradouro + '\'' +
                ", numero=" + numero +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", localidade='" + localidade + '\'' +
                ", cep='" + cep + '\'' +
                ", uf='" + uf + '\'' +
                '}';
    }
}