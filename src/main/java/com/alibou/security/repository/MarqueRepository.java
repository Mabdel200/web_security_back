package com.alibou.security.repository;

import com.alibou.security.entities.Marque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MarqueRepository extends JpaRepository<Marque, Long> {
}
