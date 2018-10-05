package com.vmt.clubDeportivo.service;

import com.vmt.clubDeportivo.model.Result;
import com.vmt.clubDeportivo.model.Trial;

public interface ResultService {

	Result create(Trial trial, Result result);
	
}
