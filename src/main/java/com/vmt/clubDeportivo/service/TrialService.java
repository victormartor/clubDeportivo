package com.vmt.clubDeportivo.service;

import com.vmt.clubDeportivo.model.Result;
import com.vmt.clubDeportivo.model.Trial;

public interface TrialService {

	Trial findById(Integer idTrial);
	
	Trial create(Trial trial);
	
	Result insertResult(Integer idTrial, Result result);
	
}
