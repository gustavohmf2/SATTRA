package apiAccess;

import models.Endereco;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsável por fazer uma requisição a api viacep, utilizando a biblioteca Java.net,
 * para realizar a consulta a api: primeiro inoformamos a url com o campo cep colocado devidaemente
 * em seu lugar, abrimos a conexão via URLConnection, fazemos a requisição e lemos a resposta do servidor
 * da api utilizando um BufferedReader, percorremos cada linha da resposta aramazenando o resultado em
 * StringBuffer e formatamos os dados para se ficar compatível com nossa classe Endereco
 *
 * @author Gustavo Henrique Miguel Ferreira
 */
public class CepConsult {

    private String cep;
    private Endereco endereco;


    public CepConsult() {

    }

    /**
     * Método que realiza a requisição a api informando a ulr com o cep desejado e ler os dados
     * da resposta da requisição num BufferedReader, para que possamos iterar sobre eles
     *
     * @param cep informação que utilizaremos como parametro para consultar a api
     * @return retorna um objeto Endereco preenchido caso a api retorne informações não nulas e caso
     * contrario retornaremos um objeto nulo
     */
    public Endereco consutarCep(String cep) {

        String urlConsult = "http://viacep.com.br/ws/" + cep + "/json";

        try {

            URL url = new URL(urlConsult);

            URLConnection con = url.openConnection();

            con.setDoInput(true);
            con.setDoOutput(false);

            con.connect();

            //if (!validResponse(con)) {
             //   return null;
           // }

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

            responseFormater(br);

            br.close();


        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            return endereco;
        }

    }

    /**
     * Nesse método formatamos as dados da resposta da requisição que está armazenado no buffer
     * como está em formato JSON, temos que retirar os {}; , e "", para então montarmos o HashMap
     * e ao final utilizarmos as informações deste map para preencher o objeto endereco
     *
     * @param br é o buffer de dados que armazena a resposta da requisição a api
     */
    public void responseFormater(BufferedReader br) {


        StringBuffer resultUrl = new StringBuffer();
        String ln = "";

        try {

            while (null != ((ln = br.readLine()))) {

                if (!(ln.equals("{") || ln.equals("}"))) {
                    resultUrl.append(ln);
                }

            }


            String[] endCampos = resultUrl.toString().split(",");

            Map<String, String> objeto = new HashMap<String, String>();

            for (String a : endCampos) {

                String[] campo = a.split(":");
                objeto.put(campo[0].
                                replace("\"", "").trim(),
                        campo[1].replace("\"", "").trim());

            }


            for (String key : objeto.keySet()) {

                String value = objeto.get(key).trim();
            }


            endereco = new Endereco(
                    objeto.get("logradouro"),
                    null,
                    objeto.get("complemento"),
                    objeto.get("bairro"),
                    objeto.get("localidade"),
                    objeto.get("cep"),
                    objeto.get("uf")
            );


        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    /**
     * Verifica se a resposta da requisição retorna um objeto diferente de json
     *
     * @param con a conexão iniciada que faz a requisição a api
     * @return tru caso a resposta seja um objeto json false caso contrário
     */
    public boolean validResponse(URLConnection con) {


        StringBuffer resultUrl = new StringBuffer();
        String ln = "";
        boolean isValid = true;


        System.out.println("validaton: " + con.getHeaderField("Content-Type"));

        if ((con.getHeaderField("Content-Type").equals("text/html; charset=utf-8"))) {
            isValid = false;

        }else {
            if (isValid){
                try {

                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    while (null != ((ln = br.readLine()))) {

                        if (ln.trim().contains("erro")) {
                            isValid = false;
                        }

                    }

                    if (resultUrl.toString().trim().contains("erro")) {
                        isValid = false;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        }
        return isValid;
    }
}

