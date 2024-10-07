package technologia.eduflex.exceptions;


import org.springframework.http.HttpStatus;

public class UnauthorizeException  extends RuntimeException{

    private HttpStatus status = HttpStatus.UNAUTHORIZED;
    private String message;

}
