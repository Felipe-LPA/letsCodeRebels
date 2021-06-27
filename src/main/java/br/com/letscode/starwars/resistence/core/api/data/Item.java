package br.com.letscode.starwars.resistence.core.api.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.letscode.starwars.resistence.utils.MessageMapper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "ITEM")
public class Item implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5185885467735688662L;

    /**
     * Id do objeto na base de dados
     */
    @Id
    @SequenceGenerator(name = "itemSequenceGenerator", sequenceName = "ITEM_SQ", initialValue = 100)
    @GeneratedValue(generator = "itemSequenceGenerator", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", insertable = true, updatable = false, nullable = true)
    private Long id;

    /**
     * Nome do item
     */
    @NotEmpty(message = "O nome do item não pode estar vazio")
    @Size(min = 1, max = 200, message = "O tamanho máximo do nome é de 200 caracteres")
    @Column(name = "NOME", nullable = false, length = 200)
    private String nome;

    /**
     * Quantidade de pontos que o item vale
     */
    @NotNull(message = "Todo item deve possuir uma pontuação")
    @Min(value = 0, message = "Todo item deve possuir uma pontuação maior que 0")
    @Column(name = "PONTO", nullable = false)
    private int ponto;

    @Override
    public String toString() {
        return MessageMapper.toJson(this);
    }
}
