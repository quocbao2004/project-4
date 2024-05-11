package com.javaweb.repository;

import com.javaweb.entity.RoleEntity;
import com.javaweb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
	RoleEntity findOneByCode(String code);
}
