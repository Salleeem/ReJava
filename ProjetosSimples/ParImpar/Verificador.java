package ProjetosSimples.ParImpar;

import java.util.Scanner;

public class Verificador {
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Digite um número para verificar se é par ou impar");
        int num = sc.nextInt();

        if (num % 2 == 0) {
            System.out.println("O número é par");
        } else {
            System.out.println("O número é impar");
        }

        sc.close();
    }
}
