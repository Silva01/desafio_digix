package br.net.digix.desafio.silva.daniel.domain.family.exception;

public class FamilyConjugeNotValidException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String message;

    public FamilyConjugeNotValidException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
