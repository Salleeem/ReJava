package DopsD.projetosDopsD;

import java.util.Scanner;

public class Apples {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double totalValue;

        System.out.println("Digite a quantidade de maças desejada: ");
        int quantity = sc.nextInt();

        if (quantity <= 11) {
            double value = 0.30;

            totalValue = quantity * value;
            System.out.println("O valor a ser pago por " + quantity + " é de R$ " + String.format("%.2f", totalValue));

        } if (quantity >= 12  ) {
            double value = 0.25;
            
            totalValue = quantity * value;
            System.out.println("O valor a ser pago por " + quantity + " é de R$ " + String.format("%.2f", totalValue));
        }
        sc.close();
    }
}
