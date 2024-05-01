package com.friendsplushies.repository;

import com.friendsplushies.model.entity.Category;
import com.friendsplushies.repository.custom.CategoryRepositoryCustom;
import com.friendsplushies.util.cruds.repository.IRepository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, IRepository<Category>, CategoryRepositoryCustom {
}
