package co.edu.usbcali.santiagoweb.domain.converter;

import co.edu.usbcali.santiagoweb.domain.enums.EstadoRelacion;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EstadoRelacionConverter extends ValorDbEnumConverter<co.edu.usbcali.santiagoweb.domain.enums.EstadoRelacion> {
    public EstadoRelacionConverter() {super(EstadoRelacion.class);
    }
}