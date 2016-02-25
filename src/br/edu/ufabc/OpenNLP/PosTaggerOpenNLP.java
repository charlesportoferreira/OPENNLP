package br.edu.ufabc.OpenNLP;

import br.edu.ufabc.teste.Teste;
import br.edu.ufabc.util.Util;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
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
                }
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

    public StringBuilder getPosTaggedText(String[] tokens, String[] tags) {
        StringBuilder finalText = new StringBuilder();
        for (int i = 0; i < tokens.length; i++) {
            finalText.append(tokens[i]).append("/").append(tags[i]).append(" ");
        }
        return finalText;
    }

    public void geraTodosOsTags() {
        String tags[] = {"CC", "CD", "DT", "EX", "FW", "IN",
            "JJ", "JJR", "JJS", "LS", "MD", "NN", "NNS", "NNP", "NNPS", "PDT", "POS", "PRP", "PRP$", "RB",
            "RBR", "RBS", "RP", "SYM", "TO", "UH", "VB", "VBD", "VBG", "VBN", "VBP", "VBZ", "WDT", "WP",
            "WP$", "WRB"};

        for (String tag : tags) {
            geraPalavraComTagStopList(tag);
            geraPalavraComTag(tag);
        }
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

    public List<String> getTudoExcetoSubstantivos(String[] tokens, String[] tags) {
        List<String> tudoExcetoSubstantivos = new ArrayList<>();
        for (int i = 0; i < tags.length; i++) {
            if (!tags[i].contains("NN") && !tags[i].contains("NNS") && !tags[i].contains("NNP")
                    && !tags[i].contains("NNPS")) {
                tudoExcetoSubstantivos.add(tokens[i]);
            }
        }
        return tudoExcetoSubstantivos;
    }

    public List<String> getTudoExcetoSubstantivosAdjetivos(String[] tokens, String[] tags) {
        List<String> tudoExcetoAdjetivos = new ArrayList<>();
        for (int i = 0; i < tags.length; i++) {
            if (!tags[i].contains("JJ") && !tags[i].contains("JJR") && !tags[i].contains("JJS")
                    && !tags[i].contains("NN") && !tags[i].contains("NNS") && !tags[i].contains("NNP")
                    && !tags[i].contains("NNPS")) {
                tudoExcetoAdjetivos.add(tokens[i]);
            }
        }
        return tudoExcetoAdjetivos;
    }

    public List<String> getTudoExcetoSubstantivosAdjetivosVerbos(String[] tokens, String[] tags) {
        List<String> tudoExcetoSubstantivosAdjetivosVerbos = new ArrayList<>();
        for (int i = 0; i < tags.length; i++) {
            if (!tags[i].contains("JJ") && !tags[i].contains("JJR") && !tags[i].contains("JJS")
                    && !tags[i].contains("NN") && !tags[i].contains("NNS") && !tags[i].contains("NNP")
                    && !tags[i].contains("NNPS") && !tags[i].contains("VB") && !tags[i].contains("VBD")
                    && !tags[i].contains("VBG") && !tags[i].contains("VBN") && !tags[i].contains("VBP")
                    && !tags[i].contains("VBZ") && !tags[i].contains("MD")) {
                tudoExcetoSubstantivosAdjetivosVerbos.add(tokens[i]);
            }
        }
        return tudoExcetoSubstantivosAdjetivosVerbos;
    }

    public List<String> getTudoExcetoSubstantivosVerbos(String[] tokens, String[] tags) {
        List<String> tudoExcetoSubstantivosVerbos = new ArrayList<>();
        for (int i = 0; i < tags.length; i++) {
            if (!tags[i].contains("NN") && !tags[i].contains("NNS") && !tags[i].contains("NNP")
                    && !tags[i].contains("NNPS") && !tags[i].contains("VB") && !tags[i].contains("VBD")
                    && !tags[i].contains("VBG") && !tags[i].contains("VBN") && !tags[i].contains("VBP")
                    && !tags[i].contains("VBZ") && !tags[i].contains("MD")) {
                tudoExcetoSubstantivosVerbos.add(tokens[i]);
            }
        }
        return tudoExcetoSubstantivosVerbos;
    }

    public List<String> getAdjetivos(String[] tokens, String[] tags) {
        List<String> adjetivos = new ArrayList<>();
        for (int i = 0; i < tags.length; i++) {
            switch (tags[i]) {
                case "JJ":
                    adjetivos.add(tokens[i]);
                    break;
                case "JJR":
                    adjetivos.add(tokens[i]);
                    break;
                case "JJS":
                    adjetivos.add(tokens[i]);
                    break;
                default:
                    break;
            }
        }
        return adjetivos;
    }

    public List<String> getSubstantivos(String[] tokens, String[] tags) {
        List<String> substantivos = new ArrayList<>();
        for (int i = 0; i < tags.length; i++) {
            switch (tags[i]) {
                case "NN":
                    substantivos.add(tokens[i]);
                    break;
                case "NNS":
                    substantivos.add(tokens[i]);
                    break;
                case "NNPS":
                    substantivos.add(tokens[i]);
                    break;
                default:
                    break;
            }
        }
        return substantivos;
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

    public List<String> getOutros(String[] tokens, String[] tags) {
        List<String> outros = new ArrayList<>();
        for (int i = 0; i < tags.length; i++) {
            if (!tags[i].equals("VB") && !tags[i].equals("VBD") && !tags[i].equals("VBG")
                    && !tags[i].equals("VBN") && !tags[i].equals("VBP") && !tags[i].equals("VBZ")
                    && !tags[i].equals("MD") && !tags[i].equals("JJ") && !tags[i].equals("JJR")
                    && !tags[i].equals("JJS") && !tags[i].equals("NN") && !tags[i].equals("NNS")
                    && !tags[i].equals("NNPS") && !tags[i].equals("RB") && !tags[i].equals("RBS")
                    && !tags[i].equals("RBR")) {
                outros.add(tokens[i]);
            }
        }
        return outros;
    }

    public List<String> getPalavraComTag(String[] tokens, String[] tags, String tagEspecifico) {
        List<String> palavras = new ArrayList<>();
        for (int i = 0; i < tags.length; i++) {
            if (tags[i].equals(tagEspecifico)) {
                palavras.add(tokens[i]);
            }
        }
        return palavras;
    }

    public void countNumeroTokens() {
        String diretorio = System.getProperty("user.dir");
        List<String> textos = Util.lerNomeArquivos(new File(diretorio), 0);
        Set<String> atributos = new HashSet<>();
        List<String> atributosComRepeticao = new ArrayList<>();
        for (int i = 0; i < textos.size(); i++) {
            String textPath = textos.get(i);

            try {
                String[] tokens = tokenizer(Util.lerArquivo(textPath));
                atributos.addAll(Arrays.asList(tokens));
                atributosComRepeticao.addAll(Arrays.asList(tokens));
                System.out.print("\r" + "Analisado arquivo " + i + " de " + textos.size() + "   " + (i * 100) / textos.size() + "%");

            } catch (IOException ex) {
                Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        System.out.println("");
        System.out.println("Número de atributos sem repeticao: " + atributos.size());
        System.out.println("Número de atributos com repeticao: " + atributosComRepeticao.size());
    }

    public void geraPalavraComTagStopList(String tag) {
        String diretorio = System.getProperty("user.dir");
        List<String> textos = Util.lerNomeArquivos(new File(diretorio), 0);
        Set<String> palavraComTag = new HashSet<>();
        for (int i = 0; i < textos.size(); i++) {
            String textPath = textos.get(i);
            try {
                String[] tokens = tokenizer(Util.lerArquivo(textPath));
                String[] taggedText = posTagger(tokens);
                palavraComTag.addAll(getPalavraComTag(tokens, taggedText, tag));
                System.out.print("\r" + "Analisado arquivo " + i + " de " + textos.size() + "   " + (i * 100) / textos.size() + "%");

            } catch (IOException ex) {
                Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("");
        System.out.println("Número de palavras: " + palavraComTag.size());
        String stopList = Util.insertStopListTag(palavraComTag);
        try {
            Util.printFile(diretorio + "/" + tag + ".xml", stopList);
        } catch (IOException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void geraPalavraComTag(String tag) {
        String diretorio = System.getProperty("user.dir");
        List<String> textos = Util.lerNomeArquivos(new File(diretorio), 0);
        Set<String> palavraComTag = new HashSet<>();
        for (int i = 0; i < textos.size(); i++) {
            String textPath = textos.get(i);
            try {
                String[] tokens = tokenizer(Util.lerArquivo(textPath));
                String[] taggedText = posTagger(tokens);
                palavraComTag.addAll(getPalavraComTag(tokens, taggedText, tag));
                System.out.print("\r" + "Analisado arquivo " + i + " de " + textos.size() + "   " + (i * 100) / textos.size() + "%");

            } catch (IOException ex) {
                Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("");
        System.out.println("Número de palavras(" + tag + "): " + palavraComTag.size());
        StringBuilder stopList = new StringBuilder();//= Util.insertStopListTag(palavraComTag);
        for (String dado : palavraComTag) {
            dado = dado.replaceAll("[^a-zA-Z]", "");
            if (!dado.matches("")) {
                stopList.append(dado).append("\n");
            }
        }
        try {
            Util.printFile(diretorio + "/" + tag + ".text", stopList.toString());
        } catch (IOException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void geraVerboStopList() {
        String diretorio = System.getProperty("user.dir");
        List<String> textos = Util.lerNomeArquivos(new File(diretorio), 0);
        Set<String> verbos = new HashSet<>();
        for (int i = 0; i < textos.size(); i++) {
            String textPath = textos.get(i);
            try {
                String[] tokens = tokenizer(Util.lerArquivo(textPath));
                String[] taggedText = posTagger(tokens);
                verbos.addAll(getVerbos(tokens, taggedText));
                System.out.print("\r" + "Analisado arquivo " + i + " de " + textos.size() + "   " + (i * 100) / textos.size() + "%");

            } catch (IOException ex) {
                Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("");
        System.out.println("Número de verbos: " + verbos.size());
        String stopList = Util.insertStopListTag(verbos);
        try {
            Util.printFile(diretorio + "/verbos.xml", stopList);
        } catch (IOException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void geraOutrosStopList() {
        String diretorio = System.getProperty("user.dir");
        List<String> textos = Util.lerNomeArquivos(new File(diretorio), 0);
        Set<String> outros = new HashSet<>();
        for (int i = 0; i < textos.size(); i++) {
            String textPath = textos.get(i);
            try {
                String[] tokens = tokenizer(Util.lerArquivo(textPath));
                String[] taggedText = posTagger(tokens);
                outros.addAll(getOutros(tokens, taggedText));
                System.out.print("\r" + "Analisado arquivo " + i + " de " + textos.size() + "   " + (i * 100) / textos.size() + "%");

            } catch (IOException ex) {
                Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("");
        System.out.println("Número de outros: " + outros.size());
        String stopList = Util.insertStopListTag(outros);
        try {
            Util.printFile(diretorio + "/outros.xml", stopList);
        } catch (IOException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void geraTudoExcetoSubstantivosStopList() {
        String diretorio = System.getProperty("user.dir");
        List<String> textos = Util.lerNomeArquivos(new File(diretorio), 0);
        Set<String> tudoExcetoSubstantivos = new HashSet<>();
        for (int i = 0; i < textos.size(); i++) {
            String textPath = textos.get(i);
            try {
                String[] tokens = tokenizer(Util.lerArquivo(textPath));
                String[] taggedText = posTagger(tokens);
                tudoExcetoSubstantivos.addAll(getTudoExcetoSubstantivos(tokens, taggedText));
                System.out.print("\r" + "Analisado arquivo " + i + " de " + textos.size() + "   " + (i * 100) / textos.size() + "%");

            } catch (IOException ex) {
                Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("");
        System.out.println("Número de dados: " + tudoExcetoSubstantivos.size());
        String stopList = Util.insertStopListTag(tudoExcetoSubstantivos);
        try {
            Util.printFile(diretorio + "/excetoSubstantivos.xml", stopList);
        } catch (IOException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void geraTudoExcetoSubstantivosAdjetivosStopList() {
        String diretorio = System.getProperty("user.dir");
        List<String> textos = Util.lerNomeArquivos(new File(diretorio), 0);
        Set<String> tudoExcetoSubstantivosAdjetivos = new HashSet<>();
        for (int i = 0; i < textos.size(); i++) {
            String textPath = textos.get(i);
            try {
                String[] tokens = tokenizer(Util.lerArquivo(textPath));
                String[] taggedText = posTagger(tokens);
                tudoExcetoSubstantivosAdjetivos.addAll(getTudoExcetoSubstantivosAdjetivos(tokens, taggedText));
                System.out.print("\r" + "Analisado arquivo " + i + " de " + textos.size() + "   " + (i * 100) / textos.size() + "%");

            } catch (IOException ex) {
                Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("");
        System.out.println("Número de dados: " + tudoExcetoSubstantivosAdjetivos.size());
        String stopList = Util.insertStopListTag(tudoExcetoSubstantivosAdjetivos);
        try {
            Util.printFile(diretorio + "/excetoSubstantivosAdjetivos.xml", stopList);
        } catch (IOException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void geraTudoExcetoSubstantivosAdjetivosVerbosStopList() {
        String diretorio = System.getProperty("user.dir");
        List<String> textos = Util.lerNomeArquivos(new File(diretorio), 0);
        Set<String> tudoExcetoSubstantivosAdjetivosVerbos = new HashSet<>();
        for (int i = 0; i < textos.size(); i++) {
            String textPath = textos.get(i);
            try {
                String[] tokens = tokenizer(Util.lerArquivo(textPath));
                String[] taggedText = posTagger(tokens);
                tudoExcetoSubstantivosAdjetivosVerbos.addAll(getTudoExcetoSubstantivosAdjetivosVerbos(tokens, taggedText));
                System.out.print("\r" + "Analisado arquivo " + i + " de " + textos.size() + "   " + (i * 100) / textos.size() + "%");

            } catch (IOException ex) {
                Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("");
        System.out.println("Número de dados: " + tudoExcetoSubstantivosAdjetivosVerbos.size());
        String stopList = Util.insertStopListTag(tudoExcetoSubstantivosAdjetivosVerbos);
        try {
            Util.printFile(diretorio + "/excetoSubstantivosAdjetivosVerbos.xml", stopList);
        } catch (IOException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void geraTudoExcetoSubstantivosVerbosStopList() {
        String diretorio = System.getProperty("user.dir");
        List<String> textos = Util.lerNomeArquivos(new File(diretorio), 0);
        Set<String> tudoExcetoSubstantivosVerbos = new HashSet<>();
        for (int i = 0; i < textos.size(); i++) {
            String textPath = textos.get(i);
            try {
                String[] tokens = tokenizer(Util.lerArquivo(textPath));
                String[] taggedText = posTagger(tokens);
                tudoExcetoSubstantivosVerbos.addAll(getTudoExcetoSubstantivosVerbos(tokens, taggedText));
                System.out.print("\r" + "Analisado arquivo " + i + " de " + textos.size() + "   " + (i * 100) / textos.size() + "%");

            } catch (IOException ex) {
                Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("");
        System.out.println("Número de verbos: " + tudoExcetoSubstantivosVerbos.size());
        String stopList = Util.insertStopListTag(tudoExcetoSubstantivosVerbos);
        try {
            Util.printFile(diretorio + "/excetoSubstantivosVerbos.xml", stopList);
        } catch (IOException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void geraAdjetivoStopList() {

        String diretorio = System.getProperty("user.dir");
        //List<String> textos = Util.fileTreePrinter(new File(diretorio), 0);
        List<String> textos = Util.lerNomeArquivos(new File(diretorio), 0);
        Set<String> adjetivos = new HashSet<>();
        for (int i = 0; i < textos.size(); i++) {
            String textPath = textos.get(i);

            try {
                String[] tokens = tokenizer(Util.lerArquivo(textPath));
                String[] taggedText = posTagger(tokens);
                adjetivos.addAll(getAdjetivos(tokens, taggedText));
                System.out.print("\r" + "Analisado arquivo " + i + " de " + textos.size() + "   " + (i * 100) / textos.size() + "%");

            } catch (IOException ex) {
                Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        System.out.println("");
        System.out.println("Número de adjetivos: " + adjetivos.size());
        String stopList = Util.insertStopListTag(adjetivos);
        try {
            Util.printFile(diretorio + "/adjetivos.xml", stopList);
        } catch (IOException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void geraSubstantivoStopList() {

        String diretorio = System.getProperty("user.dir");
        //List<String> textos = Util.fileTreePrinter(new File(diretorio), 0);
        List<String> textos = Util.lerNomeArquivos(new File(diretorio), 0);
        Set<String> substantivos = new HashSet<>();
        for (int i = 0; i < textos.size(); i++) {
            String textPath = textos.get(i);

            try {
                String[] tokens = tokenizer(Util.lerArquivo(textPath));
                String[] taggedText = posTagger(tokens);
                substantivos.addAll(getSubstantivos(tokens, taggedText));
                System.out.print("\r" + "Analisado arquivo " + i + " de " + textos.size() + "   " + (i * 100) / textos.size() + "%");

            } catch (IOException ex) {
                Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        System.out.println("");
        System.out.println("Número de substantivos: " + substantivos.size());
        String stopList = Util.insertStopListTag(substantivos);
        try {
            Util.printFile(diretorio + "/substantivos.xml", stopList);
        } catch (IOException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void geraAdverbiosStopList() {

        String diretorio = System.getProperty("user.dir");
        //List<String> textos = Util.fileTreePrinter(new File(diretorio), 0);
        List<String> textos = Util.lerNomeArquivos(new File(diretorio), 0);
        Set<String> adverbios = new HashSet<>();
        for (int i = 0; i < textos.size(); i++) {
            String textPath = textos.get(i);

            try {
                String[] tokens = tokenizer(Util.lerArquivo(textPath));
                String[] taggedText = posTagger(tokens);
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

    public void geraStopList(List<String> dados, String classeGramatical) {
        String diretorio = System.getProperty("user.dir");
        //List<String> textos = Util.fileTreePrinter(new File(diretorio), 0);
        List<String> textos = Util.lerNomeArquivos(new File(diretorio), 0);
        Set<String> adverbios = new HashSet<>();
        for (int i = 0; i < textos.size(); i++) {
            String textPath = textos.get(i);

            try {
                String[] tokens = tokenizer(Util.lerArquivo(textPath));
                String[] taggedText = posTagger(tokens);
                adverbios.addAll(getAdverbios(tokens, taggedText));
                System.out.print("\r" + "Analisado arquivo " + i + " de " + textos.size() + "   " + (i * 100) / textos.size() + "%");

            } catch (IOException ex) {
                Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        System.out.println("");
        System.out.println("Número de" + classeGramatical + ": " + adverbios.size());
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
        StringBuilder posTaggedTokens = new StringBuilder();
        String diretorio = System.getProperty("user.dir");
        List<String> textos = Util.fileTreePrinter(new File(diretorio), 0);
        int j = 0;
        for (String textPath : textos) {
            String[] tokens;
            try {
                j++;
                tokens = tokenizer(Util.lerArquivo(textPath));
                String[] taggedText = posTagger(tokens);
                posTaggedTokens = getPosTaggedText(tokens, taggedText);
            } catch (IOException ex) {
                Logger.getLogger(PosTaggerOpenNLP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            Util.printFile(diretorio + "/TokenAndTags.xml", posTaggedTokens.toString());
        } catch (IOException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

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
                }
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
                }
            }
        }
    }

    public void criarListasMescladas(String[] listas) {
        String nomeArquivoFinal = "";
        List<String> li = new ArrayList<>();
        for (String lista : listas) {
            try {
                li.add(Util.lerArquivo(lista));
                nomeArquivoFinal += lista.replace(".xml", "");
            } catch (IOException ex) {
                Logger.getLogger(PosTaggerOpenNLP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        String listaMesclada = mesclaListas(li);
        try {
            Util.printFile(nomeArquivoFinal + ".xml", listaMesclada);
        } catch (IOException ex) {
            Logger.getLogger(PosTaggerOpenNLP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String mesclaListas(List<String> listas) {
        StringBuilder listaMesclada = new StringBuilder();
        listaMesclada.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<stopfile>\n");

        for (String lista : listas) {
            lista = lista.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<stopfile>\n", "");
            lista = lista.replace("</stopfile>\n", "");
            listaMesclada.append(lista);
        }

        listaMesclada.append("</stopfile>\n");
        return listaMesclada.toString();
    }

    public void printClasseDasPalavras() {
        StringBuilder sb = new StringBuilder();
        sb.append("1. CC Coordinating conjunction\n");
        sb.append("2. CD Cardinal number\n");
        sb.append("3. DT Determiner\n");
        sb.append("4. EX Existential there\n");
        sb.append("5. FW Foreign word\n");
        sb.append("6. IN Preposition or subordinating conjunction\n");
        sb.append("7. JJ Adjective\n");
        sb.append("8. JJR Adjective, comparative\n");
        sb.append("9. JJS Adjective, superlative\n");
        sb.append("10. LS List item marker\n");
        sb.append("11. MD Modal\n");
        sb.append("12. NN Noun, singular or mass\n");
        sb.append("13. NNS Noun, plural\n");
        sb.append("14. NNP Proper noun, singular\n");
        sb.append("15. NNPS Proper noun, plural\n");
        sb.append("16. PDT Predeterminer\n");
        sb.append("17. POS Possessive ending\n");
        sb.append("18. PRP Personal pronoun\n");
        sb.append("19. PRP$ Possessive pronoun\n");
        sb.append("20. RB Adverb\n");
        sb.append("21. RBR Adverb, comparative\n");
        sb.append("22. RBS Adverb, superlative\n");
        sb.append("23. RP Particle\n");
        sb.append("24. SYM Symbol\n");
        sb.append("25. TO to\n");
        sb.append("26. UH Interjection\n");
        sb.append("27. VB Verb, base form\n");
        sb.append("28. VBD Verb, past tense\n");
        sb.append("29. VBG Verb, gerund or present participle\n");
        sb.append("30. VBN Verb, past participle\n");
        sb.append("31. VBP Verb, non​3rd person singular present\n");
        sb.append("32. VBZ Verb, 3rd person singular present\n");
        sb.append("33. WDT Wh​determiner\n");
        sb.append("34. WP Wh​pronoun\n");
        sb.append("35. WP$ Possessive wh​pronoun\n");
        sb.append("36. WRB Wh​adverb\n");

        System.out.println(sb);
    }

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
