package ui;

import facade.FacadeFuntionalities;
import models.Endereco;
import java.util.Scanner;

/**
 * Interface via linha de comando para que o usuário possa interagir com o sistema,
 * e realizar as funcionalidades
 *
 * @author Gustavo Henrique Miguel Ferreira
 */
public class UserInterface {


    private FacadeFuntionalities funtionalities;
    private String[] fildsPeople;

    public UserInterface(){

        funtionalities = new FacadeFuntionalities();
        initArrayPeople();
    }

    public void show() {

        int exit;
        String[] campo = new String[12];

        do {
        //********************************************************************

            System.out.println("#############################################################");
            System.out.println("#\t Bem vindo ao cadastro de Pessoas \t\t\t\t\t\t#");
            System.out.println("#\t  Escolha as opções abaixo:       \t\t\t\t\t\t#");
            System.out.println("#\t\t\t\t  1) Cadastrar nova pessoa  \t\t\t\t#");
            System.out.println("#\t\t\t\t  2) Editar Pessoa          \t\t\t\t#");
            System.out.println("#\t\t\t\t  3) Buscar por cpf         \t\t\t\t#");
            System.out.println("#\t\t\t\t  4) Remover pessoa por cpf  \t\t\t\t#");
            System.out.println("#\t\t\t\t  5) Exportar dados         \t\t\t\t#");
            System.out.println("#############################################################");

            Scanner inputSel = new Scanner(System.in);

            exit = inputSel.nextInt();

            switch (exit) {


                case 1:

                    int opcao = 1;

                    do {
                        //************************************************************

                        if(opcao == 1) {
                            System.out.println("Preencha cada campo abaixo:");

                            System.out.println("Nome:");
                                campo[0] = inputSel.next();
                                inputSel = new Scanner(System.in);

                            System.out.println("Cpf(Digite todos os digitos):");
                            boolean valid = false;
                                do{
                                    campo[1] = inputSel.next();
                                    inputSel = new Scanner(System.in);
                                    valid = funtionalities.validCpf(campo[1]);

                                    if(!valid){
                                        System.out.println("Cpf incorreto, digite novamente!");
                                    }
                                }while (!valid);

                            System.out.println("Telefone:");
                                campo[2] = inputSel.next();
                                inputSel = new Scanner(System.in);
                            System.out.println("Email:");
                                campo[3] = inputSel.next();
                                inputSel = new Scanner(System.in);
                            System.out.println("Cep:");
                                campo[9] = inputSel.next();
                                inputSel = new Scanner(System.in);

                            Endereco endereco = funtionalities.consultCepApi(campo[9]);
                            if(endereco != null){
                                campo[4] = endereco.getLogradouro();
                                campo[5] = endereco.getNumero();
                                campo[6] = endereco.getComplemento();
                                campo[7] = endereco.getBairro();
                                campo[8] = endereco.getLocalidade();
                                campo[10] = endereco.getUf();

                            }else {
                                System.out.println("Logradouro:");
                                campo[4] = inputSel.next();
                                inputSel = new Scanner(System.in);
                                System.out.println("Numero:");
                                campo[5] = inputSel.next();
                                inputSel = new Scanner(System.in);
                                System.out.println("Complemento:");
                                campo[6] = inputSel.next();
                                inputSel = new Scanner(System.in);
                                System.out.println("Bairro:");
                                campo[7] = inputSel.next();
                                inputSel = new Scanner(System.in);
                                System.out.println("Localidade:");
                                campo[8] = inputSel.next();
                                inputSel = new Scanner(System.in);
                                System.out.println("Uf:");
                                campo[10] = inputSel.next();
                                inputSel = new Scanner(System.in);
                            }

                                for (int k = 4; k <= 10;k++){
                                    System.out.println(fildsPeople[k] + campo[k]);
                                }

                                System.out.println("Observações:");
                                campo[11] = inputSel.next();
                                inputSel = new Scanner(System.in);

                            for (String c : campo) {

                                System.out.println(c);

                            }

                        }else if(opcao == 2){

                            int result = funtionalities.addPeople(campo);
                            if(result == 1) {
                                System.out.println("já existe uma pessoa cadastrada com o cpf digitado!");
                                System.out.println("Preencha o cadastro novamente!");
                            }else if(result == 2){
                                System.out.println("Algum campo digitado está incorreto");
                                System.out.println("Preencha o cadastro novamente!");
                            }
                        }

                        System.out.println();
                        System.out.println("Digite 1) caso queira preencecher os campos novamente");
                        System.out.println("Digite 2) caso queira efetuar o cadastro");
                        System.out.println("Digite qualquer outro botão para sair");

                        opcao = inputSel.nextInt();
                        }while (!(opcao != 1 || opcao != 2)) ;
                        //************************************************************
                    break;

                case 2:

                    String cpf = "";
                    System.out.println("Digite o cpf (com todos os digitos) da pessoa a ser editada:");

                    cpf = inputSel.next();

                    int option = 1;

                    do {
                        int i = 0;

                        System.out.println("Digite o numero da linha para editar a informação desta linha");
                        String[] fields = funtionalities.findPeople(cpf);

                        if (!(fields.length == 0)) {

                            for (String people : fields) {

                                System.out.println("" + i + ") " + people);
                            }

                            opcao = inputSel.nextInt();
                            fields[opcao] = inputSel.next();
                        }

                    }while (option != 0);

                    break;

                case 3:

                    System.out.println("Digite o cpf:");
                    cpf = inputSel.next();

                    System.out.println("Resultado da pesquisa: ");

                    int j = 0;
                    for (String c : funtionalities.findPeople(cpf)){
                        System.out.println(fildsPeople[j] + c);
                        j++;

                    }

                    break;

                case 4:

                    System.out.println("Digite o cpf da pessoa que deseja remover:");
                    cpf = inputSel.next();

                    int result = funtionalities.removePeople(cpf);
                    if(result == 1){
                        System.out.println("Remoção realizada com sucessso!");
                    }else {
                        System.out.println("Desculpe, ocorreu algum erro durante a remoção");
                    }


                    break;

                case 5:

                    System.out.println("Digite o diretorio no qual deseja exportar os dados:");
                    String dir = inputSel.next();

                    funtionalities.exportData(dir);
                    System.out.println("Confira no diretorio o arquivo de exportação");

                    break;

                default:

                    break;
            }
    //********************************************************************
        } while (exit != 0);
    }

    public void initArrayPeople(){

        fildsPeople = new String[12];

        fildsPeople[0] = "Nome: ";
        fildsPeople[1] = "Cpf: ";
        fildsPeople[2] = "Telefone: ";
        fildsPeople[3] = "Email: ";
        fildsPeople[4] = "Logradouro: ";
        fildsPeople[5] = "Numero: ";
        fildsPeople[6] = "Complemento: ";
        fildsPeople[7] = "Bairro: ";
        fildsPeople[8] = "Localidade: ";
        fildsPeople[9] = "Cep: ";
        fildsPeople[10] = "UF: ";
        fildsPeople[11] = "Observaçôes: ";

    }
}
