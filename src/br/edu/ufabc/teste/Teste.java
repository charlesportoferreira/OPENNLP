/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufabc.teste;

import br.edu.ufabc.OpenNLP.TesteOpenNLP;
import br.edu.ufabc.util.Util;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charleshenriqueportoferreira
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        String text = "I am Luffy, the best warrior of the seas.";
        TesteOpenNLP t = new TesteOpenNLP();
//        //t.sentenceDetector("text");
//        String[] tokens = t.tokenizer(text);
//        String[] taggedText = t.posTagger(tokens);
//        t.printPosTaggedText(tokens, taggedText);

        //Util.lerNomeArquivos();
        // Util.fileTreePrinter(new File("/Users/charleshenriqueportoferreira/Dropbox/pretext/textos/"), 0); 
        // Util.fileTreePrinter(new File("/Users/charleshenriqueportoferreira/abc/"), 0);
        List<String> textos = Util.fileTreePrinter(new File("/Users/charleshenriqueportoferreira/Dropbox/pretext/textos/"), 0);
        Set<String> adverbios = new HashSet<>();
        for (String texto : textos) {
            if (texto.contains(".txt")) {
                try {
                    String[] tokens = t.tokenizer(Util.lerArquivo(texto));
                    String[] taggedText = t.posTagger(tokens);
                    //t.printPosTaggedText(tokens, taggedText);
                    adverbios.addAll(t.getAdverbios(tokens, taggedText));

                } catch (IOException ex) {
                    Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        for (String verbo : adverbios) {
            System.out.println(verbo);
        }
        System.out.println(adverbios.size());
        String stopList = Util.insertStopListTag(adverbios);
        System.out.println(stopList);
        try {
            Util.printFile("/Users/charleshenriqueportoferreira/adverbios.xml", stopList);
        } catch (IOException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
