package com.element.flow.process;

import java.io.IOException;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ElementSerializerTwo extends JsonSerializer<Element>{

	public ElementSerializerTwo() {
		super();
	}
	
	@Override
	public void serialize(Element el, JsonGenerator json,
			SerializerProvider serializer) throws IOException,
			JsonProcessingException {
		json.writeStartObject();
		json.writeObjectField("tag", el.tag().toString());
		//json.writeObjectField("attributes", el.attributes().asList());
		json.writeObjectField("element", el.toString());
		json.writeEndObject();
	}

}
