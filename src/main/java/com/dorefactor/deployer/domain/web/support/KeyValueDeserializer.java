package com.dorefactor.deployer.domain.web.support;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.ImmutableMap;

import java.io.IOException;
import java.util.Map;

public class KeyValueDeserializer extends JsonDeserializer<Map<String, String>> {
    @Override
    public Map<String, String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {



        return ImmutableMap.of(jsonParser.currentName(), jsonParser.getValueAsString());
    }
}
