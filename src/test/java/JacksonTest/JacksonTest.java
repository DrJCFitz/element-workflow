package JacksonTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.element.flow.model.Merchant;
import com.element.flow.model.PortalProfile;
import com.element.flow.model.Reward;
import com.element.flow.process.ElementDeserializerTwo;
import com.element.flow.process.ElementSerializerTwo;
import com.element.flow.process.HTMLCharacterEscapes;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class JacksonTest {

	@SuppressWarnings({ "serial", "deprecation" })
	public static void main(String[] args) throws IOException {
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"classpath:portalProfile-config.xml");

		PortalProfile ebatesProfile = appContext.getBean("ebatesProfile",
				PortalProfile.class);

		//List<Element> portalElements = getElements(ebatesProfile);
		String tagHtml = "<tr class='store'> \n <td class='fav'> \n  <div class='fav-n ui2012' id='Coastal.com' data-merch='9629'></div> </td> \n <td class='storeName'> <strong><a href='/coupons/coastal-com/index.htm'>Coastal.com Coupons &amp; Cash Back </a></strong> </td> \n <td class='storeRebate'> <a href='/coastal-com_9629-xfas?campaign_entity_id=15901061&amp;navigation_id=10003' rel='nofollow' target='_blank'>5.0% </a> </td> \n <td class='storeInfo shop'> <a href='/coastal-com_9629-xfas?campaign_entity_id=15901061&amp;navigation_id=10003' rel='nofollow' target='_blank' class='ui2012'></a> </td> \n</tr>";
		String baseUri = "http://www.ebates.com";
		//String elTag = portalElements.get(0).tagName();
		//Don't set attribute - will set with double quotes.
		//Attributes elAttrs = portalElements.get(0).attributes().clone();
		
		Element el = new Element(Tag.valueOf("tr"), baseUri);
		el.prepend(tagHtml);
		
		List<Element> elementList = new ArrayList<Element>();
		elementList.add(el);
		elementList.add(el);
		
		//Merchant merch = new Merchant("name", "mkey", "link", new Reward(5.0,
		//		"points"), new GregorianCalendar().getTimeInMillis(), true);
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("myModule", new Version(1, 0, 0,
				null));
		module.addSerializer(Element.class, new ElementSerializerTwo());
		module.addDeserializer(Element.class, new ElementDeserializerTwo());
		mapper.registerModule(module);
		JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, Element.class);
		mapper.reader(type);
		//mapper.reader(new TypeReference<List<Element>>(){});
		mapper.getFactory().setCharacterEscapes(new HTMLCharacterEscapes());
		//System.out.println(el.toString());
		//System.out.println(el.select("td.storeName a").attr("href"));

		try {

			// convert user object to json string, and save to a file
			mapper.writeValue(
					new File(
							"c:\\Users\\John\\JavaEE\\sample-workflow\\portalElements.json"),
					elementList);

			// display to console
			// System.out.println(mapper.writeValueAsString(merch));
			// System.out.println(mapper.writeValueAsString(portalElements));
			List<Element> elList2 = mapper.readValue(new File("c:\\Users\\John\\JavaEE\\sample-workflow\\portalElements.json"), type);
			System.out.println(elList2);

		} catch (JsonGenerationException e) {

			e.printStackTrace();

		} catch (JsonMappingException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	private static List<Element> getElements(PortalProfile profile)
			throws IOException {
		Document doc = Jsoup.connect(profile.getAllStoresUrl()).get();
		Elements storeLinks = doc.select(profile.getRootElement());
		List<Element> portalElement = storeLinks.subList(0,
				storeLinks.size() - 1);
		//System.out.println(portalElement.get(0));
		return portalElement;
	}

}
