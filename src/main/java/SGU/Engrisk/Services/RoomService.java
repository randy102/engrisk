package SGU.Engrisk.Services;


import SGU.Engrisk.DTO.Room.ResponseRoomDTO;
import SGU.Engrisk.DTO.Room.UpdateAttendanceResultDTO;
import SGU.Engrisk.Models.Room;
import SGU.Engrisk.Repositories.ExamRepository;
import SGU.Engrisk.Repositories.RoomRepository;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoomService roomService;

    @Autowired
    ExamRepository examRepository;

    @Autowired
    AttendanceService attendanceService;

    @Autowired
    ModelMapper modelMapper;

    public List<ResponseRoomDTO> getAll() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream().map(room -> ResponseRoomDTO.convert(room)).collect(Collectors.toList());
    }

    public Room get(long id) {
        return roomRepository.findById(id).orElse(null);
    }

    public ResponseRoomDTO getResponse(long id) throws NotFoundException {
        Room room = get(id);
        if (room == null) {
            throw new NotFoundException("Not Existed");
        }
        return ResponseRoomDTO.convert(room);
    }

    public Room get(String name) {
        return roomRepository.findByName(name).orElse(null);
    }

    public ResponseRoomDTO updateResults(Long id, List<UpdateAttendanceResultDTO> results) throws Exception {
        Room room = get(id);
        if (room == null) {
            throw new NotFoundException("Room not Existed");
        }
        for (UpdateAttendanceResultDTO result : results) {
            attendanceService.updateResult(
                    new SGU.Engrisk.DTO.Attendance.UpdateAttendanceResultDTO(
                            room.getExam().getId(),
                            result.getCandidateId(),
                            result.getListening(),
                            result.getSpeaking(),
                            result.getReading(),
                            result.getWriting())
            );
        }
        return getResponse(id);
    }
}
