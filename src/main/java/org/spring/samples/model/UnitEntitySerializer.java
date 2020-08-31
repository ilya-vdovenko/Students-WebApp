package org.spring.samples.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class UnitEntitySerializer extends JsonSerializer<UnitEntity> {

  @Override
  public void serialize(UnitEntity unitEntity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
    throws IOException {
    jsonGenerator.writeStartObject();
    jsonGenerator.writeNumberField("id", unitEntity.getId());
    jsonGenerator.writeStringField("title", unitEntity.getTitle());
    jsonGenerator.writeEndObject();
  }
}
