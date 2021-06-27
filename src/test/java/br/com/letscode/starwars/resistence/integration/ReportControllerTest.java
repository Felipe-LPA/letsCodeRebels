package br.com.letscode.starwars.resistence.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import br.com.letscode.starwars.resistence.config.ResistenceConstants;
import br.com.letscode.starwars.resistence.utils.MessageMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
class ReportControllerTest {

    private static final String baseUri = ResistenceConstants.API_PREFIX + "/reports";

    @Autowired
    private MockMvc mvc;

    @Test
    void testPorcentagemTraidores() throws Exception {
        assertEquals(1 / 3.0,
                MessageMapper.toObject(
                        mvc.perform(get(baseUri + "/traidores").contentType(MediaType.APPLICATION_JSON)).andDo(print())
                                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(),
                        double.class),
                "A porcentagem deve ser de 1/3");
    }

    @Test
    void testPorcentagemRebeldes() throws Exception {
        assertEquals(2 / 3.0,
                MessageMapper.toObject(
                        mvc.perform(get(baseUri + "/rebeldes").contentType(MediaType.APPLICATION_JSON)).andDo(print())
                                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(),
                        double.class),
                "A porcentagem deve ser de 2/3");
    }

    @Test
    void testPontosTotais() throws Exception {
        assertEquals(40.0,
                MessageMapper.toObject(
                        mvc.perform(get(baseUri + "/pontos").contentType(MediaType.APPLICATION_JSON)).andDo(print())
                                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(),
                        double.class),
                "A soma de pontos deve ser de 40");
    }

    @Test
    void testPontosTraidores() throws Exception {
        assertEquals(11.0, MessageMapper.toObject(
                mvc.perform(get(baseUri + "/pontos?traidores=true").contentType(MediaType.APPLICATION_JSON))
                        .andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(),
                double.class), "A soma de pontos deve ser de 11");
    }

    @Test
    void testPontosRebeldes() throws Exception {
        assertEquals(29.0, MessageMapper.toObject(
                mvc.perform(get(baseUri + "/pontos?traidores=false").contentType(MediaType.APPLICATION_JSON))
                        .andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(),
                double.class), "A soma de pontos deve ser de 29");
    }

    @Test
    void testMediaItens() throws Exception {
        mvc.perform(get(baseUri + "/itens").contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
    }
}