package app;

import java.util.List;
import items.*;

public class App { //Classe Principal
    public static void main(String[] args) {
        List<Item> array;

        List<String> db = BuilderList.getInstance().getList(); //Cria a estrutura de palavras

        int i = 0; // Número de Writers
        int j = 100; //Número de Readers
        
        System.out.println("--Solution Readers and Writers--");
        
        while (i <= 100 && j >= 0) { //Realiza o cálculo para todas as combinações
            
            long totalTime = 0;
            for (int n = 0; n < 50; n++) { //Intera 50 vezes
            	array = BuilderThreads.getInstance().build(i, j, db, SolutionRW.getInstance()); //Constroi um array de Threads
                
                long startTime = System.nanoTime();
                
                for (Item obj : array) {
                    ((Thread) obj).start(); //Inicia a Thread
                }
                
                for (Item obj : array) {
                    try {
                        ((Thread) obj).join(); //Aguarda todas terminarem para pegar o tempo final
                    } catch (InterruptedException e) {
                         e.printStackTrace();
                    }
                }
                
                long endTime = System.nanoTime();
                totalTime += (endTime - startTime);
            }
            
            long meanTime = totalTime / 50; //Média das interações com a solução Readers and Writers
            System.out.println("MeanTime = " + meanTime);
            
            i++;
            j--;
        }
        
        System.out.println("--Solution No Reader and Writers--");

        i = 0;
        j = 100;
        
        while (i <= 100 && j >= 0) { //Realiza o cálculo para todas as combinações
            
            long totalTime = 0;
            for (int n = 0; n < 50; n++) { //Intera 50 vezes
            	array = BuilderThreads.getInstance().build(i, j, db, SolutionNoRW.getInstance()); //Constroi um array de Threads
                
                long startTime = System.nanoTime();
                
                for (Item obj : array) {
                    ((Thread) obj).start(); //Inicia a Thread
                }
                
                for (Item obj : array) {
                    try {
                        ((Thread) obj).join(); //Aguarda todas terminarem para pegar o tempo final
                    } catch (InterruptedException e) {
                         e.printStackTrace();
                    }
                }
                
                long endTime = System.nanoTime();
                totalTime += (endTime - startTime);
            }
            
            long meanTime = totalTime / 50; //Média das interações com a solução Readers and Writers
            System.out.println("MeanTime = " + meanTime);
            
            i++;
            j--;
        }
    }
}
