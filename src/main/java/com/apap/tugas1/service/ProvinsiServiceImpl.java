package com.apap.tugas1.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas1.model.*;
import com.apap.tugas1.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProvinsiServiceImpl implements ProvinsiService {
	@Autowired
	private ProvinsiDB provinsiDB;
	
	@Override
	public ProvinsiModel getProvinsiDetailById(long id) {
		return provinsiDB.findById(id);
	}
	
	@Override
	public List<ProvinsiModel> getAllProvinsi() {
		return provinsiDB.findAll();
	}
}
