package SGU.Engrisk.Repositories;

import SGU.Engrisk.Models.Attendance;
import SGU.Engrisk.Models.AttendanceID;
import SGU.Engrisk.Models.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, AttendanceID> {
    Optional<Attendance> findByCode(String code);

}