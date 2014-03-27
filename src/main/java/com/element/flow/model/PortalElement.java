package com.element.flow.model;

import java.util.Map;

import org.jsoup.nodes.Element;

public class PortalElement {

	private String baseElementTag;
	private Map<String, String> baseElementAttr;
	private String elementHtml;

	public PortalElement(String baseElementTag,
			Map<String, String> baseElementAttr, String elementHtml) {
		super();
		this.baseElementTag = baseElementTag;
		this.baseElementAttr = baseElementAttr;
		this.elementHtml = elementHtml;
	}

	public String getBaseElementTag() {
		return baseElementTag;
	}

	public void setBaseElementTag(String baseElementTag) {
		this.baseElementTag = baseElementTag;
	}

	public Map<String, String> getBaseElementAttr() {
		return baseElementAttr;
	}

	public void setBaseElementAttr(Map<String, String> baseElementAttr) {
		this.baseElementAttr = baseElementAttr;
	}

	public String getElementHtml() {
		return elementHtml;
	}

	public void setElementHtml(String elementHtml) {
		this.elementHtml = elementHtml;
	}

	@Override
	public String toString() {
		return "PortalElement [baseElementTag=" + baseElementTag
				+ ", baseElementAttr=" + baseElementAttr + ", elementHtml="
				+ elementHtml + "]";
	}

}
