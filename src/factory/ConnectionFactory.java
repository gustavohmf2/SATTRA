package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Tem a responsabilidade de iniciar uma conexão com o banco de dados, criando somente
 * uma instância dessa classe para não ficar abrindo várias conexões durante o ciclo de
 * vidad da aplicação.
 *
 * @author Gustavo Henrique Miguel Ferreira
 */
public class ConnectionFactory {

    public static ConnectionFactory connectionFactory;

    private ConnectionFactory(){

    }

    /**
     * Criação de um singleton, para garantir que somente uma instância da classe será
     * criada.
     *
     * @return instância dessa mesma classe
     */
    synchronized public static ConnectionFactory getInstance(){

        if(connectionFactory == null){
            return new ConnectionFactory();
        }else{
            return connectionFactory;
        }

    }

    /**
     * Método que retorna a conexão com o banco de dados, para termos acesso aos métodos de
     * interação com o banco de dados (Ex: salvar, editar ...)
     *
     * @param url: url em que o seu banco está configurado
     * @param usuario: usuário de acesso ao banco
     * @param senha: senha de acesso ao banco
     * @return
     */
    public Connection getConnection(String url, String usuario, String senha){

        try {

            return DriverManager.getConnection(url, usuario, senha);

        } catch (SQLException e) {

            e.printStackTrace();
            throw new RuntimeException("Não foi possível criar uma conexão com o banco de dados configurado!");
        }
    }
}
