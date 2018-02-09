package dataExport;

import models.Pessoa;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 *  Classe responsável por exportar os dados dos registro de People que estão salvos
 *  no banco de dados para um arquivo txt
 *
 * @author Gustavo Henrique Miguel Ferreira
 */
public class ExportTxt {

    public ExportTxt(){

    }

    /**
     * Método que realiza a exportação dos dados vindos do banco de dados para um arquivo txt,
     * salvará no diretóio informado como parametro, o nome do arquivo é gerado a parti de uma
     * compinação entre a palavra backup e o data do sistema em milisegundos.
     *
     * @param dir diretório onde deverá ser salvo o arquivo de exportação
     * @param peoples lista de registros que será inserido no arquivo de texto
     * @return
     */
    public boolean exportarTxt(String dir, List<Pessoa> peoples){

        Date date = new Date();

        String nameFile = "export_" + date.getTime() + ".txt";
        System.out.println(nameFile);


        File file = new File(dir+"/"+nameFile);


        try{

            if(!file.exists()){

                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);


            StringBuffer conteudo;

            for (Pessoa people:peoples) {

                conteudo = new StringBuffer();

                    conteudo.append(people.getNome());
                        conteudo.append(people.getNome() != null ? "#" : "");
                    conteudo.append(people.getCpf());
                        conteudo.append(people.getCpf() != null ? "#" : "");
                    conteudo.append(people.getContatos().getTelfone());
                        conteudo.append(people.getContatos().getTelfone() != null ? "#" : "");
                    conteudo.append(people.getContatos().getEmail());
                        conteudo.append(people.getContatos().getEmail() != null ? "#" : "");
                    conteudo.append(people.getEndereco().getCep());
                        conteudo.append(people.getEndereco().getCep() != null ? "#" : "");
                    conteudo.append(people.getEndereco().getLogradouro());
                        conteudo.append(people.getEndereco().getLogradouro() != null ? "#" : "");
                    conteudo.append(people.getEndereco().getNumero());
                        conteudo.append(people.getEndereco().getNumero() != null ? "#" : "");
                    conteudo.append(people.getEndereco().getComplemento());
                        conteudo.append(people.getEndereco().getComplemento() != null ? "#" : "");
                    conteudo.append(people.getEndereco().getBairro());
                        conteudo.append(people.getEndereco().getBairro() != null ? "#" : "");
                    conteudo.append(people.getEndereco().getLocalidade());
                        conteudo.append(people.getEndereco().getLocalidade() != null ? "#" : "");
                    conteudo.append(people.getEndereco().getUf());
                        conteudo.append(people.getEndereco().getUf() != null ? "#" : "");
                    conteudo.append(people.getObservacoes());
                        conteudo.append(people.getObservacoes() != null ? "#" : "");

                bw.write(conteudo.toString());
                bw.newLine();

            }

            bw.close();
            fw.close();



        }catch (IOException e){

            e.printStackTrace();
        }

        return true;
    }
}
