package com.project.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.dscatalog.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
