/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufabc.OpenNLP;

import java.io.IOException;
import java.io.InputStream;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

/**
 *
 * @author charleshenriqueportoferreira
 */
public class TesteOpenNLP {

    
    public String[] sentenceDetector(String text) {
        SentenceDetector sentenceDetector = null;
        InputStream modelIn = null;
        try {
            // Loading sentence detection model
            modelIn = getClass().getResourceAsStream("/resources/en-sent.bin");
            final SentenceModel sentenceModel = new SentenceModel(modelIn);
            modelIn.close();

            sentenceDetector = new SentenceDetectorME(sentenceModel);
            String[] sentences = sentenceDetector.sentDetect(text);

            return sentences;
        } catch (final IOException ioe) {
            ioe.printStackTrace();
            return null;
        } finally {
            if (modelIn != null) {
                try {
                    modelIn.close();
                } catch (final IOException e) {
                } // oh well!
            }
        }
    }

    public String[] tokenizer(String text) {
        Tokenizer tokenizer = null;
        InputStream modelIn = null;
        try {
            // Loading tokenizer model
            modelIn = getClass().getResourceAsStream("/resources/en-token.bin");
            final TokenizerModel tokenModel = new TokenizerModel(modelIn);
            modelIn.close();

            tokenizer = new TokenizerME(tokenModel);
            String[] tokens = tokenizer.tokenize(text);

            return tokens;
        } catch (final IOException ioe) {
            ioe.printStackTrace();
            return null;
        } finally {
            if (modelIn != null) {
                try {
                    modelIn.close();
                } catch (final IOException e) {
                } // oh well!
            }
        }
    }

    public String[] posTagger(String[] text) {
        Tokenizer tokenizer = null;
        InputStream modelIn = null;
        try {
            // Loading tokenizer model
            modelIn = getClass().getResourceAsStream("/resources/en-pos-maxent.bin");
            final POSModel posModel = new POSModel(modelIn);
            modelIn.close();
            POSTaggerME posTagger;
            posTagger = new POSTaggerME(posModel);

            String[] taggedText = posTagger.tag(text);

            System.out.println(taggedText);
            return taggedText;

        } catch (final IOException ioe) {
            ioe.printStackTrace();
            return null;
        } finally {
            if (modelIn != null) {
                try {
                    modelIn.close();
                } catch (final IOException e) {
                } // oh well!
            }
        }
    }

    public void printPosTaggedText(String[] tokens, String[] tags) {
        StringBuilder finalText = new StringBuilder();
        for (int i = 0; i < tokens.length; i++) {
            finalText.append(tokens[i]).append("/").append(tags[i]).append(" ");
        }
        System.out.println(finalText);
    }

}
