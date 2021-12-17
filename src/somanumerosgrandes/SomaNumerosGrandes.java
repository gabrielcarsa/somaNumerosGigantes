package somanumerosgrandes;

import java.util.Scanner;

public class SomaNumerosGrandes {

    static class LISTA {

        int num;
        LISTA prox;
    }

    //variável de entrada
    public static Scanner entrada = new Scanner(System.in);

    //variáveis auxiliares
    static LISTA aux;
    static LISTA aux1;
    static LISTA aux2;

    //lista de elemento 1 e 2
    static LISTA fimL1;
    static LISTA inicioL1;
    static LISTA fimL2;
    static LISTA inicioL2;

    //lista de elemento do resultado
    static LISTA fimResultado;
    static LISTA inicioResultado;

    public static void main(String[] args) {
        String num1, num2;

        //recebendo String 1 e 2
        System.out.print("Digite o primeiro número: ");
        num1 = entrada.nextLine();
        System.out.print("Digite o segundo número: ");
        num2 = entrada.nextLine();

        //chamando método para conversão de String para LISTA
        converterString(num1, num2);

        //chamando método para somar as LISTAS
        calculandoSoma(num2);

        //chamando método para exibir a LISTA de resultado da soma
        exibirResultado();

    }

    public static void converterString(String num1, String num2) {
        //convertendo String em LISTA de elementos
        for (int i = 0; i < num1.length(); i++) {
            LISTA resultado = new LISTA();
            resultado.num = num1.charAt(i) - '0';
            //o elemento é o primeiro a ser inserido!!
            if (inicioL1 == null) {
                inicioL1 = resultado;
                fimL1 = resultado;
                resultado.prox = null;
            } else { //NÃO é o primeiro a ser inserido!!
                resultado.prox = inicioL1;
                inicioL1 = resultado;
            }
        }

        //fazendo o mesmo para num2
        for (int i = 0; i < num2.length(); i++) {
            LISTA resultado = new LISTA();
            resultado.num = num2.charAt(i) - '0';
            //o elemento é o primeiro a ser inserido!!
            if (inicioL2 == null) {
                inicioL2 = resultado;
                fimL2 = resultado;
                resultado.prox = null;
            } else { //NÃO é o primeiro a ser inserido!!
                resultado.prox = inicioL2;
                inicioL2 = resultado;
            }
        }
    }

    public static void calculandoSoma(String num2) {

        //variáveis auxiliares
        aux1 = inicioL1;
        aux2 = inicioL2;

        //variável de controle para saber se subiu um número
        boolean subiuNum = false;

        //variável para armazenar o valor que subir da soma
        int subirAux = 0;

        //String auxiliar para armazenar valor de resultado > 9
        String stringResultado;

        for (int i = 0; i < num2.length(); i++) {
            
            //instaciando variável do tipo LISTA 
            LISTA resultadoSoma = new LISTA();
            
            //neste programa sempre é considerado que o número 2 < que número 1
            if (aux1 == null) {//Se houver acabado os elementos de 1
                if (aux2.num + subirAux > 9 && subiuNum == true) {
                    stringResultado = aux2.num + subirAux + "";//usando String auxiliar para armazenar resultado
                    resultadoSoma.num = stringResultado.charAt(1) - '0';//pegando segunda casa e atribuindo a resultado
                    subirAux = stringResultado.charAt(0) - '0';//pegando primeira casa para subir número
                    subiuNum = true;//como subiu um número para somar, true
                } else if (aux2.num + subirAux < 10 && subiuNum == true) {
                    resultadoSoma.num = aux2.num + subirAux;
                    subirAux = 0;//zerando valor
                    subiuNum = false;//como não subiu número, false
                } else {
                    resultadoSoma.num = aux2.num;
                    subiuNum = false;
                    subirAux = 0;

                }
                aux2 = aux2.prox;//avançando posição
            } else {
                if(aux2.prox == null && subiuNum == true){//Se não houver mais elementos soma direto e se tiver subido algum número
                    resultadoSoma.num = aux1.num + aux2.num + subirAux;
                    subirAux = 0;
                    subiuNum = false;
                }
                else if (aux1.num + aux2.num + subirAux > 9) {//
                    stringResultado = aux1.num + aux2.num + subirAux + "";
                    resultadoSoma.num = stringResultado.charAt(1) - '0';
                    subirAux = stringResultado.charAt(0) - '0';
                    subiuNum = true;
                } else if (aux1.num + aux2.num + subirAux < 10) {
                    resultadoSoma.num = aux1.num + aux2.num + subirAux;
                    subirAux = 0;
                    subiuNum = false;

                } else {
                    resultadoSoma.num = aux2.num + aux1.num;
                    subiuNum = false;
                    subirAux = 0;
                }
                //avançando posição
                aux1 = aux1.prox;
                aux2 = aux2.prox;

            }
            
            if (inicioResultado == null) {
                inicioResultado = resultadoSoma;
                fimResultado = resultadoSoma;
                resultadoSoma.prox = null;
            } else {
                resultadoSoma.prox = inicioResultado;
                inicioResultado = resultadoSoma;
            }

        }
    }
    
    //Método para exibir Lista de Elementos de resultado da soma
    public static void exibirResultado() {
        if (inicioResultado == null) {
            System.out.println("Lista vazia");
        } else {
            aux = inicioResultado;
            while (aux != null) {
                System.out.print(aux.num);
                aux = aux.prox;
            }
        }
        System.out.println("");
    }

}
