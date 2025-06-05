package ProjetosMedianos.NumerosPrimos;

import java.util.Scanner;

public class Primos {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int contador = 0;

        System.out.println("Digite um número para verificar se é primo:");
        int num = sc.nextInt();

        for (int i = 1; i <= num; i++) {
            if (num % i == 0) {
                contador++;
            }
        }

        if (contador == 2) {
            System.out.println("O número " + num + " é primo.");
        } else {
            System.out.println("O número " + num + " não é primo.");
        }

        sc.close();
    }
}
