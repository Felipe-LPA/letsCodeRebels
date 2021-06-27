package br.com.letscode.starwars.resistence.backend.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.letscode.starwars.resistence.config.ResistenceConstants;
import br.com.letscode.starwars.resistence.core.api.data.Rebelde;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(ResistenceConstants.API_PREFIX + "/rebeldes")
@Api(value = "Rebeldes", description = "API REST para rebeldes", tags = { "Rebeldes" })
public interface RebeldeController {

    @ApiOperation(value = "Busca todos os rebeldes cadastrados")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista de rebeldes cadastrados"),
            @ApiResponse(code = 404, message = "Nenhum rebelde encontrado"),
            @ApiResponse(code = 500, message = "Ops, acho que estamos sendo atacados."), })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<Rebelde> retrieveAll(@RequestParam(required = false, name = "traidores") Boolean traidores);

    @ApiOperation(value = "Busca um rebelde pelo ID")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna o rebelde que está sendo procurado"),
            @ApiResponse(code = 404, message = "Nenhum rebelde encontrado"),
            @ApiResponse(code = 500, message = "Ops, acho que estamos sendo atacados."), })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Rebelde retrieveById(@PathVariable("id") Long id);

    @ApiOperation(value = "Adiciona um novo rebelde para a rebelião!")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Rebelde que foi adicionado"),
            @ApiResponse(code = 500, message = "Ops, acho que estamos sendo atacados."), })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Rebelde createRebelde(@Valid @RequestBody Rebelde rebelde);

    @ApiOperation(value = "Denuncia um rebelde pelo ID")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna se o rebelde é um traidor"),
            @ApiResponse(code = 404, message = "Nenhum rebelde encontrado"),
            @ApiResponse(code = 500, message = "Ops, acho que estamos sendo atacados."), })
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean denunciar(@PathVariable("id") Long id);

    @ApiOperation(value = "O rebelde é um traidor?")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna se o rebelde é um traidor"),
            @ApiResponse(code = 404, message = "Nenhum rebelde encontrado"),
            @ApiResponse(code = 500, message = "Ops, acho que estamos sendo atacados."), })
    @GetMapping(value = "/traidor/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean isTraidor(@PathVariable("id") Long id);

}