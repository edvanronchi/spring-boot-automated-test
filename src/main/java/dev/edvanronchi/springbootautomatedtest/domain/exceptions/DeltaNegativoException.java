package dev.edvanronchi.springbootautomatedtest.domain.exceptions;

public class DeltaNegativoException extends RuntimeException {

    public DeltaNegativoException(String message) {
        super(message);
    }
}
