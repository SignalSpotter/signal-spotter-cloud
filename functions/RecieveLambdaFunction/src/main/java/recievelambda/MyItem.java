package recievelambda;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "wifi-map-csusm")
public class MyItem {

    private String Id;
    private String dateTime;
    private Number x;
    private Number y;

    public void setId(String id) {
        this.Id = id;
    }

    @DynamoDBHashKey(attributeName = "Id")
    public String getId() {
        return this.Id;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @DynamoDBRangeKey(attributeName = "dateTime")
    public String getDateTime() {
        return this.dateTime;
    }

    public void setX(Number x) {
        this.x = x;
    }

    @DynamoDBAttribute(attributeName = "x")
    public Number getX() {
        return this.x;
    }

    public void setY(Number y) {
        this.y = y;
    }

    @DynamoDBAttribute(attributeName = "y")
    public Number getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return "{id: " + this.Id + ", dateTime: " + this.dateTime + ", x: " + this.x.floatValue()
                + ", y: " + this.y.floatValue() + "}";
    }

}