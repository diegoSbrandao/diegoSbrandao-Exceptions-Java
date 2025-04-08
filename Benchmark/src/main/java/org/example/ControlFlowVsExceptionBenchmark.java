package org.example;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ControlFlowVsExceptionBenchmark {

    private static final Logger log = LoggerFactory.getLogger(ControlFlowVsExceptionBenchmark.class);

    public static void main(String[] args) {
        long start, end;

        // Controle de fluxo com if
        start = System.nanoTime();
        processWithIf(3); // valor inválido
        end = System.nanoTime();
        System.out.println("Com if: " + (end - start) + " ns");

        // Controle de fluxo com exceção
        start = System.nanoTime();
        try {
            processWithException(3); // valor inválido
        } catch (IllegalArgumentException e) {
            // ignorado
        }
        end = System.nanoTime();
        System.out.println("Com exceção: " + (end - start) + " ns");
    }

    public static int processWithIf(int value) {
        if (value < 5) {
           return 0;
        }
        return value;
    }

    public static void processWithException(int value) {
        if (value < 5) {
            throw new IllegalArgumentException("valor inválido");
        }
    }
}
