package co.edu.usbcali.santiagoweb.domain.converter;

import co.edu.usbcali.santiagoweb.domain.enums.EstadoDenuncia;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EstadoDenunciaConverter extends ValorDbEnumConverter<EstadoDenuncia> {
    public EstadoDenunciaConverter() {super(EstadoDenuncia.class);
    }
}