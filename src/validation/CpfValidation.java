package validation;


/**
 *  Classe que realiza a validação do cpf, de acordo com o algoritmos de verificação tradicional utilizado
 *  Somatorio da multiplicação dos primeiros 9 digitos do cpf em que multiplica-se cada digito pela sequencia
 *  decrescente inciando de 10, pega-se o resultado e faz a divisão com resto apartir desse resultado subtraimos
 *  de 11 o resultado será o primeiro digito validador, para o segundo digito validador seguimos o mesmo passo
 *  anterior com a diferença que ao inves de começarmos a multiplicação a partir do 10, será pelo 11 e utilizaremos
 *  os primeiros 10 digitos. se o resultado dos dois digitos forem iguais aos digitos verificadoresdo cpf então o
 *  cpf é válido.
 *
 *
 * @author Gustavo Henrique Miguel Ferreira
 */
public class CpfValidation {


    /**
     *
     * @param cpf valor com o cpf que passará pela validação
     * @return true caso o cpf seja válido e false caso contrário
     */
    public boolean isValid(String cpf){

        cpf = cpf.replace(".","").replace("-","");


        if(!cpfsCorretosInvalidos(cpf) || cpf.length() < 11) return false;

        //array de caracteres do cpf
        char[] digitosTemp = cpf.toCharArray();
        //array de inteiro do cpf
        int[] digitos = new int[11];
        //aray de digitos para multiplicação
        int[] multDigits = new int[10];

        int i = 0;
        //somatorio para o primeiro digito
        int sumFirst = 0;
        //somatorio para o segundo digito
        int sumSecond = 0;

        int firstDigit;
        int secondDigit;

        for(int j = 0;j < multDigits.length;j++){

            multDigits[j] = 11 - j;
        }

        int k = 10;
        int f = 0;

        for (int g = 0; g <= digitosTemp.length-1;g++) {
            digitos[g] = (int) (digitosTemp[g] - 48);
        }

        for (int d = 0;d < digitos.length -2;d++) {

                sumFirst += (digitos[d] * multDigits[11 - k]);

            k--;
            i++;
        }

        firstDigit = sumFirst % 11;
        firstDigit = (11 - firstDigit)%10;

        if(!(firstDigit == digitos[9])){
            return false;
        }

        //------------------------- Segundo dígito verificador ------------------------------------

        k = 11;
        f = 0;

        for (int d = 0;d < digitos.length -1;d++) {

            sumSecond += (digitos[d] * multDigits[11 - k]);

            k--;
            i++;
        }

        secondDigit = (sumSecond % 11) % 10;
        secondDigit = (11 - secondDigit)%10;

        if(secondDigit == digitos[10]){
            return true;
        }else {
            return false;
        }
    }

    /**
     * Como temos os casos que passam na validação, mas não são cpfs valido esse método serve
     * para verificar esses caso e eliminar logo de cara esses casos
     *
     * @param cpf
     * @return true caso o cpf seja válido e false caso contrário
     */
    public boolean cpfsCorretosInvalidos(String cpf){

        if(cpf.equals("11111111111")){
            return false;
        }else if(cpf.equals("22222222222")){
            return false;
        }else if(cpf.equals("33333333333")){
            return false;
        }else if(cpf.equals("44444444444")){
            return false;
        }else if(cpf.equals("55555555555")){
            return false;
        }else if(cpf.equals("66666666666")){
            return false;
        }else if(cpf.equals("77777777777")){
            return false;
        }else if(cpf.equals("88888888888")){
            return false;
        }else if(cpf.equals("99999999999")){
            return false;
        }else{
            return true;
        }
    }

}
