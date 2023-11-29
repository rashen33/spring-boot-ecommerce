package edu.personal.repository;

import edu.personal.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200") //Accept calls from web browser scripts for the origin

@RepositoryRestResource(collectionResourceRel = "productCategory", path = "product-category") //name of the json entry and /product-category
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
