/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufabc.teste;

import br.edu.ufabc.OpenNLP.TesteOpenNLP;
import br.edu.ufabc.util.Util;
import java.util.List;

/**
 *
 * @author charleshenriqueportoferreira
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        TesteOpenNLP t = new TesteOpenNLP();

        if (args.length != 0) {
            switch (args[0]) {
                case "-p":
                    t.printFilesPath();
                    break;
                case "-v":
                    t.geraVerboStopList();
                    break;
                case "-ptt":
                    t.printTokensAndTags();
                    break;
                case "-ad":
                    t.geraAdverbiosStopList();
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
