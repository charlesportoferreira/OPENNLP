/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufabc.teste;

import br.edu.ufabc.OpenNLP.TesteOpenNLP;

/**
 *
 * @author charleshenriqueportoferreira
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String text = "I am Luffy, the best warrior of the seas.";
        TesteOpenNLP t = new TesteOpenNLP();
        //t.sentenceDetector("text");
        String[] tokens = t.tokenizer(text);
        String[] taggedText = t.posTagger(tokens);
        t.printPosTaggedText(tokens, taggedText);
    }

}
