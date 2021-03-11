package com.yuval.coupons.logic;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class CacheController {

	private Map<String, Object> datamap;

	public CacheController() {
		super();
		this.datamap = new HashMap<>();
	}

	public void put(String key, Object value) {
		this.datamap.put(key, value);
	}

	public Object get(String key) {
		return this.datamap.get(key);
	}

	@Override
	public String toString() {
		return "CacheController [datamap=" + datamap + "]";
	}

}
