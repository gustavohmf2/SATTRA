package dataAccess;

import models.Pessoa;

import java.sql.SQLException;
import java.util.List;

public interface IPeopleDao {

    public void insert(Pessoa people) throws SQLException;
    public Pessoa findByCPF(String cpf) throws SQLException;
    public List<Pessoa> findAll() throws SQLException;
    public boolean edit(Pessoa people) throws SQLException;
    public int remove(Pessoa people) throws SQLException;
    public boolean removeAll() throws SQLException;
}
