package SGU.Engrisk.DTO.Room;

import SGU.Engrisk.DTO.Candidate.ResponseExamRef;
import SGU.Engrisk.Models.Room;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class ResponseRoomDTO {
    private String name;
    private ResponseExamRef exam;
    public static ResponseRoomDTO convert(Room room) {
        ResponseRoomDTO res = (new ModelMapper()).map(room, ResponseRoomDTO.class);
        res.setExam(ResponseExamRef.convert(room.getExam()));
        return res;
    }
}
