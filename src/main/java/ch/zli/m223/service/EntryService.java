package ch.zli.m223.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import ch.zli.m223.model.Entry;
import ch.zli.m223.repository.EntryRepository;
import io.quarkus.security.identity.SecurityIdentity;

@ApplicationScoped  
public class EntryService {
    @Inject
    private EntryRepository entryRepository;
    @Inject
    EntryService entryService;

    @Inject
    SecurityIdentity identity;

    @Transactional
    public Entry createEntry(Entry entry, Long categoryId, List<Long> tagIds, String username) {
        entryRepository.persist(entry);
        return entry;
    }

    public List<Entry> findAll() {
        return entryRepository.listAll();
    }

    @Transactional
    public boolean deleteEntry(Long id) {
        return entryRepository.deleteById(id);
    }

    @Transactional
    public Entry updateEntry(Long id, Entry updatedEntry, Long categoryId, List<Long> tagIds){
        Entry existingEntry = entryRepository.findById(id);

        if (existingEntry == null){
            return null;
        }

        existingEntry.setCheckIn(updatedEntry.getCheckIn());
        existingEntry.setCheckOut(updatedEntry.getCheckOut());

        return existingEntry;
    }
}
