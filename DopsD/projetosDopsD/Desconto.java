package DopsD.projetosDopsD;

import java.util.Scanner;

public class Desconto {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        double desconto;
        double precodescontado;

        System.out.println("Digite o valor do produto:");
        double preco = sc.nextDouble();

        if (preco < 100) {
            System.out.println("Seu produto não tem direito a desconto, o valor permanece: " + preco);
            
        } else if (preco >= 100 && preco < 200) {
            desconto = preco * 0.1;
            precodescontado = preco - desconto;
            System.out.println("O preço do seu produto recebeu desconto e agora é: " + precodescontado + 
                               " (Recebeu " + desconto + " de desconto)");
                               
        } else {
            // Exemplo para produtos acima de 200
            desconto = preco * 0.2;
            precodescontado = preco - desconto;
            System.out.println("O preço do seu produto recebeu desconto e agora é: " + precodescontado + 
                               " (Recebeu " + desconto + " de desconto)");
        }

        sc.close();
    }
}
