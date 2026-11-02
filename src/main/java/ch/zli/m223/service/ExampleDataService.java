
package ch.zli.m223.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import ch.zli.m223.model.ApplicationUser;
import ch.zli.m223.model.Category;
import ch.zli.m223.model.Tag;
import ch.zli.m223.model.Entry;

import ch.zli.m223.repository.ApplicationUserRepository;
import ch.zli.m223.repository.CategoryRepository;
import ch.zli.m223.repository.TagRepository;
import ch.zli.m223.repository.EntryRepository;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ExampleDataService {

    @Inject
    CategoryRepository categoryRepository;

    @Inject
    TagRepository tagRepository;

    @Inject
    EntryRepository entryRepository;

    @Inject
    ApplicationUserRepository userRepository;

    @ConfigProperty(
        name = "punchclock.example-data.enabled",
        defaultValue = "false"
    )
    boolean enabled;

    // Wird beim Start von Quarkus aufgerufen
    void onStart(@Observes StartupEvent event) {
        if (enabled) {
            createExampleData();
            createExampleUser();
        }
    }

    // Testbenutzer erstellen
    @Transactional
    public void createExampleUser() {

        if (userRepository.count() > 0) {
            return;
        }

        ApplicationUser user = new ApplicationUser();

        user.setUsername("testuser");
        user.setPassword("Test1234!");
        user.setRole("User");

        userRepository.persist(user);
    }

    // Kategorien, Tags und Entries erstellen
    @Transactional
    public void createExampleData() {

        // Keine Daten überschreiben oder doppelt anlegen
        if (categoryRepository.count() > 0 ||
            tagRepository.count() > 0 ||
            entryRepository.count() > 0) {
            return;
        }

        // Kategorien erstellen
        Category arbeit = new Category();
        arbeit.setTitle("Arbeit");
        categoryRepository.persist(arbeit);

        Category schule = new Category();
        schule.setTitle("Schule");
        categoryRepository.persist(schule);

        Category freizeit = new Category();
        freizeit.setTitle("Freizeit");
        categoryRepository.persist(freizeit);

        // Tags erstellen
        Tag java = new Tag();
        java.setTitle("Java");
        tagRepository.persist(java);

        Tag quarkus = new Tag();
        quarkus.setTitle("Quarkus");
        tagRepository.persist(quarkus);

        Tag projekt = new Tag();
        projekt.setTitle("Projekt");
        tagRepository.persist(projekt);

        // Entry 1
        Entry entry1 = new Entry();

        entry1.setCheckIn(
            LocalDateTime.of(2026, 11, 2, 8, 0)
        );
        entry1.setCheckOut(
            LocalDateTime.of(2026, 11, 2, 12, 0)
        );

        entry1.setCategory(arbeit);
        entry1.setTags(
            new HashSet<>(Set.of(java, quarkus))
        );

        entryRepository.persist(entry1);

        // Entry 2
        Entry entry2 = new Entry();

        entry2.setCheckIn(
            LocalDateTime.of(2026, 11, 2, 13, 0)
        );
        entry2.setCheckOut(
            LocalDateTime.of(2026, 11, 2, 17, 0)
        );

        entry2.setCategory(schule);
        entry2.setTags(
            new HashSet<>(Set.of(projekt))
        );

        entryRepository.persist(entry2);

        // Entry 3
        Entry entry3 = new Entry();

        entry3.setCheckIn(
            LocalDateTime.of(2026, 11, 3, 9, 0)
        );
        entry3.setCheckOut(
            LocalDateTime.of(2026, 11, 3, 11, 0)
        );

        entry3.setCategory(freizeit);
        entry3.setTags(new HashSet<>());

        entryRepository.persist(entry3);
    }
}
