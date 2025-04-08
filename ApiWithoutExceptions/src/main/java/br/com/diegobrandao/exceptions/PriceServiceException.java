package br.com.diegobrandao.exceptions;

public class PriceServiceException extends Exception {
    public PriceServiceException(String message) {
        super(message);
    }

    public PriceServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
