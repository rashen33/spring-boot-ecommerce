package edu.personal.repository;

import edu.personal.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200") //Accept calls from web browser scripts for the origin
public interface ProductRepository extends JpaRepository<Product, Long> {
}
