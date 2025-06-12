package CalculadoraFinanceira;

import java.util.Scanner;

public class Money {

    // Método para calcular o Imposto de Renda
    public static double calcularIR(double rendimentoBruto, long dias) {
        double aliquota;
        if (dias <= 180) {
            aliquota = 0.225; 
        } else if (dias <= 360) { 
            aliquota = 0.20; 
        } else if (dias <= 720) { 
            aliquota = 0.175; 
        } else { 
            aliquota = 0.15; 
        }
        return rendimentoBruto * aliquota;
    }

    // Método para calcular o IOF
    public static double calcularIOF(double rendimentoBruto, long dias) {
        if (dias >= 30) {
            return 0; // IOF é zero após 30 dias
        } else {
            double[] iofTabela = {
                0.96, 0.93, 0.90, 0.86, 0.83, 0.80, 0.76, 0.73, 0.70, 0.66, // 1 a 10 dias
                0.63, 0.60, 0.56, 0.53, 0.50, 0.46, 0.43, 0.40, 0.36, 0.33, // 11 a 20 dias
                0.30, 0.26, 0.23, 0.20, 0.16, 0.13, 0.10, 0.06, 0.03, 0.00  // 21 a 30 dias
            };
            // O índice é (dias - 1) porque a tabela é de 0 a 29 para 1 a 30 dias
            double fator = iofTabela[(int) dias - 1]; 
            return rendimentoBruto * fator;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Calculadora de Rendimento Financeiro ---");

        // 1. Entrada do Aporte Inicial
        System.out.print("Digite o valor do aporte inicial (R$): ");
        double aporteInicial = sc.nextDouble();

        // 2. Entrada da Porcentagem do CDI
        System.out.print("Digite a porcentagem do CDI (ex: 100 para 100% do CDI, 90 para 90%): ");
        double porcentagemCDI = sc.nextDouble();

        // 3. Entrada do Tempo em Dias
        System.out.print("Digite o tempo em dias para o investimento: ");
        long diasInvestidos = sc.nextLong();

        // Para simular o CDI, vamos usar uma taxa SELIC atual como base, já que o CDI acompanha a SELIC.
        // É importante lembrar que o CDI varia diariamente. Para uma calculadora simples,
        // podemos usar uma estimativa anual e converter para diária.
        // Taxa CDI anual estimada (aproximadamente a SELIC atual)
        // Usaremos um valor fixo para o exemplo, mas em uma aplicação real, você buscaria a taxa CDI atual.
        double cdiAnual = 0.1475; // Exemplo: 10.40% ao ano (SELIC atual em junho/2025)

        // Ajusta a taxa CDI pela porcentagem informada pelo usuário
        double cdiAnualAjustado = cdiAnual * (porcentagemCDI / 100.0);

        // Convertendo a taxa anual para diária para o cálculo composto (aproximação)
        // (1 + taxa_anual)^(1/365) - 1
        double cdiDiario = Math.pow((1 + cdiAnualAjustado), (1.0 / 365.0)) - 1;

        // Cálculo do rendimento bruto
        // Montante final = Aporte Inicial * (1 + CDI_Diario)^diasInvestidos
        double montanteFinalBruto = aporteInicial * Math.pow((1 + cdiDiario), diasInvestidos);
        double rendimentoBruto = montanteFinalBruto - aporteInicial;

        // Cálculo do IOF
        double valorIOF = calcularIOF(rendimentoBruto, diasInvestidos);

        // Rendimento após IOF
        double rendimentoAposIOF = rendimentoBruto - valorIOF;

        // Cálculo do IR sobre o rendimento após IOF
        double valorIR = calcularIR(rendimentoAposIOF, diasInvestidos);

        // Rendimento líquido
        double rendimentoLiquido = rendimentoAposIOF - valorIR;
        double montanteFinalLiquido = aporteInicial + rendimentoLiquido;

        System.out.println("\n--- Resultado do Investimento ---");
        System.out.printf("Aporte Inicial: R$ %.2f%n", aporteInicial);
        System.out.printf("Rendimento Bruto: R$ %.2f%n", rendimentoBruto);
        System.out.printf("IOF Retido: R$ %.2f%n", valorIOF);
        System.out.printf("IR Retido: R$ %.2f%n", valorIR);
        System.out.printf("Rendimento Líquido: R$ %.2f%n", rendimentoLiquido);
        System.out.printf("Montante Final Líquido: R$ %.2f%n", montanteFinalLiquido);

        sc.close();
    }
}