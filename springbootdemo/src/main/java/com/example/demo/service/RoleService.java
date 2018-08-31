package com.example.demo.service;

import java.util.Set;

public interface RoleService {
	Set<String> findRoleByUserId(Long userId);	
}
