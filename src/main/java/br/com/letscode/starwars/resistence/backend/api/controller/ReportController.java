package br.com.letscode.starwars.resistence.backend.api.controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.letscode.starwars.resistence.config.ResistenceConstants;
import br.com.letscode.starwars.resistence.core.api.data.Item;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(ResistenceConstants.API_PREFIX + "/reports")
@Api(value = "Report", description = "API REST para relatórios", tags = { "Report" })
public interface ReportController {

    @ApiOperation(value = "Porcentagem de traidores")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a porcentagem de rebeldes traidores"),
            @ApiResponse(code = 500, message = "Ops, acho que estamos sendo atacados."), })
    @GetMapping(value = "/traidores", produces = MediaType.APPLICATION_JSON_VALUE)
    double traidores();

    @ApiOperation(value = "Porcentagem de rebeldes")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a porcentagem de rebeldes"),
            @ApiResponse(code = 500, message = "Ops, acho que estamos sendo atacados."), })
    @GetMapping(value = "/rebeldes", produces = MediaType.APPLICATION_JSON_VALUE)
    double rebeldes();

    @ApiOperation(value = "Quantidade média de cada tipo de recurso por rebelde (Ex: 2 armas por rebelde).")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a quantidade média de cada tipo de recurso por rebelde (Ex: 2 armas por rebelde)."),
            @ApiResponse(code = 500, message = "Ops, acho que estamos sendo atacados."), })
    @GetMapping(value = "/itens", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<Item, Long> mediaItens();

    @ApiOperation(value = "Pontos dos rebeldes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a quantidade de pontos perdidos dos rebeldes, traidores ou nao."),
            @ApiResponse(code = 500, message = "Ops, acho que estamos sendo atacados."), })
    @GetMapping(value = "/pontos", produces = MediaType.APPLICATION_JSON_VALUE)
    double pontosRebeldes(@RequestParam(name = "traidores", required = false) Boolean traidores);
}
