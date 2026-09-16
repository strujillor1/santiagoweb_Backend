package co.edu.usbcali.santiagoweb.dto.response;


import java.time.LocalDateTime;

public record ObtenerUsuarioResponse(

        Integer id,
        String email,
        String username,
        LocalDateTime lastLogin

) {

}
