package Cours;

import java.util.Scanner;

public class ExceptionHandling {
    static int sumArray(int[] tab){
        int somme=0;
        for(int i=0;i<=tab.length;i++){
            somme =somme+tab[i];
        }
        return somme;
    }
    public static void main(String[] args) {
        Scanner clavier=new Scanner(System.in);
        System.out.println("Amount of numbers : ");
        int amount=clavier.nextInt();
        int[] nombres=new int[amount];
        for(int i=0;i<nombres.length;i++){
            System.out.println("Entrez un nombre : ");
            nombres[i]=clavier.nextInt();
        }
        for(int i=0;i<nombres.length;i++){
            System.out.print(nombres[i]);
        }
        try{
            int sommeArrayNumbers=sumArray(nombres);
        }catch (ArrayIndexOutOfBoundsException trouble){
            System.out.println(trouble.getMessage());
        }finally {
            System.out.println("Maman j'ai râté l'avion");
        }
    }
}