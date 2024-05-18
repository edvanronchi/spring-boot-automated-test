package dev.edvanronchi.springbootautomatedtest.domain.exceptions;

public class DivisaoPorZeroException extends RuntimeException {

    public DivisaoPorZeroException(String message) {
        super(message);
    }
}
