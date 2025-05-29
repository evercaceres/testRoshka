package test.roshka.test_java.exception;

import org.springframework.http.HttpStatus;

public class NoticiaException extends RuntimeException {
    private final String codigo;
    private final HttpStatus status;

    public NoticiaException(String codigo, String mensaje, HttpStatus status) {
        super(mensaje);
        this.codigo = codigo;
        this.status = status;
    }

    public String getCodigo() {
        return codigo;
    }

    public HttpStatus getStatus() {
        return status;
    }
}