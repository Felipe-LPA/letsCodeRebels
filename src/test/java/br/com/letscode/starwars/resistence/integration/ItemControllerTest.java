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
import org.springframework.test.web.servlet.MockMvc;

import br.com.letscode.starwars.resistence.config.ResistenceConstants;
import br.com.letscode.starwars.resistence.core.api.data.Item;
import br.com.letscode.starwars.resistence.utils.MessageMapper;

@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTest {

    private static final String baseUri = ResistenceConstants.API_PREFIX + "/itens";

    @Autowired
    private MockMvc mvc;

    @Test
    void testObtainInitialItens() throws Exception {
        assertEquals(4,
                MessageMapper.toObject(
                        mvc.perform(get(baseUri).contentType(MediaType.APPLICATION_JSON)).andDo(print())
                                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(),
                        Item[].class).length,
                "O retorno tem que possuir 4 itens");
    }

    @Test
    void testObtainValidItem() throws Exception {
        Item item = MessageMapper.toObject(
                mvc.perform(get(baseUri + "/1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(), 
                Item.class);

        assertEquals("Arma", item.getNome(), "O item cujo ID é 1 deve ser arma");
        assertEquals(4, item.getPonto(), "O item cujo ID é 1 deve ter 4 pontos");
    }

    @Test
    void testObtainInvalidItem() throws Exception {
        mvc.perform(get(baseUri + "/99").contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isNotFound());

    }

}
