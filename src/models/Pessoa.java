package models;

/**
 * Classe que representa os dados de uma pessoa
 *
 * @author Gustavo Henrique Miguel Ferreira
 */

public class Pessoa {

    private String nome;
    private String cpf;
    private Contatos contatos;
    private Endereco endereco;
    private String observacoes;


    public Pessoa(String nome, String cpf, String telefone, String email,  String cep, String logradouro, String numero, String complemento, String bairro, String localidade, String uf, String observacoes) {

        this.nome = nome;
        this.cpf = cpf;
        this.contatos = new Contatos(telefone, email);
        this.endereco = new Endereco(logradouro, numero, complemento, bairro, localidade,  cep, uf );
        this.observacoes = observacoes;
    }

    public Pessoa(String nome, String cpf, Contatos contatos,  Endereco endereco, String observacoes) {
        this.nome = nome;
        this.cpf = cpf;
        this.contatos = contatos;
        this.endereco = endereco;
        this.observacoes = observacoes;
    }


    /**
     * gettes and setters ...
     *
     */
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Contatos getContatos() {
        return contatos;
    }

    public void setContatos(Contatos contatos) {
        this.contatos = contatos;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public String toString() {
        return "People{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", contatos=" + contatos +
                ", endereco=" + endereco +
                ", observacoes='" + observacoes + '\'' +
                '}';
    }
}
