package tests;

import apiAccess.CepConsult;
import models.Endereco;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.FileNotFoundException;

public class TestApiCep {

    private CepConsult consult = new CepConsult();

    @Test
    public void testFoundCep(){

        Endereco test = consult.consutarCep("59140-780");

        boolean found;

        if(test.getBairro().equals("Cohabinal") &&
            test.getCep().equals("59140-780") &&
            test.getComplemento().equals("") &&
            test.getLogradouro().equals("Rua Professor Eliah Maia do Rêgo") &&
            test.getLocalidade().equals("Parnamirim") &&
            test.getUf().equals("RN")){
            found = true;
        }else{
            found = false;
        }

        Assert.assertTrue(found);

    }

    @Test
    public void testErroCep(){

        Endereco test = consult.consutarCep("5939S780");

        Assert.assertNull(test);
    }

    @Ignore
    @Test
    public void testInvalidCep(){

        Endereco test = consult.consutarCep("59392780");

        Assert.assertNull(test);
    }
}
