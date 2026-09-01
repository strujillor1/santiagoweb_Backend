package co.edu.usbcali.santiagoweb.domain.converter;

import co.edu.usbcali.santiagoweb.domain.enums.TipoMedio;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoMedioConverter extends ValorDbEnumConverter<TipoMedio> {
    public TipoMedioConverter() {super(TipoMedio.class);}
}
