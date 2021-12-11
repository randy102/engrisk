package SGU.Engrisk.Services;

import SGU.Engrisk.DTO.Attendance.CreateAttendanceDTO;
import SGU.Engrisk.DTO.Attendance.ResponseAttendanceDTO;
import SGU.Engrisk.DTO.Attendance.UpdateAttendanceDTO;
import SGU.Engrisk.Models.Attendance;
import SGU.Engrisk.Models.AttendanceID;
import SGU.Engrisk.Models.Exam;
import SGU.Engrisk.Repositories.AttendanceRepository;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceService {
    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    RoomService roomService;

    @Autowired
    ExamService examService;

    @Autowired
    CandidateService candidateService;

    @Autowired
    ModelMapper modelMapper;

    public List<ResponseAttendanceDTO> getAll() {
        List<Attendance> attendances = attendanceRepository.findAll();
        List<ResponseAttendanceDTO> resList = attendances.stream().map(attendance -> ResponseAttendanceDTO.convert(attendance)).collect(Collectors.toList());
        return resList;
    }

    public Attendance get(AttendanceID id) {
        return attendanceRepository.findById(id).orElse(null);
    }

    public Attendance getByCode(String code) {
        return attendanceRepository.findByCode(code).orElse(null);
    }

    public ResponseAttendanceDTO getResponse(AttendanceID id) throws NotFoundException {
        Attendance attendance = get(id);
        if (attendance == null) {
            throw new NotFoundException("Not Existed");
        }
        return ResponseAttendanceDTO.convert(attendance);
    }

    public ResponseAttendanceDTO getResponseByCode(String code) throws NotFoundException {
        Attendance attendance = getByCode(code);
        if (attendance == null) {
            throw new NotFoundException("Not Existed");
        }
        return ResponseAttendanceDTO.convert(attendance);
    }

    public ResponseAttendanceDTO create(CreateAttendanceDTO dto) throws Exception {
        //Check exam existed
        if (!examService.existsById(dto.getExamId()))
            throw new NotFoundException("Exam not found");

        //Check candidate existed
        if (!candidateService.existsById(dto.getCandidateId()))
            throw new NotFoundException("Candidate not found");

        //Check attendance existed
        if (attendanceRepository.existsById(new AttendanceID(dto.getCandidateId(), dto.getExamId())))
            throw new NotFoundException("Attendance existed");

        Exam exam = examService.get(dto.getExamId());

        if (exam.isClose())
            throw new IllegalArgumentException("Exam is closed");

        Attendance attendance = new Attendance(examService.get(dto.getExamId()), candidateService.get(dto.getCandidateId()));
        attendance = attendanceRepository.save(attendance);
        return ResponseAttendanceDTO.convert(attendance);
    }

    public ResponseAttendanceDTO update(UpdateAttendanceDTO dto) throws Exception {
        //Check exam existed
        if (!examService.existsById(dto.getExamId()))
            throw new NotFoundException("Exam not found");

        //Check candidate existed
        if (!candidateService.existsById(dto.getCandidateId()))
            throw new NotFoundException("Candidate not found");

        //Check attendance
        if (!attendanceRepository.existsById(new AttendanceID(dto.getCandidateId(), dto.getExamId())))
            throw new NotFoundException("Attendance not found");

        Attendance attendance = attendanceRepository.getById(new AttendanceID(dto.getCandidateId(), dto.getExamId()));
        if (attendance == null) {
            throw new NotFoundException("Not Existed");
        }
        attendance.setListening(dto.getListening());
        attendance.setSpeaking(dto.getSpeaking());
        attendance.setWriting(dto.getWriting());
        attendance.setReading(dto.getReading());

        attendance = attendanceRepository.save(attendance);
        return ResponseAttendanceDTO.convert(attendance);
    }

    public ResponseAttendanceDTO delete(AttendanceID id) throws NotFoundException {
        Attendance attendance = attendanceRepository.getById(new AttendanceID(id.getCandidateId(), id.getExamId()));
        if (attendance == null)
            throw new NotFoundException("Not existed id: " + id);
        ResponseAttendanceDTO res = ResponseAttendanceDTO.convert(attendance);
        attendanceRepository.deleteById(id);
        return res;
    }

    public List<ResponseAttendanceDTO> getResponseByExamId(Long examId) {
        List<ResponseAttendanceDTO> resList = getAll();
        List<ResponseAttendanceDTO> result = new ArrayList<ResponseAttendanceDTO>();
        for (ResponseAttendanceDTO res : resList)
            if (res.getId().getExamId() == examId)
                result.add(res);
        return result;
    }


    public List<ResponseAttendanceDTO> getResponseByExamIdAndRoomName(Long examId, String roomName) throws NotFoundException {
        Exam exam = examService.get(examId);
        if (exam == null)
            throw new NotFoundException("Exam id not found");
        return attendanceRepository.findAllByExamIdAndRoomName(examId, roomName).stream().map(attendance -> ResponseAttendanceDTO.convert(attendance)).collect(Collectors.toList());
    }

    public List<ResponseAttendanceDTO> getResponseByNameLike(String name) {
        List<Attendance> attendances = attendanceRepository.findAll();
        List<ResponseAttendanceDTO> result = new ArrayList<ResponseAttendanceDTO>();
        for (Attendance a : attendances)
            if (a.getCandidate().getName().contains(name))
                result.add(ResponseAttendanceDTO.convert(a));
        return result;
    }
}
