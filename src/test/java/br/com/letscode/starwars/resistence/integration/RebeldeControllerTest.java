package br.com.letscode.starwars.resistence.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import br.com.letscode.starwars.resistence.config.ResistenceConstants;
import br.com.letscode.starwars.resistence.core.api.data.Item;
import br.com.letscode.starwars.resistence.core.api.data.Localizacao;
import br.com.letscode.starwars.resistence.core.api.data.Rebelde;
import br.com.letscode.starwars.resistence.utils.MessageMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
class RebeldeControllerTest {

    private static final String baseUri = ResistenceConstants.API_PREFIX + "/rebeldes";

    @Autowired
    private MockMvc mvc;

    @Test
    void testRetrieveEzra() throws Exception {
        Rebelde rebelde = MessageMapper.toObject(
                mvc.perform(get(baseUri + "/50").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(), 
                Rebelde.class);
        
        assertEquals("Ezra Bridger", rebelde.getNome(), "O nome do heroi deve ser Ezra Bridger");
        assertEquals(false, rebelde.isTraidor(), "O jovem jedi não é um traidor");
        assertEquals(3, rebelde.getInventario().size(), "O jovem jedi deve possuir 3 itens");
        assertEquals("Orla Exterior", rebelde.getLocalizacao().getGalaxia(), "O jovem jedi está na Orla Exterior");
    }
    
    @Test
    void testRetrieveSabine() throws Exception {
        Rebelde rebelde = MessageMapper.toObject(
                mvc.perform(get(baseUri + "/51").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(), 
                Rebelde.class);
        
        assertEquals("Sabine", rebelde.getNome(), "O nome da rebelde deve ser Sabine");
        assertEquals(false, rebelde.isTraidor(), "O jovem jedi não é um traidor");
        assertEquals(2, rebelde.getInventario().size(), "A Mandalorian deve possuir 3 itens");
        assertEquals("Orla Exterior", rebelde.getLocalizacao().getGalaxia(), "A Mandalorian está na Orla Exterior");
    }
    
    @Test
    @Transactional
    void testDenunciaSabine() throws Exception {
        Boolean traidor = MessageMapper.toObject(
                mvc.perform(delete(baseUri + "/51").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(), 
                Boolean.class);
        
        assertTrue(traidor, "Sabine agora é uma traidora");
    }
    
    @Test
    @Transactional
    void testDenunciaEzra() throws Exception {
        Boolean traidor = MessageMapper.toObject(
                mvc.perform(delete(baseUri + "/50").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(), 
                Boolean.class);
        
        assertTrue(!traidor, "Ezra não é um traidor");
    }
    
    @Test
    void testIsTraidorSabine() throws Exception {
        Boolean traidor = MessageMapper.toObject(
                mvc.perform(get(baseUri + "/traidor/51").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(), 
                Boolean.class);
        
        assertTrue(!traidor, "Sabine não é uma traidora");
    }
    
    @Test
    void testIsTraidorMaul() throws Exception {
        Boolean traidor = MessageMapper.toObject(
                mvc.perform(get(baseUri + "/traidor/52").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(), 
                Boolean.class);
        
        assertTrue(traidor, "Maul é uma traidor!");
    }
    
    @Test
    void testIsTraidorNotFound() throws Exception {
        mvc.perform(get(baseUri + "/traidor/49").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
        
    }
    
    @Test
    @Transactional
    void testCreateInvalidRebelde() throws Exception {
        Rebelde rebelde = new Rebelde();
        mvc.perform(post(baseUri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(MessageMapper.toJson(rebelde)))
                .andDo(print())
                .andExpect(status().isBadRequest());
        
    }
    
    @Test
    void testCreateValidRebeldeLothal() throws Exception {
        Rebelde rebelde = new Rebelde()
                .setGenero("Masculino")
                .setIdade(30)
                .setNome("Kanan Jarrus")
                .setLocalizacao(new Localizacao().setId(50L))
                .setInventario(Collections.singletonList(new Item().setId(1L)));
        
        Rebelde rebeldeCadastrado = MessageMapper.toObject(
                mvc.perform(post(baseUri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MessageMapper.toJson(rebelde)))
                    .andDo(print())
                    .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(), 
                Rebelde.class);
        
        assertEquals(rebelde.getIdade(), rebeldeCadastrado.getIdade());
        assertEquals(rebelde.getNome(), rebeldeCadastrado.getNome());
        assertEquals(1, rebeldeCadastrado.getInventario().size());
        assertEquals("Arma", rebeldeCadastrado.getInventario().get(0).getNome());
        assertEquals("Lothal", rebeldeCadastrado.getLocalizacao().getNome());
    }


    @Test
    @Transactional
    void testCreateValidRebeldeNovaLocalizacao() throws Exception {
        Rebelde rebelde = new Rebelde()
                .setGenero("Masculino")
                .setIdade(30)
                .setNome("Kanan Jarrus")
                .setLocalizacao(new Localizacao()
                        .setBase("Alderan")
                        .setGalaxia("Alderan System")
                        .setLatitute(1.234567)
                        .setLongitude(0)
                        .setNome("QG"))
                .setInventario(Collections.singletonList(new Item().setId(2L)));
        
        Rebelde rebeldeCadastrado = MessageMapper.toObject(
                mvc.perform(post(baseUri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MessageMapper.toJson(rebelde)))
                    .andDo(print())
                    .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(), 
                Rebelde.class);
        
        assertEquals(rebelde.getIdade(), rebeldeCadastrado.getIdade());
        assertEquals(rebelde.getNome(), rebeldeCadastrado.getNome());
        assertEquals(1, rebeldeCadastrado.getInventario().size());
        assertEquals("QG", rebeldeCadastrado.getLocalizacao().getNome());
        assertEquals("Alderan", rebeldeCadastrado.getLocalizacao().getBase());
        assertTrue(rebeldeCadastrado.getLocalizacao().getId() > 99, "Id deve começar em 100");
    }
}
