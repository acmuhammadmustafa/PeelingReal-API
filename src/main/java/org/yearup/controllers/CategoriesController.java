package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.*;
import org.yearup.models.*;
import java.util.List;



@RestController // The annotations to make this a REST controller
@RequestMapping("/categories") // Annotation to make this controller the endpoint for the following url (http://localhost:8080/categories)
@CrossOrigin // enables cross-origin resource sharing only for this specific method (https://spring.io/guides/gs/rest-service-cors)


public class CategoriesController
{
    private CategoryDao categoryDao;
    private ProductDao productDao;


@Autowired    // an Autowired controller to inject the categoryDao and ProductDao

public CategoriesController(CategoryDao categoryDao, ProductDao productDao){
    this.categoryDao = categoryDao;
    this.productDao = productDao;
}

    @GetMapping("") // add the appropriate annotation for a get action
    @PreAuthorize("permitAll()")
    public List<Category> getAll() {
        try {
            return categoryDao.getAllCategories(); // find and return all categories
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "getALL() Error");
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public Category getById(@PathVariable int id)
    {
        try{
            var category = categoryDao.getById(id);
            if (category == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return category; // get the category by id
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"getById Error");
        }
    }

    @PreAuthorize("permitAll()") // the url to return all products in category 1 would look like this
    @GetMapping("/{categoryId}/products")// https://localhost:8080/categories/1/products
    public List<Product> getProductsById(@PathVariable int categoryId)
    {
        try {
            return productDao.listByCategoryId(categoryId); // get a list of product by categoryId
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "getProductsById() Error");
        }
    }

    @PostMapping // add annotation to call this method for a POST action
    @PreAuthorize("hasRole('ROLE_ADMIN')")// add annotation to ensure that only an ADMIN can call this function
    @ResponseStatus(HttpStatus.CREATED)
    public Category addCategory(@RequestBody Category category)
    {
        try {
            return categoryDao.create(category); // insert the category
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "addCategory() Error");
        }
    }
    @PutMapping("/{id}") // add annotation to call this method for a PUT (update) action - the url path must include the categoryId
    @PreAuthorize("hasRole('ROLE_ADMIN')") // add annotation to ensure that only an ADMIN can call this function
    public void updateCategory(@PathVariable int id, @RequestBody Category category)
    {
        try {
            categoryDao.update(id, category); // update the category by id
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "updateCategory() Error");
        }
    }


    @DeleteMapping("/{id}")// add annotation to call this method for a DELETE action - the url path must include the categoryId
    @PreAuthorize("hasRole('ROLE_ADMIN')")// add annotation to ensure that only an ADMIN can call this function
    @ResponseStatus(HttpStatus.NO_CONTENT) // Without this annotation, Spring would return "200 OK"
    public void deleteCategory(@PathVariable int id)
    {
        try{
            var category = categoryDao.getById(id);

            if(category == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);

            categoryDao.delete(id); // delete the category by id
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"deleteCategory() Error");
        }
    }
}
