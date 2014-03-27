package com.element.flow.process;

import java.io.IOException;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ElementDeserializerTwo extends JsonDeserializer<Element>{

	public ElementDeserializerTwo() {
		super();
	}

	@Override
	public Element deserialize(JsonParser jp, DeserializationContext context)
			throws IOException, JsonProcessingException {
		Element el; 
		String tag = "";
		String baseUri = "";
		String element = "";
		
		while (jp.nextToken() != JsonToken.END_OBJECT) {
			String fieldname = jp.getCurrentName();
			
			if (fieldname.equals("tag")) {
				jp.nextToken();
				tag = jp.getText();
			} else if (fieldname.equals("element")) {
				jp.nextToken();
				element = jp.getText();
			}
		}
		Tag newTag = Tag.valueOf(tag);
		el = new Element(newTag, baseUri);
		el.prepend(element);
		
		return el;
	}
	
	

}
