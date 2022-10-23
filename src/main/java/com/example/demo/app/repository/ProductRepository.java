package com.example.demo.app.repository;

import com.example.demo.app.model.entity.Product;
import com.example.demo.app.model.dto.projection.ProductListDto;
import com.example.demo.app.model.dto.projection.ProductPageDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query(nativeQuery = true, value = "select p.id as id, p.name as name, p.capacity as capacity, p.price as price,"
            + " p.type as type, pi.source as imageSource"
            + " from storedb.product p"
            + " join storedb.product_picture pi on p.id = pi.product_id")
    Set<ProductListDto> getProductsListWithPicture();

    @Query(nativeQuery = true, value = "SELECT p.id as id, p.name as name, p.capacity as capacity, p.price as price,"
            + " p.type as type, pi.source as source, s.count as quantity"
            + " from storedb.product p"
            + " join storedb.product_picture pi on p.id = pi.product_id"
            + " join storedb.storage s on p.id = s.product_id"
            + " where p.id = :id")
    Optional<ProductPageDto> getProductPage(@Param("id") Long id);


}
