package SGU.Engrisk.Repositories;

import SGU.Engrisk.Models.Attendance;
import SGU.Engrisk.Models.AttendanceID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, AttendanceID> {
    Optional<Attendance> findByCode(String code);

    Optional<Attendance> findById(AttendanceID id);

    List<Attendance> findAllByRoomId(Long roomId);

    @Query("SELECT a FROM Attendance a where a.candidate.name=:name and a.candidate.phone=:phone")
    List<Attendance> findByCandidateNameAndPhone(@Param("name") String name, @Param("phone") String phone);

    @Query("SELECT a FROM Attendance a where a.exam.name=:exam and a.code=:code")
    Optional<Attendance> findByCodeAndExam(@Param("code") String code, @Param("exam") String exam);

    @Query("SELECT a FROM Attendance a where a.exam.id=:examId and a.room.name=:roomName")
    Optional<Attendance> findAllByExamIdAndRoomName(@Param("examId") Long examId, @Param("roomName") String roomName);
}