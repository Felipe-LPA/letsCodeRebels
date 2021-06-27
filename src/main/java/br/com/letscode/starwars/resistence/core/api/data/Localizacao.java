package br.com.letscode.starwars.resistence.core.api.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "LOCALIZACAO")
public class Localizacao implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5928407679893789643L;

    /**
     * Id do objeto na base de dados
     */
    @Id
    @SequenceGenerator(name = "localizacaoSequenceGenerator", sequenceName = "LOCALIZACAO_SQ", initialValue = 100)
    @GeneratedValue(generator = "localizacaoSequenceGenerator", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", insertable = true, updatable = false, nullable = true)
    private Long id;
    
    /**
     * Coordenadas de latitude
     */
    @NotNull(message = "Toda localizacao deve possuir uma latidade")
    @Column(name = "LATITUDE", nullable = false)
    private double latitute;
    
    /**
     * Coordenadas de longitude
     */
    @NotNull(message = "Toda localizacao deve possuir uma longitude")
    @Column(name = "LONGITUDE", nullable = false)
    private double longitude;
    
    /**
     * Nome do local
     */
    @NotEmpty(message = "O nome do local não pode estar vazio")
    @Size(min = 1, max = 200, message = "O tamanho máximo do nome é de 200 caracteres")
    @Column(name = "NOME", nullable = false, length = 200)
    private String nome;
    
    /**
     * Nome da galáxia da localização
     */
    @NotEmpty(message = "O nome do galaxia não pode estar vazio")
    @Size(min = 1, max = 200, message = "O tamanho máximo do nome é de 200 caracteres")
    @Column(name = "GALAXIA", nullable = false, length = 200)
    private String galaxia;
    
    /**
     * Nome da base
     */
    @NotEmpty(message = "O nome da base não pode estar vazio")
    @Size(min = 1, max = 200, message = "O tamanho máximo do nome é de 200 caracteres")
    @Column(name = "BASE", nullable = false, length = 200)
    private String base;
    
}
