package ProjetosMedianos.NumerosPrimos;

import java.util.Scanner;

public class Primos2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Digite um número para verificar se é primo:");
        int num = sc.nextInt();
        boolean ehPrimo = true;

        if (num <= 1) {
            ehPrimo = false;
        } else {
            for (int i = 2; i * i <= num; i++) {
                if (num % i == 0) {
                    ehPrimo = false;
                    break; // já sabemos que não é primo, pode parar
                }
            }
        }

        if (ehPrimo) {
            System.out.println("O número " + num + " é primo.");
        } else {
            System.out.println("O número " + num + " não é primo.");
        }

        sc.close();
    }
}
