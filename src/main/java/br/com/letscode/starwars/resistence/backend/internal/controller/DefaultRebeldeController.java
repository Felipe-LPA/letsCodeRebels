package br.com.letscode.starwars.resistence.backend.internal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.letscode.starwars.resistence.backend.api.controller.RebeldeController;
import br.com.letscode.starwars.resistence.core.api.data.Rebelde;
import br.com.letscode.starwars.resistence.core.api.usecases.CreateRebelde;
import br.com.letscode.starwars.resistence.core.api.usecases.RebeldeTraidor;
import br.com.letscode.starwars.resistence.core.api.usecases.RetrieveRebeldes;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@ConditionalOnSingleCandidate(RebeldeController.class)
public class DefaultRebeldeController implements RebeldeController {

    private final RetrieveRebeldes retrieveRebeldes;
    private final CreateRebelde criarRebelde;
    private final RebeldeTraidor rebeldeTraidor;
    
    @Override
    public List<Rebelde> retrieveAll(Boolean traidores) {
        return retrieveRebeldes.execute(traidores);
    }

    @Override
    public Rebelde retrieveById(Long id) {
        return retrieveRebeldes.execute(id);
    }

    @Override
    public Rebelde createRebelde(@Valid Rebelde rebelde) {
        return criarRebelde.execute(rebelde);
    }

    @Override
    public boolean denunciar(Long id) {
        return rebeldeTraidor.denunciar(id);
    }

    @Override
    public boolean isTraidor(Long id) {
        return rebeldeTraidor.status(id);
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