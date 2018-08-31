package com.example.demo.mapper;

import java.util.Set;

public interface PermissionMapper {

	Set<String> findPermissionByUserId(Long userId);
}
