package com.example.demo.app.repository;

import com.example.demo.app.model.entity.ProductPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPictureRepository extends JpaRepository<ProductPicture, Integer> {
}
