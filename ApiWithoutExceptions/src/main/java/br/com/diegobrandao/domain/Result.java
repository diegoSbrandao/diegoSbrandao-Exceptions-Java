package br.com.diegobrandao.domain;

public class Result<T> {
    private final boolean success;
    private final T value;
    private final String errorMessage;

    private Result(boolean success, T value, String errorMessage) {
        this.success = success;
        this.value = value;
        this.errorMessage = errorMessage;
    }

    public static <T> Result<T> success(T value) {
        return new Result<>(true, value, null);
    }

    public static <T> Result<T> error(String message) {
        return new Result<>(false, null, message);
    }

    public boolean isSuccess() { return success; }
    public T getValue() { return value; }
    public String getErrorMessage() { return errorMessage; }
}
