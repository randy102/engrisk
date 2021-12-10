package SGU.Engrisk.Repositories;

import SGU.Engrisk.Models.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    boolean existsByName(String name);
    boolean existsById(Long id);
    Optional<Exam> findByName(String name);
}