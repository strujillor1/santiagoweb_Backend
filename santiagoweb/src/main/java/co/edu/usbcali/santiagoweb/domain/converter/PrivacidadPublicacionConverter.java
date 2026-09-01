package co.edu.usbcali.santiagoweb.domain.converter;

import co.edu.usbcali.santiagoweb.domain.enums.PrivacidadPublicacion;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PrivacidadPublicacionConverter extends ValorDbEnumConverter<PrivacidadPublicacion> {
    public PrivacidadPublicacionConverter() {super(PrivacidadPublicacion.class);}
}