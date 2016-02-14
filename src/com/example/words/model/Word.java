package com.example.words.model;

public class Word {
	private String name;
	private String content;
	private int proficiency;
	private int id;
	
	
	public Word(int id, String name, String content, int proficiency) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
		this.proficiency = proficiency;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getProficiency() {
		return proficiency;
	}
	public void setProficiency(int proficiency) {
		this.proficiency = proficiency;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getId() {
		return id;
	}
}
