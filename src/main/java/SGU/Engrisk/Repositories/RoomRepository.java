package SGU.Engrisk.Repositories;

import SGU.Engrisk.Models.Candidate;
import SGU.Engrisk.Models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}