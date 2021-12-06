package SGU.Engrisk.Repositories;

import SGU.Engrisk.Models.Candidate;
import SGU.Engrisk.Models.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    boolean existsByName(String name);

    Optional<Candidate> findByName(String name);
}