package com.capstone.productservice.repositories;

import com.capstone.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Product save(Product product);

    Optional<Product> findProductById(Long id);

    List<Product> findProductByPriceBetween(Double low, Double high);

    List<Product> findProductByIsPrimeSpecial(Boolean val);
    List<Product> findProductByIsPrimeSpecialTrue();

    //List<Product> findAllOrderByIdDesc();
    List<Product> findAllByOrderByIdDesc();

    @Query("select p.name from Product p where p.id=:id1")
    String getProductNameFromId(@Param("id1") Long id);

    @Query("select c.name from Product p join Category c on p.category.id=c.id and p.id=?1")
    String getCategoryNameFromProductId(Long id);
}