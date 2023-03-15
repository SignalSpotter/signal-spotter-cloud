package recievelambda;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "wifi-map-csusm")
public class Item {

    private String Id;

    public void setId(String id) {
        this.Id = id;
    }

    @DynamoDBHashKey(attributeName = "Id")
    public String getId() {
        return this.Id;
    }

}