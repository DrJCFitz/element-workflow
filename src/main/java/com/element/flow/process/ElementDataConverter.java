package com.element.flow.process;

import java.util.List;

import org.jsoup.nodes.Element;

import com.amazonaws.services.simpleworkflow.flow.JsonDataConverter;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class ElementDataConverter extends JsonDataConverter {
    public ElementDataConverter() {
        super();
        SimpleModule module = new SimpleModule("elementModule");
    	module.addSerializer(Element.class, new ElementSerializerTwo());
    	module.addDeserializer(Element.class, new ElementDeserializerTwo());
		mapper.registerModule(module);

		JavaType elementList = mapper.getTypeFactory().constructCollectionType(List.class, Element.class);
		//mapper.reader(new TypeReference<List<Element>>(){});
		mapper.reader(elementList);
		mapper.getFactory().setCharacterEscapes(new HTMLCharacterEscapes());
    }
}
