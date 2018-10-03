package com.vmt.clubDeportivo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No existe el recurso que se busca")
public class NotFoundException extends RuntimeException {

}
