package br.com.alexsdm.dog.hero.adapter.out.database.datamodel;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@DynamoDBTable(tableName = "Passeio")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PasseioItem {

    @DynamoDBHashKey(attributeName = "dono_id")
    @DynamoDBAutoGeneratedKey
    private String idDono;

    @DynamoDBRangeKey(attributeName = "passeio_id")
    @DynamoDBAutoGeneratedKey
    private String idPasseio;

    @DynamoDBAttribute(attributeName = "pets_id")
    private String pets;

    @DynamoDBAttribute(attributeName = "data_agendamento")
    private String dataAgendamento;

    private String duracao;

    private String local;

    private BigDecimal preco;

    private String status;


}
