package SGU.Engrisk.Controllers.APIs;

import SGU.Engrisk.DTO.ErrorDTO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
class GlobalDefaultExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    ErrorDTO defaultErrorHandler(Exception e) throws Exception {
        return new ErrorDTO(e.getMessage());
    }
}