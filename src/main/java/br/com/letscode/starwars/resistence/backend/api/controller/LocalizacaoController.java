package br.com.letscode.starwars.resistence.backend.api.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.letscode.starwars.resistence.config.ResistenceConstants;
import br.com.letscode.starwars.resistence.core.api.data.Localizacao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(ResistenceConstants.API_PREFIX + "/localizacoes")
@Api(value = "Localizacao", description = "API REST para localizacoes", tags = { "Localizacao" })
public interface LocalizacaoController {

    @ApiOperation(value = "Busca um a localizacao de um rebelde pelo ID do rebelde")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a localizacao do rebelde que está sendo procurado"),
            @ApiResponse(code = 404, message = "Nenhum localizacao encontrada. Rebelde não existe ou sumiu!"),
            @ApiResponse(code = 500, message = "Ops, acho que estamos sendo atacados."), })
    @GetMapping(value = "/rebelde/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Localizacao retrieveByRebeldeId(@PathVariable("id") Long id);

    @ApiOperation(value = "Atualiza a localizacao de um rebelde pelo ID do rebelde")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a localizacao do rebelde atualizada"),
            @ApiResponse(code = 404, message = "Nenhum localizacao encontrada. Rebelde não existe ou sumiu!"),
            @ApiResponse(code = 500, message = "Ops, acho que estamos sendo atacados."), })
    @PutMapping(value = "/rebelde/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Localizacao atualizaLocalizacaoRebelde(@PathVariable("id") Long id, @RequestBody Localizacao localizacao);
}