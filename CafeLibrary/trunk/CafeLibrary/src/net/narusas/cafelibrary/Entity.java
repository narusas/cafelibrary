package net.narusas.cafelibrary;

import java.io.Serializable;

public class Entity implements Serializable{
	long id;
	String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Entity(long id, String name) {
		this.id = id;
		this.name = name;
	}

	
	public String toString() {
		return name;
	}

}
