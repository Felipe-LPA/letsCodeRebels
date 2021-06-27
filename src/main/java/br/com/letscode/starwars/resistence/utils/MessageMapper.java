package br.com.letscode.starwars.resistence.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.experimental.UtilityClass;

/**
 * Classe responsável por conversão de Mappers
 */
@UtilityClass
public class MessageMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
    *
    * Converte objeto passado por parâmetro em JSON
    *
    * @param object - objeto a ser convertido
    * @param <T>    - Generic
    * @return - String JSON convertida
    * @throws IllegalArgumentException se ocorreu erro na conversão
    */
   public static <T> String toJson(T object) {
       try {
           return objectMapper.writeValueAsString(object);
       } catch (JsonProcessingException e) {
           throw new IllegalArgumentException("Erro ao converter em JSON");
       }
   }
   
    /**
     * Deserializa String JSON em Objeto
     * 
     * @param <T>        Classe que será desserializada
     * @param json       Objeto serializado em String
     * @param returnType classe do objeto que será desserializado
     * @return Objeto desserializado
     */
    public static <T> T toObject(String json, Class<T> returnType) {
        try {
            return objectMapper.readValue(json, returnType);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(
                    "Erro ao mapear " + returnType.getClass().getSimpleName() + " - " + json);
        }
    }

}
