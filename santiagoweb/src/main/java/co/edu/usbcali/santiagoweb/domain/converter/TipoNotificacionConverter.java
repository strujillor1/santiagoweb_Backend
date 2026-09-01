package co.edu.usbcali.santiagoweb.domain.converter;

import co.edu.usbcali.santiagoweb.domain.enums.TipoNotificacion;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoNotificacionConverter extends ValorDbEnumConverter<TipoNotificacion> {
    public TipoNotificacionConverter() {super(TipoNotificacion.class);}
}
