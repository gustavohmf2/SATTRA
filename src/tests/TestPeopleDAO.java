package tests;

import dataAccess.IPeopleDao;
import dataAccess.PeopleDAO;
import models.Contatos;
import models.Pessoa;
import org.junit.*;
import org.junit.runners.MethodSorters;

import javax.annotation.PostConstruct;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

public class TestPeopleDAO {

    public static IPeopleDao peopleDAO = new PeopleDAO();
    public static Pessoa people;
    public static Pessoa p1;
    public static Pessoa p2;
    public static Pessoa p3;

    @BeforeClass
    public static void init() throws SQLException {

        p1 = new Pessoa("Mario ",
                "123456-50",
                "998543278",
                "Mario@gmail.com",
                "59140-780",
                "Rua Dos Carvalhos",
                "17",
                "Igreja Renascer",
                "Jardim Florido",
                "Natal",
                "RN",
                "");

        peopleDAO.insert(p1);

        p2 = new Pessoa("Gabriel",
                "214256791-50",
                "998543278",
                "Gabriel@gmail.com",
                "59140-780",
                "Rua Das Flores",
                "7",
                "Igreja Renascer",
                "Jardim Florido",
                "Natal",
                "RN",
                "");

        peopleDAO.insert(p2);

        p3 = new Pessoa("Fernanda",
                "928517283-43",
                "998543278",
                "fernanda@gmail.com",
                "59140-780",
                "Rua Azul",
                "1",
                "Carrefour",
                "Vieira",
                "Parnamirim",
                "RN",
                "");

        peopleDAO.insert(p3);

    }

    @AfterClass
    public static void clean() throws SQLException {

        System.out.println("Cleaning...");
        System.out.println(p1.getNome());
        peopleDAO.remove(p1);
        peopleDAO.remove(p2);
        peopleDAO.remove(p3);
        peopleDAO.remove(people);
    }




    @Test
    public void testInsert() throws SQLException {
        System.out.println("Insert");
        people = new Pessoa("Claudio",
                "321456789-50",
                "998543278",
                "tahyna@gmail.com",
                "59140-780",
                "Rua Das Papoulas",
                "21",
                "Igreja Renascer",
                "Jardim Florido",
                "Parnamirim",
                "RN",
                "");

        peopleDAO.insert(people);

        Pessoa teste = peopleDAO.findByCPF("321456789-50");

        Assert.assertEquals(teste.getCpf(),people.getCpf());

    }



    @Test(expected = SQLIntegrityConstraintViolationException.class)
    public void testPrimaryKeyViolation(){
        System.out.println("violatio");
        people = new Pessoa("Claudio",
                "321456789-50",
                "998543278",
                "tahyna@gmail.com",
                "59140-780",
                "Rua Das Papoulas",
                "21",
                "Igreja Renascer",
                "Jardim Florido",
                "Parnamirim",
                "RN",
                "");

    }


    @Test
    public void testFindByCPF() throws SQLException {
        System.out.println("Bycpf");
        Pessoa p = peopleDAO.findByCPF("928517283-43");


        Pessoa test = new Pessoa("Fernanda",
                "928517283-43",
                "998543278",
                "fernanda@gmail.com",
                "59140-780",
                "Rua Azul",
                "12",
                "Carrefour",
                "Vieira",
                "Parnamirim",
                "RN",
                "");

        Assert.assertEquals(test.getCpf(),p.getCpf());

    }



    @Test
    public void testUpdate() throws SQLException {

        System.out.println("update");
        Pessoa current = peopleDAO.findByCPF("321456789-50");
        String currentEmail = current.getContatos().getEmail();

        Pessoa test = current;

        Contatos c = new Contatos("94357619","claudeio@gmail.com");

        test.setContatos(c);

        peopleDAO.edit(test);

        test = peopleDAO.findByCPF("321456789-50");
        System.out.println(currentEmail);

        System.out.println(test.getContatos().getEmail());
        Assert.assertNotEquals(currentEmail,test.getContatos().getEmail());



    }



    @Test
    public void testRemove() throws SQLException {
        //inserir um registro e remove-lo, realizar busca e verificar se ainda está
        // persistido em banco

        Pessoa test = peopleDAO.findByCPF("214256791-50");
        int result = peopleDAO.remove(test);


        Assert.assertEquals(1,result);


    }



}
