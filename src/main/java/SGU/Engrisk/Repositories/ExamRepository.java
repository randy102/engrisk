package SGU.Engrisk.Repositories;

import SGU.Engrisk.Models.Candidate;
import SGU.Engrisk.Models.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

}