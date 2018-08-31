package com.example.demo.service;

import java.util.Set;

public interface PermissionSerivce {

	Set<String> findPermissionByUserId(Long userId);

}
