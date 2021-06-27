package br.com.letscode.starwars.resistence.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import br.com.letscode.starwars.resistence.config.ResistenceConstants;
import br.com.letscode.starwars.resistence.core.api.data.Localizacao;
import br.com.letscode.starwars.resistence.utils.MessageMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
class LocalizacaoControllerTest {

    private static final String baseUri = ResistenceConstants.API_PREFIX + "/localizacoes/rebelde/";

    @Autowired
    private MockMvc mvc;

    @Test
    void testLocalizacaoInvalida() throws Exception {
        mvc.perform(get(baseUri + "49").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
        
    }

    @Test
    void testLocalizacaoMaul() throws Exception {
        Localizacao local = MessageMapper.toObject(
                mvc.perform(get(baseUri + "52").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(), 
                Localizacao.class);
        
        assertEquals("Lothal", local.getNome(), "Está em Lothal");
        assertEquals("Jedi Temple", local.getBase(), "Está na base Jedi Temple");
        assertEquals("Orla Exterior", local.getGalaxia(), "Está na Orla Exterior");
        assertEquals(0.12346, local.getLatitute(), "Coordenada errada");
        assertEquals(-0.54320, local.getLongitude(), "Coordenada errada");
    }

    @Test
    void testLocalizacaoSabine() throws Exception {
        Localizacao local = MessageMapper.toObject(
                mvc.perform(get(baseUri + "51").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(), 
                Localizacao.class);
        
        assertEquals("Lothal", local.getNome(), "Está em Lothal");
        assertEquals("Jedi Temple", local.getBase(), "Está na base Jedi Temple");
        assertEquals("Orla Exterior", local.getGalaxia(), "Está na Orla Exterior");
        assertEquals(0.12345, local.getLatitute(), "Coordenada errada");
        assertEquals(-0.54321, local.getLongitude(), "Coordenada errada");
    }

    @Test
    @Transactional
    void testAtualizaLocalizacaoSabine() throws Exception {
        Localizacao nova = new Localizacao()
                .setBase("Nenhuma")
                .setGalaxia("Orla Exterior")
                .setLatitute(0)
                .setLongitude(0)
                .setNome("Deserto");

        Localizacao local = MessageMapper.toObject(
                mvc.perform(put(baseUri + "51").contentType(MediaType.APPLICATION_JSON)
                        .content(MessageMapper.toJson(nova)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(), 
                Localizacao.class);
        
        assertEquals("Deserto", local.getNome(), "Está no Deserto");
        assertEquals("Nenhuma", local.getBase(), "Está na em nenhuma base");
        assertEquals("Orla Exterior", local.getGalaxia(), "Está na Orla Exterior");
        assertEquals(0, local.getLatitute(), "Coordenada errada");
        assertEquals(0, local.getLongitude(), "Coordenada errada");
    }
    
    @Test
    @Transactional
    void testAtualizaLocalizacaoMaul() throws Exception {
        Localizacao nova = new Localizacao().setId(50L);
        
        Localizacao local = MessageMapper.toObject(
                mvc.perform(put(baseUri + "52").contentType(MediaType.APPLICATION_JSON)
                        .content(MessageMapper.toJson(nova)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(), 
                Localizacao.class);
        
        assertEquals("Lothal", local.getNome(), "Está em Lothal");
        assertEquals("Jedi Temple", local.getBase(), "Está na base Jedi Temple");
        assertEquals("Orla Exterior", local.getGalaxia(), "Está na Orla Exterior");
        assertEquals(0.12345, local.getLatitute(), "Coordenada errada");
        assertEquals(-0.54321, local.getLongitude(), "Coordenada errada");
    }
    
    @Test
    void testAtualizaLocalizacaoRebeldeInvalido() throws Exception {
        mvc.perform(put(baseUri + "512").contentType(MediaType.APPLICATION_JSON)
                        .content(MessageMapper.toJson(new Localizacao())))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void testAtualizaLocalizacaoInvalidaRebelde() throws Exception {
        
        mvc.perform(put(baseUri + "52").contentType(MediaType.APPLICATION_JSON)
                        .content(MessageMapper.toJson(new Localizacao())))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
