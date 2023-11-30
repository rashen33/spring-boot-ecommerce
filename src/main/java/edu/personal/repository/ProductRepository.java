package edu.personal.repository;

import edu.personal.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200") //Accept calls from web browser scripts for the origin
public interface ProductRepository extends JpaRepository<Product, Long> {
    //THis is a query method
    //SELECT * FROM product where category_id = ?
    //Spring Data REST -> api/products/search/findByCategoryId?id=2
    Page<Product> findByCategoryId(@Param("id") Long id, Pageable pageable);
}
