package ProjetosSimples.Tabuada;

import java.util.Scanner;

//Exemplo com menos repitação e mais compacto.
public class Tabuadinhas2 {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite um número para ver sua tabuada:");
        int num = sc.nextInt();

        System.out.println("Tabuada do " + num + ":");

        for (int i = 1; i <= 10; i++) {
            System.out.println(num + " x " + i + " = " + (num * i));
        }

        sc.close();
    }
}
