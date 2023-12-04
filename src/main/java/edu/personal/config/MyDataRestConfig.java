package edu.personal.config;

import edu.personal.entity.Product;
import edu.personal.entity.ProductCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManger;

    @Autowired
    public MyDataRestConfig(EntityManager theEntityManager){
        entityManger = theEntityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);

        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};

        //disabling HTTP methods for Product : PUT, POST and DELETE
        config.getExposureConfiguration()
                .forDomainType(Product.class)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)))
                .withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)));

        //disabling HTTP methods for ProductCategory : PUT, POST and DELETE
        config.getExposureConfiguration()
                .forDomainType(ProductCategory.class)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)))
                .withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)));

        //calling this method to expose the ids
        exposeIds(config);
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        //getting all entity classes from the entity manager
        Set<EntityType<?>> entities = entityManger.getMetamodel().getEntities();

        //creating an array of the entity types
        List<Class> entityClasses = new ArrayList<>();

        //get the entity types for the entities
        for(EntityType tempeEntityType : entities){
            entityClasses.add(tempeEntityType.getJavaType());
        }

        //exposing the entity ids for the array of entity/domain types
        Class[] domianTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domianTypes);

    }
}
