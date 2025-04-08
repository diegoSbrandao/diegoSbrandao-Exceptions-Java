package org.example;

public class ExceptionOverheadLoopBenchmark {
    public static void main(String[] args) throws Exception {
        final int N = 10_000_000;

        // Teste 0: execução pura
        long start = System.nanoTime();
        for (int i = 0; i < N ; i++) {
            int x = i * 2;
        }
        long end = System.nanoTime();
        System.out.println("execução pura: " + (end - start) / 1_000_000.0 + " ms");

        // Teste 1: if-else simples
        start = System.nanoTime();
        for (int i = 0; i < N ; i++) {
            if (i % 2 == 0) {
                int x = i * 2;
            }
        }
        end = System.nanoTime();
        System.out.println("if: " + (end - start) / 1_000_000.0 + " ms");

        // Teste 2: try-catch sem exceção
        start = System.nanoTime();
        for (int i = 0; i < N; i++) {
            try {
                int x = i * 2;
            } catch (Exception e) {
            }
        }
        end = System.nanoTime();
        System.out.println("try-catch (sem erro): " + (end - start) / 1_000_000.0 + " ms");

        // Teste 3: try-catch com exceção
        start = System.nanoTime();
        for (int i = 0; i < N / 100; i++) {
            try {
                throw new Exception("erro");
            } catch (Exception e) {
            }
        }
        end = System.nanoTime();
        System.out.println("try-catch (com erro): " + (end - start) / 1_000_000.0 + " ms");
    }
}