package SGU.Engrisk.DTO.Exam;

import SGU.Engrisk.Models.Room;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class ResponseRoomRef {
    private Long id;

    private String name;
    public static ResponseRoomRef convert(Room room){
        ResponseRoomRef res = (new ModelMapper()).map(room, ResponseRoomRef.class);
        return res;
    }
}
