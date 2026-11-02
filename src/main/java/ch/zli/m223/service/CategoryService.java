package ch.zli.m223.service;
import java.util.List;

import com.arjuna.ats.internal.jdbc.drivers.modifiers.list;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import ch.zli.m223.model.Category;
import ch.zli.m223.repository.CategoryRepository;

@ApplicationScoped 
public class CategoryService {

    @Inject
    CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.listAll();
    }
    
    public Category findById(Long id){
        Category category = categoryRepository.findById(id);

        if (category == null){
            throw new NotFoundException("Category not Found!");
        }
        return category;
    }

     @Transactional
    public Category create(String title) {
        Category category = new Category();
        category.setTitle(title);

        categoryRepository.persist(category);

        return category;
    }
    
    @Transactional 
        public Category update(Long id, String title) {
        Category category = findById(id);
        category.setTitle(title);

        return category;
    }

    @Transactional
    public void delete(Long id) {
        Category category = findById(id);
        categoryRepository.delete(category);
    }
}
