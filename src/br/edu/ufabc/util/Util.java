/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufabc.util;

import java.io.File;

/**
 *
 * @author charleshenriqueportoferreira
 */
public class Util {

    public static void lerNomeArquivos() {
        // Use assim (exemplo): 

        String dir = "/Users/charleshenriqueportoferreira/Documentos/";

        File diretorio = new File("Users/charlehenriqueportoferreira");
        File fList[] = diretorio.listFiles();

        System.out.println("Numero de arquivos no diretorio : " + fList.length);
        for (File fList1 : fList) {
            System.out.println(fList.toString());
        }
    }

    public static void fileTreePrinter(File initialPath, int initialDepth) {
        int depth = initialDepth++;
        if (initialPath.exists()) {
            File[] contents = initialPath.listFiles();
            for (File content : contents) {
                if (content.isDirectory()) {
                    fileTreePrinter(content, initialDepth + 1);
                } else {
                    char[] dpt = new char[initialDepth];
                    for (int j = 0; j < initialDepth; j++) {
                        dpt[j] = '+';
                    }
                    System.out.println(new String(dpt) + content.getName() + " " + content.getPath());
                }
            }
        }
    }

}
