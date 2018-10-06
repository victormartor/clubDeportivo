package com.vmt.clubDeportivo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error en la escritura del fichero")
public class FileException extends RuntimeException{

}
