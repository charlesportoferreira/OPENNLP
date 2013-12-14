/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufabc.teste;

import br.edu.ufabc.OpenNLP.PosTaggerOpenNLP;

/**
 *
 * @author charleshenriqueportoferreira
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        PosTaggerOpenNLP pto = new PosTaggerOpenNLP();
      

        if (args.length != 0) {
            switch (args[0]) {
                case "-p":
                    pto.printFilesPath();
                    break;
                case "-v":
                    pto.geraVerboStopList();
                    break;
                case "-ptt":
                    pto.printTokensAndTags();
                    break;
                case "-ad":
                    pto.geraAdverbiosStopList();
                    break;
                default:
                    System.out.println("Parâmetro digitado incorretamente");
                    help();
                    break;
            }
        } else {

            help();
        }

    }

    public static void help() {
        System.out.println("Faltou passar algum argumento:");
        System.out.println("-p = imprime arquivos txt da pasta de origem");
        System.out.println("-v = imprime os verbos dos arquivos");
        System.out.println("-ad = imprime os adverbios");
    }

}
