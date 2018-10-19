package com.apap.tugas1.service;

import com.apap.tugas1.model.*;

import java.util.List;

public interface ProvinsiService {
	ProvinsiModel getProvinsiDetailById(long id);
	List<ProvinsiModel> getAllProvinsi();
}
