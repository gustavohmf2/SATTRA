package tests;

import org.junit.Assert;
import org.junit.Test;
import validation.CpfValidation;

public class TestCpfValidation {


    private CpfValidation cpfValidation = new CpfValidation();

    @Test
    public void testFalseValid(){

        boolean test = cpfValidation.isValid("111111111-11");
        Assert.assertFalse(test);

    }

    @Test
    public void testNonValid(){

        boolean test = cpfValidation.isValid("021345672-21");
        Assert.assertFalse(test);
    }

    @Test
    public void testIsValid(){

        boolean test = cpfValidation.isValid("092325784-50");
        Assert.assertTrue(test);
    }

}
