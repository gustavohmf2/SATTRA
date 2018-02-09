package facade;

import apiAccess.CepConsult;
import dataAccess.IPeopleDao;
import dataAccess.PeopleDAO;
import dataExport.ExportTxt;
import models.Endereco;
import models.Pessoa;
import validation.CpfValidation;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Classe de faixada para todas as funcionalidades do sistema, criada para facilitar
 * a interação da classe UserInterace. Segue o padrão Facade.
 *
 * Gustavo Henrique Miguel Ferreira
 */
public class FacadeFuntionalities {

    private CepConsult cepConsult;
    private CpfValidation cpfValidation;
    private IPeopleDao peopleDAO;
    private ExportTxt exportTxt;
    private FacadeFuntionalities funtionalities;

    public FacadeFuntionalities(){

        cepConsult = new CepConsult();
        cpfValidation = new CpfValidation();
        peopleDAO = new PeopleDAO();
        exportTxt = new ExportTxt();
    }

    public int addPeople(String[] people){

        Pessoa p = new Pessoa(
          people[0],
          people[1],
                people[2],
                people[3],
                people[4],
                people[5],
                people[6],
                people[7],
                people[8],
                people[9],
                people[10],
                people[11]
        );
        int success = 0;

        try {
            peopleDAO.insert(p);
        } catch (SQLIntegrityConstraintViolationException e) {
            success = 1;
            e.printStackTrace();
        } catch (SQLException e) {
            success = 2;
            e.printStackTrace();
        }finally {
            return success;
        }

    }

    public String[] findPeople(String cpf){

        Pessoa p = null;
        String[] result = new String[12];

        try {
            p = peopleDAO.findByCPF(cpf);

                result[0] = p.getNome();
                result[1] = p.getCpf();
                result[2] = p.getContatos().getTelfone();
                result[3] = p.getContatos().getEmail();
                result[4] = p.getEndereco().getLogradouro();
                result[5] = p.getEndereco().getNumero();
                result[6] = p.getEndereco().getComplemento();
                result[7] = p.getEndereco().getBairro();
                result[8] = p.getEndereco().getLocalidade();
                result[9] = p.getEndereco().getCep();
                result[10] = p.getEndereco().getUf();
                result[11] = p.getObservacoes();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int removePeople(String cpf){

        Pessoa tmp = null;
        int success = 0;
        try {
            tmp = peopleDAO.findByCPF(cpf);
            success = peopleDAO.remove(tmp);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return success;
        }
    }

    public void editPeople(String[] novaPessoa){

    }

    public void exportData(String dir){

        try {
            exportTxt.exportarTxt(dir,peopleDAO.findAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean validCpf(String cpf){

        return cpfValidation.isValid(cpf);
    }

    public Endereco consultCepApi(String cep){
        Endereco endereco = cepConsult.consutarCep(cep);
        return endereco;
    }
}
