package co.edu.usbcali.santiagoweb.dto.request;

public record CrearUsuarioRequest(
        String email,
        String username,
        String passwordHash
) {
}