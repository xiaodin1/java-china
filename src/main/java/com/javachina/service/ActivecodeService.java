package com.javachina.service;

import com.javachina.model.Activecode;

public interface ActivecodeService {
	
	Activecode getActivecode(String code, String type);
	
	String save(Long uid, String type);
	
	boolean useCode(Long code, String type);
	
}
