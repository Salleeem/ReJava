package ProjetosSimples.Tabuada;

import java.util.Scanner;

public class Tabuadinhas {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite um número para ver sua tabuada");
        int num = sc.nextInt();

        System.out.println("Tabuada do " + num + "\n"
        + num * 1 + "\n"
        + num * 2 + "\n" 
        + num * 3 + "\n"
        + num * 4 + "\n" 
        + num * 5 + "\n"
        + num * 6 + "\n"
        + num * 7 + "\n"
        + num * 8 + "\n"
        + num * 9 + "\n"
        + num * 10 + "\n");

        sc.close();
    }
}
