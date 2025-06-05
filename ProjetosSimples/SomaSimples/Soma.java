package ProjetosSimples.SomaSimples;

import java.util.Scanner;

public class Soma {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o primeiro número da soma");
        int a = sc.nextInt();

        System.out.println("Digite o segundo número da soma");
        int b = sc.nextInt();

        int resultado = a + b;
        System.out.println("O resultado da soma é " + resultado);

        sc.close();
    }

}
