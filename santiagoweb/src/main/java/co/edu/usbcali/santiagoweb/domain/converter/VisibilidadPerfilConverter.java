package co.edu.usbcali.santiagoweb.domain.converter;


import co.edu.usbcali.santiagoweb.domain.enums.VisibilidadPerfil;
import jakarta.persistence.Converter;


@Converter(autoApply = true)
public class VisibilidadPerfilConverter extends ValorDbEnumConverter<VisibilidadPerfil> {
    public VisibilidadPerfilConverter() {super(VisibilidadPerfil.class);}
}
