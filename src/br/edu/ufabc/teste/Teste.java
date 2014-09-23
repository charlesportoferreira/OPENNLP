package br.edu.ufabc.teste;

import br.edu.ufabc.OpenNLP.PosTaggerOpenNLP;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Teste {

    public static void main(String[] args) {

        PosTaggerOpenNLP pto = new PosTaggerOpenNLP();
        List<String> argumentos = new ArrayList<>(Arrays.asList(args));
        if (argumentos.isEmpty()) {
            help();
        }
        Iterator itr = argumentos.iterator();
        while (itr.hasNext()) {
            String argumento = (String) itr.next();
            if (args.length != 0) {
                switch (argumento) {
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
                    case "-adj":
                        pto.geraAdjetivoStopList();
                        break;
                    case "-sub":
                        pto.geraSubstantivoStopList();
                        break;
                    case "-ns":
                        pto.geraTudoExcetoSubstantivosStopList();
                        break;
                    case "-nsv":
                        pto.geraTudoExcetoSubstantivosVerbosStopList();
                        break;
                    case "-nsa":
                        pto.geraTudoExcetoSubstantivosAdjetivosStopList();
                        break;
                    case "-nsav":
                        pto.geraTudoExcetoSubstantivosAdjetivosVerbosStopList();
                        break;
                    case "-m":
                        String[] listas = ((String) itr.next()).split(",");
                        pto.criarListasMescladas(listas);
                        break;
                    case "-c":
                        pto.countNumeroTokens();
                        break;
                    default:
                        System.out.println("Parâmetro digitado incorretamente");
                        System.out.println("Parametro: " + argumento);
                        help();
                        break;
                }
            } else {

                help();
            }
        }
    }

    public static void help() {
        System.out.println("Faltou passar algum argumento:");
        System.out.println("-p = imprime arquivos txt da pasta de origem");
        System.out.println("-v = imprime os verbos dos arquivos");
        System.out.println("-ad = imprime os adverbios");
        System.out.println("-adj = imprime os adjetivos");
        System.out.println("-sub = imprime os substantivos");
        System.out.println("-ptt = imprime os tokens com seus respectivos tags");
        System.out.println("-ns = imprime todos menos os substantivos");
        System.out.println("-nsv = imprime todos menos os substantivos e os verbos");
        System.out.println("-nsa = imprime todos menos os substantivos e os adjetivos");
        System.out.println("-nsav = imprime todos menos os substantivos, adjetivos e os verbos");
        System.out.println("-m = mescla varias stoplist para formar uma unica. "
                + "Os arquivos devem ser separados por virgula, sem espacos");
    }

}
