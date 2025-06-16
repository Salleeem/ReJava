package DopsD;

import java.util.Scanner;

public class Vowel {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Digite uma letra do alfabeto;");
        String vowel = sc.nextLine().toLowerCase();

        if (vowel.length() != 1 || !Character.isLetter(vowel.charAt(0))) {
            System.out.println("Erro. Digite apenas uma letra do alfabeto");
        } else {
            switch (vowel) {
            case "a", "e", "i", "o", "u":
                System.out.println(vowel + " é uma vogal");
                break;

            default:
                System.out.println(vowel + " é uma consoante");
                break;
        }

        }

        
        sc.close();
    }

}
