package br.com.letscode.starwars.resistence.core.api.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "TROCA")
public class Troca implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3057916413686627616L;

    /**
     * Id do objeto na base de dados
     */
    @Id
    @SequenceGenerator(name = "trocaSequenceGenerator", sequenceName = "TROCA_SQ", initialValue = 100)
    @GeneratedValue(generator = "trocaSequenceGenerator", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", insertable = true, updatable = false, nullable = true)
    private Long id;

    /**
     * Oferta dos itens
     */
    @ManyToMany(cascade = { CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST })
    @JoinTable(name = "OFERTA", 
            joinColumns = @JoinColumn(name = "TROCA_ID"), 
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
    private List<Item> oferta = new ArrayList<>();

    /**
     * Itens procurados para serem trocados
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST })
    @JoinTable(name = "PROCURA", 
            joinColumns = @JoinColumn(name = "TROCA_ID"), 
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
    private List<Item> procura = new ArrayList<>();

    /**
     * Dono da oferta
     */
    @NotNull(message = "Toda oferta tem que ter um dono")
    @Column(name = "PARTE_ID", nullable = false)
    private Long parte;

    @Column(name = "CONTRA_PARTE_ID", nullable = true)
    private Long contraParte;
}
