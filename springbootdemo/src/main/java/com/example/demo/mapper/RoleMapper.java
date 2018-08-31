package com.example.demo.mapper;

import java.util.Set;

public interface RoleMapper {

	Set<String> findRoleByUserId(Long userId);

}
