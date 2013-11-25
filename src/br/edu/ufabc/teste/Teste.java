/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufabc.teste;

import br.edu.ufabc.OpenNLP.TesteOpenNLP;
//import br.edu.ufabc.util.Util;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author charleshenriqueportoferreira
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int a = 0;
        TesteOpenNLP t = new TesteOpenNLP();
        int tamanho = 500;
        String retorna = "\b";
        for (int i = 0; i < 100; i++) {
//            if ((i * 100.0 / tamanho) % 10.0 == 0) {
//                retorna += retorna;
//            }
            for (int j = 0; j < 50000000; j++) {
                a = a + a;
            }
            System.out.print(retorna);
            System.out.print(i);

        }

        if (args.length != 0) {
            switch (args[0]) {
                case "-p":
                    t.printFilesPath();
                    break;
                case "-v":
                    t.geraVerboStopList();
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
            //  help();
        }

//        String text = "I am Luffy, the best warrior of the seas.";
        //TesteOpenNLP t = new TesteOpenNLP();
//        //t.sentenceDetector("text");
//        String[] tokens = t.tokenizer(text);
//        String[] taggedText = t.posTagger(tokens);
//        t.printPosTaggedText(tokens, taggedText);
        //Util.lerNomeArquivos();
        // Util.fileTreePrinter(new File("/Users/charleshenriqueportoferreira/Dropbox/pretext/textos/"), 0); 
        // Util.fileTreePrinter(new File("/Users/charleshenriqueportoferreira/abc/"), 0);
//        List<String> textos = Util.fileTreePrinter(new File("/Users/charleshenriqueportoferreira/Dropbox/pretext/textos/"), 0);
//        String diretorio = System.getProperty("user.dir");
//        //List<String> textos = Util.fileTreePrinter(new File(diretorio), 0);
//        Set<String> adverbios = new HashSet<>();
//        for (String texto : textos) {
//            if (texto.contains(".txt")) {
//                try {
//                    String[] tokens = t.tokenizer(Util.lerArquivo(texto));
//                    String[] taggedText = t.posTagger(tokens);
//                    //t.printPosTaggedText(tokens, taggedText);
//                    adverbios.addAll(t.getAdverbios(tokens, taggedText));
//
//                } catch (IOException ex) {
//                    Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//
//        for (String verbo : adverbios) {
//            System.out.println(verbo);
//        }
//        System.out.println(adverbios.size());
//        String stopList = Util.insertStopListTag(adverbios);
//        System.out.println(stopList);
//        try {
//            Util.printFile("/Users/charleshenriqueportoferreira/adverbios.xml", stopList);
//        } catch (IOException ex) {
//            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public static void help() {
        System.out.println("Faltou passar algum argumento:");
        System.out.println("-p = imprime arquivos txt da pasta de origem");
        System.out.println("-v = imprime os verbos dos arquivos");
        System.out.println("-ad = imprime os adverbios");
    }

}
