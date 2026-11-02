package ch.zli.m223.service;
import java.util.List;

import com.arjuna.ats.internal.jdbc.drivers.modifiers.list;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import ch.zli.m223.model.Tag;
import ch.zli.m223.repository.TagRepository;

@ApplicationScoped 
public class TagService {

    @Inject
    TagRepository tagRepository;

    public List<Tag> findAll(){
        return tagRepository.listAll();
    }
    
    public Tag findById(Long id){
        Tag tag = tagRepository.findById(id);

        if (tag == null){
            throw new NotFoundException("Tag not Found!");
        }
        return tag;
    }

     @Transactional
    public Tag create(String title) {
        Tag tag = new Tag();
        tag.setTitle(title);

        tagRepository.persist(tag);

        return tag;
    }
    
    @Transactional 
        public Tag update(Long id, String title) {
        Tag tag = findById(id);
        tag.setTitle(title);

        return tag;
    }

    @Transactional
    public void delete(Long id) {
        Tag tag = findById(id);
        tagRepository.delete(tag);
    }
}
