package com.example.demo.app.repository;

import com.example.demo.app.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByIdIn(@Param(value = "id") List<Long> id);

    @Query(nativeQuery = true, value = "SELECT p.* FROM storedb.product p INNER JOIN id_list i ON i.id = p.id")
    List<Product> findAllByIdInWithDuplicates();

    @Modifying
    @Query(nativeQuery = true, value = "CREATE TEMPORARY TABLE id_list (id int4)")
    void createTmpTableForIds();

    @Modifying
    @Query(nativeQuery = true, value = "DROP TABLE id_list")
    void dropTmpTableWithIds();

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO id_list VALUES (:id)")
    void insertIdIntoTmpTable(@Param(value = "id") Long id);



}
