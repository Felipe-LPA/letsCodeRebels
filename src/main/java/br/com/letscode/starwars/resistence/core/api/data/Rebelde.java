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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "REBELDE")
@Slf4j
public class Rebelde implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1718307778513129185L;

    /**
     * Id do objeto na base de dados
     */
    @Id
    @SequenceGenerator(name = "rebeldeSequenceGenerator", sequenceName = "REBELDE_SQ", initialValue = 100)
    @GeneratedValue(generator = "rebeldeSequenceGenerator", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", insertable = true, updatable = false, nullable = true)
    private Long id;

    /**
     * Nome do rebelde
     */
    @NotEmpty(message = "O nome do rebelde não pode estar vazio")
    @Size(min = 1, max = 200, message = "O tamanho máximo do nome é de 200 caracteres")
    @Column(name = "NOME", nullable = false, length = 200)
    private String nome;

    /**
     * Idade do rebelde
     */
    @NotNull(message = "Todo rebelde deve possuir uma idade")
    @Min(value = 0, message = "A idade não pode ser negativa")
    @Column(name = "idade", nullable = false)
    private int idade;

    /**
     * Genero do rebelde
     */
    @NotEmpty(message = "O genero do rebelde não pode estar vazio")
    @Size(min = 1, max = 100, message = "O tamanho máximo do genero é de 100 caracteres")
    @Column(name = "GENERO", nullable = false, length = 100)
    private String genero;

    /**
     * Localização atual do rebelde
     */
    @NotNull(message = "Todo rebelde deve possuir uma localizacao")
    @ManyToOne(optional = true, fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH, CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "LOCALIZACAO_ID", referencedColumnName = "ID", nullable = false)
    private Localizacao localizacao;

    /**
     * Inventario do rebelde
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST })
    @JoinTable(name = "INVENTARIO", 
            joinColumns = @JoinColumn(name = "REBELDE_ID"), 
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
    private List<Item> inventario = new ArrayList<>();

    /**
     * Quantidade de pessoas que denunciaram esse rebelde como traidor
     */
    @NotNull
    @Min(value = 0, message = "O número de denuncias tem que ser positovo.")
    @Column(name = "denuncias", nullable = false)
    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private int denuncias = 0;

    public boolean isTraidor() {
        boolean traidor = this.denuncias > 2;
        logger.info("O rebelde {} um traidor!", traidor ? "é" : "não é");
        return traidor;
    }

    public boolean denunciar() {
        logger.info("O rebelde {} cujo id é {} foi denunciado.", this.nome, this.id);
        this.denuncias++;
        return isTraidor();
    }
}
