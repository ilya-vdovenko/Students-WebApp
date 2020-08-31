package org.spring.samples.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class Group_classSerializer extends JsonSerializer<Group_class> {

  @Override
  public void serialize(Group_class group_class, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    jsonGenerator.writeStartObject();
    jsonGenerator.writeNumberField("id", group_class.getId());
    jsonGenerator.writeStringField("title", group_class.getTitle());
    jsonGenerator.writeEndObject();
  }
}
