package br.net.digix.desafio.silva.daniel.infraestrutura.exception.handler;


import br.net.digix.desafio.silva.daniel.domain.family.exception.FamilyConjugeNotValidException;
import br.net.digix.desafio.silva.daniel.infraestrutura.dto.ErrorResponseDTO;
import br.net.digix.desafio.silva.daniel.domain.family.exception.CpfJaCadastradoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(value = {CpfJaCadastradoException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO cpfNaoCadastradoHandler() {
        return new ErrorResponseDTO("Familia Já Cadastrada");
    }

    @ExceptionHandler(value = {FamilyConjugeNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO conjugeFamilyHandler(FamilyConjugeNotValidException ex) {
        return new ErrorResponseDTO(ex.getMessage());
    }
}
