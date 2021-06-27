package br.com.letscode.starwars.resistence.backend.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.letscode.starwars.resistence.config.ResistenceConstants;
import br.com.letscode.starwars.resistence.core.api.data.Troca;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(ResistenceConstants.API_PREFIX + "/trocas")
@Api(value = "TROCA", description = "API REST para trocas de itens", tags = { "TROCA" })
public interface TrocaController {

    @ApiOperation(value = "Busca todos os trocas disponíveis")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista de trocas disponíveis"),
            @ApiResponse(code = 404, message = "Nenhuma troca encontrada"),
            @ApiResponse(code = 500, message = "Ops, acho que estamos sendo atacados."), })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<Troca> retrieveAll();

    @ApiOperation(value = "Busca uma troca pelo ID")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a troca procurada"),
            @ApiResponse(code = 404, message = "Nenhuma troca encontrada"),
            @ApiResponse(code = 500, message = "Ops, acho que estamos sendo atacados."), })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Troca retrieveById(@PathVariable("id") Long id);

    @ApiOperation(value = "Cria uma nova troca")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a troca procurada"),
            @ApiResponse(code = 404, message = "Nenhuma troca encontrada"),
            @ApiResponse(code = 500, message = "Ops, acho que estamos sendo atacados."), })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    Troca createTroca(@Valid @RequestBody Troca troca);

    @ApiOperation(value = "Efetua uma troca")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a troca procurada"),
            @ApiResponse(code = 404, message = "Nenhuma troca encontrada"),
            @ApiResponse(code = 500, message = "Ops, acho que estamos sendo atacados."), })
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Troca doTroca(@PathVariable("id") Long id, @RequestParam(name = "rebeldeId", required = true) Long rebeldeId);

    @ApiOperation(value = "Deleta uma troca")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a troca procurada"),
            @ApiResponse(code = 404, message = "Nenhuma troca encontrada"),
            @ApiResponse(code = 500, message = "Ops, acho que estamos sendo atacados."), })
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteTroca(@PathVariable("id") Long id);

}
