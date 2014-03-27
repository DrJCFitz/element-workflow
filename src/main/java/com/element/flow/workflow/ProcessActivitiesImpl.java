package com.element.flow.workflow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.amazonaws.services.simpleworkflow.flow.annotations.Asynchronous;
import com.amazonaws.services.simpleworkflow.flow.annotations.Wait;
import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import com.amazonaws.services.simpleworkflow.flow.core.Settable;
import com.element.flow.model.Merchant;
import com.element.flow.model.PortalProfile;
import com.element.flow.model.Reward;

public class ProcessActivitiesImpl implements ProcessActivities {
	
	//@Autowired
	//private HashMap<String,String> mKeyExclusions;

	//@Override
	//public List<Merchant> parsePortalForMerchants(PortalProfile profile)
	//		throws IOException {
		/*Promise<Elements> portalElements = retrieveDocumentElements(profile);
		Settable<List<Merchant>> merchList = loopOverMerchants(portalElements, profile);
		//List<Merchant> merchList = loopOverMerchants(portalElements, profile);
		//List<Merchant> mergedList = mergeMerchants(merchList);
		Promise<List<Merchant>> promiseList = pullFromSettable(merchList);
		
		return returnFromPromise(promiseList);*/
	//}

	@Override
	public Element retrieveDocumentElements(final PortalProfile profile) throws IOException {
		Document doc = Jsoup.connect(profile.getAllStoresUrl()).get();
		Elements storeLinks = doc.select(profile.getRootElement());
		List<Element> listLinks = storeLinks.subList(0, 1);
		
		//Merchant merch = new Merchant();
		return listLinks.get(0);
	}
	
	@Asynchronous
	public Promise<List<Merchant>> pullFromSettable(Settable<List<Merchant>> settableList) {
		return settableList;
	}
	
	@Asynchronous
	public List<Merchant> returnFromPromise(Promise<List<Merchant>> promiseList) {
		return promiseList.get();
	}
	
	@Asynchronous
	public List<Merchant> mergeMerchants(@Wait Promise<List<Merchant>> merchListIn) {
		List<Merchant> merchListOut = new ArrayList<Merchant>();
		if (merchListIn.get().size()>1) {
			Promise<Void> runMerch = Promise.Void();
			for (Merchant merchInList : merchListIn.get()) {
				merchListOut.add(merchInList);
			}			
		}
		return merchListOut;
	}
	
	@Asynchronous
	public Settable<List<Merchant>> loopOverMerchants(Promise<Elements> links, PortalProfile profile) {
		Settable<List<Merchant>> settableMerchList = new Settable<List<Merchant>>();
		List<Merchant> merchList = new ArrayList<Merchant>();
		if (links.get().size()>1) {
			Promise<Void> runMerch = Promise.Void();
			for (Element link : links.get()) {
				runMerch = keepGoing(runMerch);
				merchList.add(parseElementsForMerchant(link,profile));
			}			
		}
		settableMerchList.set(merchList);
		return settableMerchList;
	}
	
	@Asynchronous
	public Promise<Void> keepGoing(Promise<Void> condition) {
		return Promise.Void();
	}
	
	@Asynchronous
	public Merchant parseElementsForMerchant(Element link, PortalProfile profile) {
		Promise<String> storeLink = getStoreLink(link, profile);
		Promise<String> merchName = getMerchName(link, profile);
		Promise<String> merchKey = checkMerchKey(merchName, profile);
		Promise<Reward> reward = parseRewardsValue(link, profile, merchName);
		return constructAsyncMerchant(merchName,merchKey,reward,storeLink);
	}
	
	@Asynchronous
	private Merchant constructAsyncMerchant(Promise<String> merchName,
			Promise<String> merchKey, Promise<Reward> reward,
			Promise<String> storeLink) {
		return new Merchant(merchName.get(), 
				merchKey.get(), 
				storeLink.get(), 
				reward.get(),
				new GregorianCalendar().getTimeInMillis(),
				true);
	}

	@Asynchronous
	private Promise<String> getStoreLink(Element link, PortalProfile profile) {
		String storeLink = profile.getPortalUrl()+link.select(profile.getMerchantElement()).attr("href");
		return Promise.asPromise(storeLink);
	}
	
	@Asynchronous
	private Promise<String> getMerchName(Element link, PortalProfile profile) {
		String merchName = link.select(profile.getMerchantElement())
				.text()
				.replaceAll(profile.getPortalRegexp().get("exclusionName"), "");
		return Promise.asPromise(merchName);
	}
	
	@Asynchronous
	private Promise<Reward> parseRewardsValue(Element link, PortalProfile profile, Promise<String> merchName) {
		/*String rewardString = "";
		if (profile.getProfileKey().equals("upromise")) {
			rewardString = link.text().substring(merchName.length()).toLowerCase().trim();
		} else {
			rewardString = link.select(profile.getRewardElement()).text().trim().toLowerCase();
		}*/
	
		Reward reward = new Reward(0.0, "none", "none");
		/*HashMap<String, String> regexpMap = profile.getPortalRegexp();
		
		rewardString = rewardString.replaceAll(regexpMap.get("exclusionReward"), "");

		String regexp = regexpMap.get("prefix") +
				"(?<cashVal>" + regexpMap.get("cashVal") + 
				"(?<val>" + regexpMap.get("rewardVal") +
				"(?<percentVal>" + regexpMap.get("percentVal") + 
				regexpMap.get("unitspace") +
				"(?<units>" + regexpMap.get("units") + 
				"(?<rate>" + regexpMap.get("rate") +
				regexpMap.get("suffix");

		Pattern rewardPattern = Pattern.compile(regexp);
		Matcher match = rewardPattern.matcher(rewardString);
		while (match.find()) {
			if (match.group("val") != null && !match.group("val").isEmpty()) {
				reward.setRewardValue(Double.valueOf(match.group("val").replaceAll("\\s", "")));
			}
			
			if (match.group("percentVal") != null && !match.group("percentVal").isEmpty()) {
				reward.setRewardRate("%");
			} else if (match.group("rate") != null) {
				reward.setRewardRate(match.group("rate").trim());
			}
			
			if (match.group("cashVal") != null && !match.group("cashVal").isEmpty()) {
				reward.setRewardUnit("$");
			} else if (match.group("units") != null && !match.group("units").isEmpty()) {
				reward.setRewardUnit(match.group("units").trim());
			} else {
				reward.setRewardUnit("none");
			}
		}*/
		return Promise.asPromise(reward);
	}

	@Asynchronous
	public Promise<String> checkMerchKey(Promise<String> mKey, PortalProfile profile) {
		//String merchKey = mKey.get().toLowerCase().replaceAll(profile.getPortalRegexp().get("EXCLUSIONS"), "");
		String merchKey = mKey.get().toLowerCase();
		return Promise.asPromise(merchKey);
	}

}
