package SGU.Engrisk.Services;


import SGU.Engrisk.DTO.Room.ResponseRoomDTO;
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

//    public Room get(String name) {
//        return roomRepository.findByName(name).orElse(null);
//    }
//
//    public ResponseRoomDTO create(CreateRoomDTO dto) throws EntityExistsException, NotFoundException {
//        dto.setName(FormatString.TitleCase(dto.getName()));
//
//        Exam exam = examRepository.getById(dto.getExamId());
//
//        if (exam == null) {
//            throw new NotFoundException("Exam Not Found");
//        }
//
//        if (roomRepository.existsByName(dto.getName())) {
//            throw new EntityExistsException(dto.getName() + " Existed");
//        }
//
//        Room room = modelMapper.map(dto, Room.class);
//        room.setExam(exam);
//        room = roomRepository.save(room);
//
//        return ResponseRoomDTO.convert(room);
//    }

//    public ResponseRoomDTO update(UpdateRoomDTO dto) throws NotFoundException {
//        if (!roomRepository.existsById(dto.getId())) {
//            throw new NotFoundException("Not Existed");
//        }
//        dto.setName(FormatString.TitleCase(dto.getName()));
//
//        Exam exam = examRepository.getById(dto.getExamId());
//
//        if (exam == null) {
//            throw new NotFoundException("Exam Not Found");
//        }
//
//        if (roomRepository.existsByName(dto.getName()) && get(dto.getName()).getId() != dto.getId()) {
//            throw new EntityExistsException(dto.getName() + " Existed");
//        }
//
//        Room room = modelMapper.map(dto, Room.class);
//        room.setExam(exam);
//        room = roomRepository.save(room);
//
//        return ResponseRoomDTO.convert(room);
//    }

//    public void delete(Long id) throws NotFoundException {
//        if (!roomRepository.existsById(id))
//            throw new NotFoundException("Not existed id: " + id);
//        roomRepository.deleteById(id);
//    }

//    public Room arrange(Attendance attendance) throws NotFoundException {
//        Exam exam = attendance.getExam();
//        List<Room> rooms = getAllRoomByExamType(exam.getType());
//        double numberOfAttendances = (exam.getAttendances() == null ? 0 : exam.getAttendances().size()) + 1;
//        int roomNo = (int) Math.ceil(numberOfAttendances / Room.CAPACITY);
//        if (rooms.size() < roomNo) {
//            Room room = new Room(exam, exam.getType().name() + String.format("P%02d", roomNo));
////            exam.getRooms().add(room);
////            examRepository.save(exam);
//            return roomRepository.save(room);
//        }
//        return rooms.get(roomNo - 1);
//    }
//
//    public List<Room> getAllRoomByExamType(ExamType type) {
//        List<Exam> exams = examRepository.findAll();
//        List<Room> results = new ArrayList<Room>();
//        for (Exam e : exams)
//            if (e.getType() == type && e.getRooms() != null)
//                results.addAll(e.getRooms());
//        return results;
//    }
//
//    public List<ResponseRoomDTO> getResponseAllRoomByExamType(String type) {
//        List<Room> rooms = getAllRoomByExamType(ExamType.valueOf(type));
//        return rooms.stream().map(room -> ResponseRoomDTO.convert(room)).collect(Collectors.toList());
//    }

}
