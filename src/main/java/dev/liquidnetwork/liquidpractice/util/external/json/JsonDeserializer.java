package dev.liquidnetwork.liquidpractice.util.external.json;

import com.google.gson.JsonObject;

public interface JsonDeserializer<T> {

    T deserialize(JsonObject object);

}
