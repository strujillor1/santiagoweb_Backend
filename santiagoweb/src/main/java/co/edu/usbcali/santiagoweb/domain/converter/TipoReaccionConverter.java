package co.edu.usbcali.santiagoweb.domain.converter;

import co.edu.usbcali.santiagoweb.domain.enums.TipoReaccion;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoReaccionConverter extends ValorDbEnumConverter<TipoReaccion> {
    public TipoReaccionConverter() {super(TipoReaccion.class);}
}