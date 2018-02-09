package dataAccess;

import factory.ConnectionFactory;
import models.Pessoa;
import java.util.Date;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável que define a interface de comunicação com a instância do banco de dados
 * aqui definimos os métodos principais para a manipulação desse registro no banco de dado
 * (operações CRUD)
 *
 * @author Gustavo Henrique Miguel Ferreira
 */
public class PeopleDAO implements IPeopleDao{

    private Connection connection;
    private String query;

    public PeopleDAO(){

        connection = ConnectionFactory
                .getInstance()
                .getConnection("jdbc:mysql://localhost:3306/sattra","root","guga8802");


    }

    /**
     * Método que realiza uma inserção de dados no registro de pessoa no banco de dados
     *
     * @param people recebe o objeto que deverá ser persistido no banco de dados
     * @return retorna true caso tudo tenha ocorrido bem ou levanta uma exceção caso contrário
     */
    @Override
    public void insert(Pessoa people) throws SQLException {

        boolean resultExecute = false;
        query = "INSERT INTO People (nome, cpf, telefone, email, cep, logradouro, numero, complemento, bairro, localidade, uf, observacoes)"
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";

        PreparedStatement preparedStatement = null;

        try{

            connection.setAutoCommit(false);
            //objeto que representa a instrução SQL
            preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, people.getNome());
                preparedStatement.setString(2, people.getCpf());
                preparedStatement.setString(3, people.getContatos().getTelfone());
                preparedStatement.setString(4, people.getContatos().getEmail());
                preparedStatement.setString(5, people.getEndereco().getCep());
                preparedStatement.setString(6, people.getEndereco().getLogradouro());
                preparedStatement.setString(7, people.getEndereco().getNumero());
                preparedStatement.setString(8, people.getEndereco().getComplemento());
                preparedStatement.setString(9, people.getEndereco().getBairro());
                preparedStatement.setString(10, people.getEndereco().getLocalidade());
                preparedStatement.setString(11, people.getEndereco().getUf());
                preparedStatement.setString(12, people.getObservacoes());


            preparedStatement.executeUpdate();
            connection.commit();



        }catch (SQLException e){

            e.printStackTrace();
            throw new RuntimeException("Não foi possível inserir os dados da tabela pessoa no banco de dados!");
        }finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }


        }

    }

    /**
     * Método que busca os dados de uma pessoa registrada previamente no banco de dados
     * @param cpf informação referente a pessoa no qual se deseja recuperar as informações
     * @return o objeto preenchido com os dados vindo do banco de dados ou levanta uma exceção
     * caso não consiga encontrar um registro pessoa com o cpf informado.
     */
    @Override
    public Pessoa findByCPF(String cpf) throws SQLException {

        query = "SELECT *FROM People WHERE cpf = '" + cpf + "';";

        Statement preparedStatement = null;
        try {

            preparedStatement = connection.createStatement();

            ResultSet resultSet = preparedStatement.executeQuery(query);

            Pessoa peopleResult =  null;

                while (resultSet.next()) {

                   peopleResult = new Pessoa(
                            resultSet.getString("nome"),
                            resultSet.getString("cpf"),
                            resultSet.getString("telefone"),
                            resultSet.getString("email"),
                            resultSet.getString("cep"),
                            resultSet.getString("logradouro"),
                            resultSet.getString("numero"),
                            resultSet.getString("complemento"),
                            resultSet.getString("bairro"),
                            resultSet.getString("localidade"),
                            resultSet.getString("uf"),
                            resultSet.getString("observacoes")
                    );

                }


            return peopleResult;

        }catch (Exception e){

            e.printStackTrace();

            throw new RuntimeException("Não foi possível buscar os dados da pessoa informada");

        }finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

        }
    }

    /**
     * Lista todas as informações dos registro Pessoa cadastrados no banco de dados
     * @return uma lista de objetos People com os dados do banco de dados.
     */
    @Override
    public List<Pessoa> findAll() throws SQLException {

        query = "SELECT *FROM People";
        ArrayList<Pessoa> listPeople = new ArrayList<Pessoa>();

        Statement preparedStatement = null;
        try {

            preparedStatement = connection.createStatement();

            ResultSet resultSet = preparedStatement.executeQuery(query);


            while (resultSet.next()) {

                Pessoa people = new Pessoa(
                        resultSet.getString("nome"),
                        resultSet.getString("cpf"),
                        resultSet.getString("telefone"),
                        resultSet.getString("email"),
                        resultSet.getString("cep"),
                        resultSet.getString("logradouro"),
                        resultSet.getString("numero"),
                        resultSet.getString("complemento"),
                        resultSet.getString("bairro"),
                        resultSet.getString("localidade"),
                        resultSet.getString("uf"),
                        resultSet.getString("observacoes")
                );

                listPeople.add(people);

            }


            return listPeople;

        }catch (Exception e){

            e.printStackTrace();
            throw new RuntimeException("Não foi possível listar todos os usuários!");

        }finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

        }


    }

    /**
     * Responsável por permitir a edição dos dados de um registro que esteja cadastrado no
     * banco de dados.
     * @param people indica qual o registro cadastrado no banco de dados deverá ser modificado
     *
     * @return retorana true no caso de sucesso de edição, ou levanta uma exceção caso contrário
     */
    @Override
    public boolean edit(Pessoa people) throws SQLException {

        boolean resultExecute = false;

        query = "UPDATE People SET "
                    + "nome=?, cpf=?, telefone=?, email=?, cep=?,"
                    +"logradouro=?, numero=?, complemento=?, bairro=?, localidade =?, uf=?, observacoes=?"
                         + " WHERE cpf=?;";
        PreparedStatement preparedStatement = null;
        connection.setAutoCommit(false);
        try {

            preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, people.getNome());
                preparedStatement.setString(2, people.getCpf());
                preparedStatement.setString(3, people.getContatos().getTelfone());
                preparedStatement.setString(4, people.getContatos().getEmail());
                preparedStatement.setString(5, people.getEndereco().getCep());
                preparedStatement.setString(6, people.getEndereco().getLogradouro());
                preparedStatement.setString(7, people.getEndereco().getNumero());
                preparedStatement.setString(8, people.getEndereco().getComplemento());
                preparedStatement.setString(9, people.getEndereco().getBairro());
                preparedStatement.setString(10, people.getEndereco().getLocalidade());
                preparedStatement.setString(11, people.getEndereco().getUf());
                preparedStatement.setString(12, people.getObservacoes());
                preparedStatement.setString(13, people.getCpf());

            resultExecute = preparedStatement.execute();
            connection.commit();

            return resultExecute;

        }catch (Exception e){

            e.printStackTrace();
            throw new RuntimeException("Não foi possível editar o registro!");
        }finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

        }

    }

    /**
     * Realiza a deleção de um registro no banco de dados
     * @param people objeto de referencia para realizar a remoção no banco de dados
     *
     * @return retorana true no caso de sucesso de edição, ou levanta uma exceção caso contrário
     */
    @Override
    public int remove(Pessoa people) throws SQLException {

        int resultExecute = 0;

        query = "DELETE from People WHERE cpf =?;";
        PreparedStatement preparedStatement = null;

        connection.setAutoCommit(false);
        try {

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, people.getCpf());

            System.out.println(preparedStatement.toString());


            resultExecute = preparedStatement.executeUpdate();

            connection.commit();
            System.out.println(resultExecute);



        }catch (Exception e){

            e.printStackTrace();
            throw new RuntimeException("Não foi possível remover o registro!");
        }finally {


            if (preparedStatement != null) {
                preparedStatement.close();
            }
            return resultExecute;
        }

    }

    @Override
    public boolean removeAll() throws SQLException {
        boolean resultExecute = false;

        query = "DELETE FROM People;";

        PreparedStatement preparedStatement = null;
        connection.setAutoCommit(false);

        try {

            preparedStatement = connection.prepareStatement(query);
            resultExecute = preparedStatement.executeUpdate() == 1?true:false;

            connection.commit();

            return resultExecute;

        }catch (Exception e){

            e.printStackTrace();
            throw new RuntimeException("Não foi possível remover todos os registro!");
        }finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

        }

    }

    private static Timestamp getTime() {

        Date today = new Date();
        return new Timestamp(today.getTime());

    }


}
