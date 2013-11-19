/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufabc.OpenNLP;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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

            //System.out.println(taggedText);
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

    public List<String> getVerbos(String[] tokens, String[] tags) {
        List<String> verbos = new ArrayList<>();
        for (int i = 0; i < tags.length; i++) {
            switch (tags[i]) {
                case "VB":
                    verbos.add(tokens[i]);
                    break;
                case "VBD":
                    verbos.add(tokens[i]);
                    break;
                case "VBG":
                    verbos.add(tokens[i]);
                    break;
                case "VBN":
                    verbos.add(tokens[i]);
                    break;
                case "VBP":
                    verbos.add(tokens[i]);
                    break;
                case "VBZ":
                    verbos.add(tokens[i]);
                    break;
                default:
                    break;
            }
        }
        return verbos;
    }

    public List<String> getAdverbios(String[] tokens, String[] tags) {
        List<String> adverbios = new ArrayList<>();
        for (int i = 0; i < tags.length; i++) {
            switch (tags[i]) {
                case "RB":
                    adverbios.add(tokens[i]);
                    break;
                case "RBS":
                    adverbios.add(tokens[i]);
                    break;
                case "RBR":
                    adverbios.add(tokens[i]);
                    break;
                default:
                    break;
            }
        }
        return adverbios;
    }

//1. CC Coordinating conjunction 2. CD Cardinal number
//3. DT Determiner
//4. EX Existential there
//5. FW Foreign word
//6. IN Preposition or subordinating conjunction
//7. JJ Adjective
//8. JJR Adjective, comparative
//9. JJS Adjective, superlative 10. LS List item marker
//11. MD Modal
//12. NN Noun, singular or mass 13. NNS Noun, plural
//14. NNP Proper noun, singular 15. NNPS Proper noun, plural 16. PDT Predeterminer
//17. POS Possessive ending
//18. PRP Personal pronoun
//19. PRP$ Possessive pronoun
//20. RB Adverb
//21. RBR Adverb, comparative
//22. RBS Adverb, superlative 23. RP Particle
//24. SYM Symbol
//25. TO to
//26. UH Interjection
//27. VB Verb, base form
//28. VBD Verb, past tense
//29. VBG Verb, gerund or present participle 30. VBN Verb, past participle
//30. VBN Verb, past participle
//31. VBP Verb, non​3rd person singular present 32. VBZ Verb, 3rd person singular present
//32. VBZ Verb, 3rd person singular present
//33. WDT Wh​determiner
//34. WP Wh​pronoun
//35. WP$ Possessive wh​pronoun 36. WRB Wh​adverb
}
