package br.com.letscode.starwars.resistence.backend.api.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.letscode.starwars.resistence.config.ResistenceConstants;
import br.com.letscode.starwars.resistence.core.api.data.Item;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(ResistenceConstants.API_PREFIX + "/itens")
@Api(value = "Iten", description = "API REST para itens", tags = { "Item" })
public interface ItemController {

    @ApiOperation(value = "Busca todos os itens cadastrados")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista de itens cadastrados"),
            @ApiResponse(code = 404, message = "Nenhum item encontrado"),
            @ApiResponse(code = 500, message = "Ops, acho que estamos sendo atacados."), })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<Item> retrieveAll();

    @ApiOperation(value = "Busca um item pelo ID")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna o item que está sendo procurado"),
            @ApiResponse(code = 404, message = "Nenhum item encontrado"),
            @ApiResponse(code = 500, message = "Ops, acho que estamos sendo atacados."), })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Item retrieveById(@PathVariable("id") Long id);
}