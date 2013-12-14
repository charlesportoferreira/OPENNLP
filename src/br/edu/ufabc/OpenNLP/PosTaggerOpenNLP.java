/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufabc.OpenNLP;

import br.edu.ufabc.teste.Teste;
import br.edu.ufabc.util.Util;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class PosTaggerOpenNLP {

    InputStream modelInPosTaggger = null;
    InputStream modelInToken = null;
    POSTaggerME posTagger;
    Tokenizer tokenizer;

    public PosTaggerOpenNLP() {

        buildPostagger();
        buildTokenizer();
    }

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
        String[] tokens = tokenizer.tokenize(text);
        return tokens;
    }

    public String[] posTagger(String[] text) {

        String[] taggedText = posTagger.tag(text);
        return taggedText;
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
                case "MD":
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

    public void geraVerboStopList() {

        String diretorio = System.getProperty("user.dir");
        List<String> textos = Util.fileTreePrinter(new File(diretorio), 0);
        Set<String> verbos = new HashSet<>();
        for (int i = 0; i < textos.size(); i++) {
            String textPath = textos.get(i);

            try {
                //System.out.print("\r" + "Lendo o arquivo " + i + "de " + textos.size() + "   " + (i * 100) / textos.size() + "%");
                String[] tokens = tokenizer(Util.lerArquivo(textPath));
                //System.out.print("\r" + "Adicionando tags ao arquivo " + i + "de " + textos.size() + "   " + (i * 100) / textos.size() + "%");
                String[] taggedText = posTagger(tokens);
                // System.out.print("\r" + "extraindo verbos do arquivo " + i + "de " + textos.size() + "   " + (i * 100) / textos.size() + "%");
                verbos.addAll(getVerbos(tokens, taggedText));
                System.out.print("\r" + "Analisado arquivo " + i + " de " + textos.size() + "   " + (i * 100) / textos.size() + "%");

            } catch (IOException ex) {
                Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        System.out.println("");
        System.out.println("Número de verbos: " + verbos.size());
        String stopList = Util.insertStopListTag(verbos);
        //System.out.println(stopList);
        try {
            Util.printFile(diretorio + "/verbos.xml", stopList);
        } catch (IOException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void geraAdverbiosStopList() {

        String diretorio = System.getProperty("user.dir");
        List<String> textos = Util.fileTreePrinter(new File(diretorio), 0);
        Set<String> adverbios = new HashSet<>();
        for (int i = 0; i < textos.size(); i++) {
            String textPath = textos.get(i);

            try {
                //  System.out.print("\r" + "Lendo o arquivo " + i + "de " + textos.size() + "   " + (i * 100) / textos.size() + "%");
                String[] tokens = tokenizer(Util.lerArquivo(textPath));
                //  System.out.print("\r" + "Adicionando tags ao arquivo " + i + "de " + textos.size() + "   " + (i * 100) / textos.size() + "%");
                String[] taggedText = posTagger(tokens);
                //   System.out.print("\r" + "extraindo adverbos do arquivo " + i + "de " + textos.size() + "   " + (i * 100) / textos.size() + "%");
                adverbios.addAll(getAdverbios(tokens, taggedText));
                System.out.print("\r" + "Analisado arquivo " + i + " de " + textos.size() + "   " + (i * 100) / textos.size() + "%");

            } catch (IOException ex) {
                Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        System.out.println("");
        System.out.println("Número de adverbios: " + adverbios.size());
        String stopList = Util.insertStopListTag(adverbios);
        try {
            Util.printFile(diretorio + "/adverbios.xml", stopList);
        } catch (IOException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void printFilesPath() {
        String diretorio = System.getProperty("user.dir");
        List<String> textos = Util.fileTreePrinter(new File(diretorio), 0);
        for (String texto : textos) {
            if (texto.contains(".txt")) {
                System.out.println(texto);
            }
        }
    }

    public void printTokensAndTags() {
        List<String> verbos = new ArrayList();
        String diretorio = System.getProperty("user.dir");
        List<String> textos = Util.fileTreePrinter(new File(diretorio), 0);
        int j = 0;
        for (String textPath : textos) {
            String[] tokens;
            try {
                j++;
                tokens = tokenizer(Util.lerArquivo(textPath));
                String[] taggedText = posTagger(tokens);
                verbos.addAll(getVerbos(tokens, taggedText));
                //System.out.println("******************" + diretorio + "**********");
                System.out.print("\rAnalisado arquivo " + j + " de " + textos.size());
//                for (int i = 0; i < taggedText.length; i++) {
//                    System.out.println("token: " + tokens[i] + "     tag: " + taggedText[i]);
//                }
            } catch (IOException ex) {
                Logger.getLogger(PosTaggerOpenNLP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        StringBuilder sb = new StringBuilder();

        for (String v : verbos) {
            sb.append(v);
            sb.append("\n");
        }
        try {
            Util.printFile(diretorio + "/verbos.xml", sb.toString());
        } catch (IOException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.print("\r" + "Adicionando tags ao arquivo " + i + "de " + textos.size() + "   " + (i * 100) / textos.size() + "%");

    }

//1. CC Coordinating conjunction
//2. CD Cardinal number
//3. DT Determiner
//4. EX Existential there
//5. FW Foreign word
//6. IN Preposition or subordinating conjunction
//7. JJ Adjective
//8. JJR Adjective, comparative
//9. JJS Adjective, superlative 
//10. LS List item marker
//11. MD Modal
//12. NN Noun, singular or mass 
//13. NNS Noun, plural
//14. NNP Proper noun, singular 
//15. NNPS Proper noun, plural 
//16. PDT Predeterminer
//17. POS Possessive ending
//18. PRP Personal pronoun
//19. PRP$ Possessive pronoun
//20. RB Adverb
//21. RBR Adverb, comparative
//22. RBS Adverb, superlative 
//23. RP Particle
//24. SYM Symbol
//25. TO to
//26. UH Interjection
//27. VB Verb, base form
//28. VBD Verb, past tense
//29. VBG Verb, gerund or present participle 
//30. VBN Verb, past participle
//31. VBP Verb, non​3rd person singular present 
//32. VBZ Verb, 3rd person singular present
//33. WDT Wh​determiner
//34. WP Wh​pronoun
//35. WP$ Possessive wh​pronoun 
//36. WRB Wh​adverb
    private void buildPostagger() {
        // Loading tokenizer model
        modelInPosTaggger = getClass().getResourceAsStream("/resources/en-pos-maxent.bin");
        final POSModel posModel;
        try {
            posModel = new POSModel(modelInPosTaggger);
            modelInPosTaggger.close();
//            POSTaggerME posTagger;
            posTagger = new POSTaggerME(posModel);
        } catch (final IOException ioe) {
            //ioe.printStackTrace();
            System.out.println("Erro ao tentar executar o postagger");
        } finally {
            if (modelInPosTaggger != null) {
                try {
                    modelInPosTaggger.close();
                } catch (final IOException e) {
                } // oh well!
            }
        }

    }

    private void buildTokenizer() {
        try {

            // Loading tokenizer model
            modelInToken = getClass().getResourceAsStream("/resources/en-token.bin");
            final TokenizerModel tokenModel = new TokenizerModel(modelInToken);
            modelInToken.close();

            tokenizer = new TokenizerME(tokenModel);

        } catch (final IOException ioe) {
            //ioe.printStackTrace();
            System.out.println("Erro ao tentar executar o tokenizador");

        } finally {
            if (modelInToken != null) {
                try {
                    modelInToken.close();
                } catch (final IOException e) {
                } // oh well!
            }
        }
    }
}
