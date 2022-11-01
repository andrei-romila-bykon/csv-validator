# Requisitos para el validador de archivos

### Se necesitan validar todos los campos y obtener el resultado de las validaciones:

1. ID: - Obligatorio, debe ser numérico, mayor a cero
2. EMPRESA: - Opcional, pero si viene informado solo puede contener letras, números y espacios con un máximo de 50 caracteres y minimo 5
3. CONTACTO: - Opcional, pero si viene informado solo puede contener letras, números y espacios con un máximo de 50 caracteres y minimo 5
4. RFC: obligatorio, único no se puede repetir en el archivo, solo puede contener letras y números y la longitud es de 13 obligatorio
4. EMAIL: obligatorio, único y debe contener "@" y acabar con ".com", ".com.mx"
5. TELEFONO: opcional, pero si viene informado solo se deben aceptar números con longitud 9
6. EXTRANJERA: booleano, obligatorio, puede venir para valores verdaderos: "1", "true", "verdadero" - para valores falsos: "0", "false", "falso"

