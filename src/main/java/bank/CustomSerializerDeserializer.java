package bank;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Klasse zur Persistierung von Transaktions-Objekten
 */
public class CustomSerializerDeserializer implements JsonSerializer<Transaction>, JsonDeserializer<Transaction> {

    /**
     * Serialisierung von Transaktions-Objekten
     * @param transaction
     * @param type
     * @param jsonSerializationContext
     * @return
     */
    @Override
    public JsonElement serialize(Transaction transaction, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        JsonObject Ausgabe = new JsonObject();
        jsonObject.addProperty("date", transaction.getDate());
        jsonObject.addProperty("description", transaction.getDescription());
        jsonObject.addProperty("amount", transaction.getAmount());

        if (transaction instanceof Transfer transfer) {
            jsonObject.addProperty("sender", transfer.getSender());
            jsonObject.addProperty("recipient", transfer.getRecipient());
        } else if (transaction instanceof Payment payment) {
            jsonObject.addProperty("incomingInterest", payment.getIncomingInterest());
            jsonObject.addProperty("outcomingInterest", payment.getOutgoingInterest());
        }
        Ausgabe.addProperty("CLASSNAME", transaction.getClass().getSimpleName());
        Ausgabe.add("Instance", jsonObject);

        return Ausgabe;
    }

    /**
     * Deserialisierung von Json Dateien zu Transaktionsobjekten
     * @param jsonElement
     * @param type
     * @param jsonDeserializationContext
     * @return
     * @throws JsonParseException
     */
    @Override
    public Transaction deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        if (jsonObject.get("CLASSNAME").getAsString().equals("IncomingTransfer")) {
            return new IncomingTransfer(jsonObject.get("date").getAsString(), jsonObject.get("amount").getAsDouble(), jsonObject.get("description").getAsString(), jsonObject.get("sender").getAsString(), jsonObject.get("recipient").getAsString());
        } else if (jsonObject.get("CLASSNAME").getAsString().equals("OutgoingTransfer")) {
            return new OutgoingTransfer(jsonObject.get("date").getAsString(), jsonObject.get("amount").getAsDouble(), jsonObject.get("description").getAsString(), jsonObject.get("sender").getAsString(), jsonObject.get("recipient").getAsString());
        } else if (jsonObject.get("CLASSNAME").getAsString().equals("Payment")) {
            return new Payment(jsonObject.get("date").getAsString(), jsonObject.get("amount").getAsDouble(), jsonObject.get("description").getAsString(), jsonObject.get("incomingInterest").getAsDouble(), jsonObject.get("outgoingInterest").getAsDouble());
        }
        return null;
    }
}
