package SGU.Engrisk.Controllers.APIs;

import SGU.Engrisk.DTO.Room.ResponseRoomDTO;
import SGU.Engrisk.Services.RoomService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomRestController {
    @Autowired
    RoomService roomService;

    @GetMapping
    public List<ResponseRoomDTO> index(/*@RequestParam Optional<String> examType*/) {
//        if(examType.isPresent())
//            return roomService.getResponseAllRoomByExamType(examType.get());
        return roomService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseRoomDTO get(@PathVariable Long id) throws NotFoundException {
        return roomService.getResponse(id);
    }

//    @PostMapping
//    public ResponseRoomDTO create(@RequestBody CreateRoomDTO dto)  throws EntityExistsException, NotFoundException {
//        return roomService.create(dto);
//    }
//
//    @PutMapping
//    public ResponseRoomDTO update(@RequestBody UpdateRoomDTO dto) throws NotFoundException {
//        return roomService.update(dto);
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) throws NotFoundException {
//        roomService.delete(id);
//    }
}
