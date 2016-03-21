package com.javachina.service;

import java.util.List;
import java.util.Map;

import com.blade.jdbc.Page;
import com.blade.jdbc.QueryParam;
import com.javachina.model.User;

public interface UserService {
	
	User getUser(Long uid);
	
	User getUser(QueryParam queryParam);
	
	Map<String, Object> getUserDetail(Long uid);
	
	List<User> getUserList(QueryParam queryParam);
	
	Page<User> getPageList(QueryParam queryParam);
	
	boolean signup(String loginName, String passWord, String email);
	
	User signin(String loginName, String passWord);
	
	boolean delete(Long uid);
	
	boolean updateStatus(Long uid, Integer status);
	
	boolean resetPwd(String email);

	boolean active(Long id, Long uid);
	
}
