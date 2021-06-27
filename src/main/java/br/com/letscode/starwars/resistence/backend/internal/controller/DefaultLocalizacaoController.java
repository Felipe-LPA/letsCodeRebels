package br.com.letscode.starwars.resistence.backend.internal.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.letscode.starwars.resistence.backend.api.controller.LocalizacaoController;
import br.com.letscode.starwars.resistence.core.api.data.Localizacao;
import br.com.letscode.starwars.resistence.core.api.usecases.CreateRebelde;
import br.com.letscode.starwars.resistence.core.api.usecases.RetrieveLocalizacoes;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@ConditionalOnSingleCandidate(LocalizacaoController.class)
public class DefaultLocalizacaoController implements LocalizacaoController {

    private final RetrieveLocalizacoes retrieveLocalizacoes;
    private final CreateRebelde createRebelde;

    @Override
    public Localizacao retrieveByRebeldeId(Long id) {
        return retrieveLocalizacoes.executeByRebeldeId(id);
    }

    @Override
    public Localizacao atualizaLocalizacaoRebelde(Long id, Localizacao localizacao) {
        return createRebelde.execute(localizacao, id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
