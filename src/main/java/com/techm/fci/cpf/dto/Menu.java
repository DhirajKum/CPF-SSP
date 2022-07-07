package com.techm.fci.cpf.dto;

import java.io.Serializable;
import java.util.List;

public class Menu implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String label;
	private String url;
	private String text;
	private List<Menu> subMenus;

	public Menu() {

	}

	public Menu(String label) {
		super();
		this.label = label;
		this.text = label;
		this.url = "#";
	}

	public Menu(String label, String text) {
		super();
		this.label = label;
		this.text = text;
		this.url = "#";
	}

	public Menu(String label, String text, String url) {
		super();
		this.label = label;
		this.url = url;
		this.text = text;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Menu> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<Menu> subMenus) {
		this.subMenus = subMenus;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
