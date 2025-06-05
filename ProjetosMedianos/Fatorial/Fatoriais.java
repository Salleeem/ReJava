package ProjetosMedianos.Fatorial;  // Declaração do pacote onde o arquivo está organizado

import java.util.Scanner;  // Importa a classe Scanner para leitura de dados do usuário

public class Fatoriais {  // Declaração da classe pública chamada Fatoriais

    public static void main(String[] args) {  // Método principal, ponto de entrada do programa

        Scanner sc = new Scanner(System.in);  // Cria o objeto Scanner para ler dados do teclado
        int fatorial = 1;  // Inicializa a variável que vai armazenar o resultado do fatorial, começa em 1 pois é o elemento neutro da multiplicação

        System.out.println("Digite um número para ver seu fatorial");  // Solicita que o usuário digite um número
        int num = sc.nextInt();  // Lê o número digitado pelo usuário e armazena na variável num

        // Laço for que vai multiplicar todos os números de 1 até o número digitado
        for (int i = 1; i <= num; i++) {
           fatorial *= i;  // Multiplica o valor atual de fatorial pelo valor de i e armazena de volta em fatorial
        }

        // Mostra o resultado final para o usuário
        System.out.println("O fatorial de " + num + " é " + fatorial);

        sc.close();  // Fecha o Scanner para liberar recursos do sistema
    }
}
