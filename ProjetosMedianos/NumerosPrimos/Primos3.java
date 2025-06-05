package ProjetosMedianos.NumerosPrimos;

import java.util.Scanner;

public class Primos3 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Digite um número para verificar se é primo:");
        int num = sc.nextInt();
        boolean ehPrimo = true;

        if (num <= 1) {
            ehPrimo = false;
        } else {
            // Usando a biblioteca Math para pegar a raiz quadrada
            int limite = (int) Math.sqrt(num);
            for (int i = 2; i <= limite; i++) {
                if (num % i == 0) {
                    ehPrimo = false;
                    break;
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
