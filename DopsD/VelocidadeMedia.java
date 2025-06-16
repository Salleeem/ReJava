package DopsD;

import java.util.Scanner;

public class VelocidadeMedia {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        System.out.println("Cálculo da velocidade média em m/s");
        
        System.out.println("Digite a distância em metros:");
        double distancia = sc.nextDouble();

        System.out.println("Digite o tempo, em segundos:");
        double tempo = sc.nextDouble();

        double veloMediaM = distancia / tempo;
        double veloMediaKm = veloMediaM * 3.6; 

        System.out.println("A velocidade média é de " + veloMediaM + " m/s. Ou " + veloMediaKm + " Km/h" );

        sc.close();
    }
}
