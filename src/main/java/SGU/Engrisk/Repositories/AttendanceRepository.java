package SGU.Engrisk.Repositories;

import SGU.Engrisk.Models.Attendance;
import SGU.Engrisk.Models.AttendanceID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, AttendanceID> {
    Optional<Attendance> findByCode(String code);

    Optional<Attendance> findById(AttendanceID id);

    @Query("SELECT a FROM Attendance a where a.exam.id=:examId and a.room.name=:roomName")
    Optional<Attendance> findAllByExamIdAndRoomName(@Param("examId") Long examId, @Param("roomName") String roomName);
}