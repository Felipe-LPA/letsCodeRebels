package br.com.letscode.starwars.resistence.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import javax.transaction.Transactional;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import br.com.letscode.starwars.resistence.config.ResistenceConstants;
import br.com.letscode.starwars.resistence.core.api.data.Item;
import br.com.letscode.starwars.resistence.core.api.data.Troca;
import br.com.letscode.starwars.resistence.utils.MessageMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
class TrocaControllerTest {

    private static final String baseUri = ResistenceConstants.API_PREFIX + "/trocas";

    @Autowired
    private MockMvc mvc;

    @Test
    void testListarTodasTrocasIniciais() throws Exception {
        assertEquals(1,
                MessageMapper.toObject(
                        mvc.perform(get(baseUri).contentType(MediaType.APPLICATION_JSON)).andDo(print())
                                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(),
                        Troca[].class).length,
                "Inicialmente o banco só possui uma troca disponível");
    }

    @Test
    void testListarTrocaValida() throws Exception {
        Troca troca = MessageMapper.toObject(mvc.perform(get(baseUri + "/51").contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), Troca.class);

        assertEquals(54L, troca.getParte(), "O ID do ofertante deve ser 54");
        assertNull(troca.getContraParte(), "A contra parte deve ser nula");
        assertEquals(1, troca.getOferta().size(), "Foi ofertado 1 item");
        assertEquals(2, troca.getProcura().size(), "Busca-se 2 itens para troca");
    }

    @Test
    void testListarTrocaJaRealizada() throws Exception {
        mvc.perform(get(baseUri + "/50").contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testListarTrocaInexistente() throws Exception {
        mvc.perform(get(baseUri + "/500").contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void testCriarTrocaValida() throws Exception {
        mvc.perform(post(baseUri).contentType(MediaType.APPLICATION_JSON)
                .content(MessageMapper.toJson(createTrocaPadrao()))).andDo(print()).andExpect(status().isOk());

        Troca troca = MessageMapper.toObject(mvc.perform(get(baseUri + "/100").contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), Troca.class);

        assertEquals(54L, troca.getParte(), "O ID do ofertante deve ser 54");
        assertNull(troca.getContraParte(), "A contra parte deve ser nula");
        assertEquals(2, troca.getOferta().size(), "Foi ofertado 2 itens");
        assertEquals(1, troca.getProcura().size(), "Busca-se 1 item para troca");
    }

    @Test
    void testCriarTrocaNaoTemItens() throws Exception {
        mvc.perform(post(baseUri).contentType(MediaType.APPLICATION_JSON)
                .content(MessageMapper.toJson(createTrocaPadrao().setParte(50L)))).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCriarTrocaValidaTraidor() throws Exception {
        mvc.perform(post(baseUri).contentType(MediaType.APPLICATION_JSON)
                .content(MessageMapper.toJson(createTrocaPadrao().setParte(55L)))).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCriarTrocaItemInvalido() throws Exception {
        mvc.perform(post(baseUri).contentType(MediaType.APPLICATION_JSON)
                .content(MessageMapper
                        .toJson(createTrocaPadrao().setProcura(Collections.singletonList(new Item().setId(1234L))))))
                .andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void testCriarTrocaPontuacaoInvalida() throws Exception {
        mvc.perform(post(baseUri).contentType(MediaType.APPLICATION_JSON).content(
                MessageMapper.toJson(createTrocaPadrao().setProcura(Collections.singletonList(new Item().setId(2L))))))
                .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    void testCriarTrocaValidaRebeldeInexistente() throws Exception {
        mvc.perform(post(baseUri).contentType(MediaType.APPLICATION_JSON)
                .content(MessageMapper.toJson(createTrocaPadrao().setParte(500L)))).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void testEfetuaTrocaValida() throws Exception {
        mvc.perform(put(baseUri + "/51?rebeldeId=53").contentType(MediaType.APPLICATION_JSON)
                .content(MessageMapper.toJson(createTrocaPadrao().setParte(500L)))).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testEfetuaTrocaSemDestino() throws Exception {
        mvc.perform(put(baseUri + "/51").contentType(MediaType.APPLICATION_JSON)
                .content(MessageMapper.toJson(createTrocaPadrao().setParte(500L)))).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testEfetuaTrocaRebeldeInexistente() throws Exception {
        mvc.perform(put(baseUri + "/51?rebeldeId=500").contentType(MediaType.APPLICATION_JSON)
                .content(MessageMapper.toJson(createTrocaPadrao().setParte(500L)))).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void testEfetuaTrocaInexistente() throws Exception {
        mvc.perform(put(baseUri + "/510?rebeldeId=53").contentType(MediaType.APPLICATION_JSON)
                .content(MessageMapper.toJson(createTrocaPadrao().setParte(500L)))).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void testEfetuaTrocaNaoTemItens() throws Exception {
        mvc.perform(put(baseUri + "/51?rebeldeId=50").contentType(MediaType.APPLICATION_JSON)
                .content(MessageMapper.toJson(createTrocaPadrao().setParte(500L)))).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testEfetuaTrocaTraidor() throws Exception {
        mvc.perform(put(baseUri + "/51?rebeldeId=55").contentType(MediaType.APPLICATION_JSON)
                .content(MessageMapper.toJson(createTrocaPadrao().setParte(500L)))).andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    @Transactional
    void testEfetuaTrocaOfertanteTraidor() throws Exception {
        Boolean traidor = MessageMapper.toObject(
                mvc.perform(delete(ResistenceConstants.API_PREFIX + "/rebeldes/54").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(), 
                Boolean.class);
        
        assertTrue(traidor, "Rebelde agora é um traidor");
        
        mvc.perform(put(baseUri + "/51?rebeldeId=53").contentType(MediaType.APPLICATION_JSON)
                .content(MessageMapper.toJson(createTrocaPadrao().setParte(500L)))).andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    void testEfetuaTrocaJaRealizada() throws Exception {
        mvc.perform(put(baseUri + "/50?rebeldeId=53").contentType(MediaType.APPLICATION_JSON)
                .content(MessageMapper.toJson(createTrocaPadrao().setParte(500L)))).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    void testDeletaTrocaValida() throws Exception {
        mvc.perform(delete(baseUri + "/51").contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());

        mvc.perform(get(baseUri + "/51").contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    void testDeletaTrocaJaRealizada() throws Exception {
        mvc.perform(delete(baseUri + "/50").contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDeletaTrocaInexistente() throws Exception {
        mvc.perform(delete(baseUri + "/500").contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isNotFound());
    }

    private Troca createTrocaPadrao() {
        return new Troca().setParte(54L).setOferta(Lists.list(new Item().setId(3L), new Item().setId(3L)))
                .setProcura(Lists.list(new Item().setId(1L)));
    }
}