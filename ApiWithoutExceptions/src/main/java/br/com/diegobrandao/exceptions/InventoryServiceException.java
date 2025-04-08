package br.com.diegobrandao.exceptions;

public class InventoryServiceException extends Exception {
    public InventoryServiceException(String message) {
        super(message);
    }

    public InventoryServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
