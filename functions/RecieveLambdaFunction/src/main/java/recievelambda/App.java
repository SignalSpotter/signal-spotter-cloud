package recievelambda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();

    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent().withHeaders(headers);

        try {

            AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();

            DynamoDB dynamoDB = new DynamoDB(client);
            Table table = dynamoDB.getTable("wifi-map-csusm");

            List<MyItem> itemList = new ArrayList<>();
            for (Item item : table.scan()) {
                MyItem myItem = new MyItem();
                myItem.setId(item.getString("id"));
                myItem.setDateTime(item.getString("dateTime"));
                myItem.setX(item.getNumber("x"));
                myItem.setY(item.getNumber("y"));
                itemList.add(myItem);
            }

            String output = String.format(
                    "{ \"message\": \"I AM A RECIEVE FUNCTION\",  \"items\": \"%1$s\" }",
                    itemList.toString());

            return response
                    .withStatusCode(200)
                    .withBody(output);
        } catch (Exception e) {
            return response
                    .withBody("{" + e.toString() + "}")
                    .withStatusCode(500);
        }
    }

}
