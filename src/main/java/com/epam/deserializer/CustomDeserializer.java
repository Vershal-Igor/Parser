package com.epam.deserializer;

import com.epam.entity.Article;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class CustomDeserializer extends JsonDeserializer<Article> {
    private static final String DEFAULT_AUTHOR = "UNKNOWN";
    private static final String NO_ELEMENT = "";

    @Override
    public Article deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException,
            JsonProcessingException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        JsonNode articleNode = node.get("article");

        final String title = safetyGetElement(articleNode.get("title"));
        String authorName = safetyGetElement(articleNode.get("author_name"));
        if (authorName.isEmpty()) {
            authorName = DEFAULT_AUTHOR;
        }

        return new Article(title, authorName);
    }

    private String safetyGetElement(JsonNode jsonNode) {
        if (jsonNode == null) {
            return NO_ELEMENT;
        } else {
            return jsonNode.asText();
        }
    }
}




