package SGU.Engrisk.Services;

import SGU.Engrisk.DTO.Exam.CreateExamDTO;
import SGU.Engrisk.DTO.Exam.ResponseExamDTO;
import SGU.Engrisk.DTO.Exam.UpdateExamDTO;
import SGU.Engrisk.Models.Attendance;
import SGU.Engrisk.Models.Exam;
import SGU.Engrisk.Models.Room;
import SGU.Engrisk.Repositories.AttendanceRepository;
import SGU.Engrisk.Repositories.ExamRepository;
import SGU.Engrisk.Repositories.RoomRepository;
import SGU.Engrisk.Utils.FormatString;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamService {
    @Autowired
    ExamRepository examRepository;

    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<ResponseExamDTO> getAll() {
        List<Exam> exams = examRepository.findAll();

        List<ResponseExamDTO> resList = exams.stream().map(exam -> ResponseExamDTO.convert(exam)).collect(Collectors.toList());

        return resList;
    }

    public Exam get(long id) {
        return examRepository.findById(id).orElse(null);
    }

    public ResponseExamDTO getResponse(long id) throws NotFoundException {
        Exam exam = get(id);
        if (exam == null) {
            throw new NotFoundException("Not Existed");
        }
        return ResponseExamDTO.convert(exam);
    }

    public Exam get(String name) {
        return examRepository.findByName(name).orElse(null);
    }

    public ResponseExamDTO create(CreateExamDTO dto) throws Exception {
        //Check name
        if (dto.getName() == null || dto.getName().isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty");

        //Check price
        if (dto.getPrice() == null)
            dto.setPrice(0L);
        else if (dto.getPrice() < 0)
            throw new IllegalArgumentException("Price must be greater than 0");

        //Check date
        if (dto.getExam_date() == null || dto.getExam_date().before(new Date()))
            throw new IllegalArgumentException("Date cannot be null and must be after current date");

        //Check type
        if (dto.getType() == null)
            throw new IllegalArgumentException("Type cannot be null");

        //Check existed
        dto.setName(FormatString.TitleCase(dto.getName()));
        if (examRepository.existsByName(dto.getName()))
            throw new EntityExistsException(dto.getName() + " existed");

        Exam exam = modelMapper.map(dto, Exam.class);
        exam = examRepository.save(exam);

        return ResponseExamDTO.convert(exam);
    }

    public ResponseExamDTO update(UpdateExamDTO dto) throws Exception {
        //Check name
        if (dto.getName() == null || dto.getName().isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty");

        //Check price
        if (dto.getPrice() == null)
            dto.setPrice(0L);
        else if (dto.getPrice() < 0)
            throw new IllegalArgumentException("Price must be greater than 0");

        //Check date
        if (dto.getExam_date() == null || dto.getExam_date().before(new Date()))
            throw new IllegalArgumentException("Date cannot be null and must be after current date");

        //Check type
        if (dto.getType() == null || dto.getName().isEmpty())
            throw new IllegalArgumentException("Type cannot be null or empty");

        Exam exam = examRepository.getById(dto.getId());
        if (exam == null) {
            throw new NotFoundException("Not Existed");
        }
        if (examRepository.existsByName(dto.getName()) && get(dto.getName()).getId() != exam.getId()) {
            throw new EntityExistsException(dto.getName() + " Existed");
        }

        exam.setExam_date(dto.getExam_date());
        exam.setType(dto.getType());
        exam.setName(dto.getName());
        exam.setPrice(dto.getPrice());

        exam = examRepository.save(exam);
        return ResponseExamDTO.convert(exam);
    }

    public ResponseExamDTO rearrange(Long id) throws Exception {
        Exam exam = examRepository.getById(id);
        if (exam == null) {
            throw new NotFoundException("Not Existed");
        }
        double numberOfAttendances = (exam.getAttendances() == null ? 0 : exam.getAttendances().size());
        if(numberOfAttendances==0)
            throw new Exception("No attendance");
        int roomNo = (int) Math.ceil(numberOfAttendances / Room.CAPACITY);
        List<Room> rooms = exam.getRooms() == null ? (new ArrayList<Room>()) : exam.getRooms();
        for (int i = rooms.size(); i < roomNo; i++) {
            rooms.add(new Room(exam, exam.getType().name() + String.format("P%02d", i+1)));
        }
        exam.setRooms(rooms);
        exam = examRepository.save(exam);

        //Set room for Attendance
        int code=1;
        for (int i = 0; i < roomNo; i++) {
            Room currentRoom=rooms.get(i);
            int firstAttendanceIndex=i*Room.CAPACITY;
            int lastAttendanceIndex= (int) Math.min(firstAttendanceIndex+35,numberOfAttendances);
            for(int j =firstAttendanceIndex;j<lastAttendanceIndex;j++){
                Attendance currentAttendance=exam.getAttendances().get(j);
                currentAttendance.setCode(exam.getType().name() + String.format("%03d", code));
                currentAttendance.setRoom(currentRoom);
                attendanceRepository.save(currentAttendance);
                code++;
            }
        }

        return ResponseExamDTO.convert(exam);
    }

    public void delete(Long id) throws NotFoundException {
        if (!examRepository.existsById(id))
            throw new NotFoundException("Not existed id: " + id);
        examRepository.deleteById(id);
    }

    public boolean existsById(Long examId) {
        return examRepository.existsById(examId);
    }
}
