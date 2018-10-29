package com.apap.tugas1.service;

import com.apap.tugas1.model.*;
import com.apap.tugas1.repository.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InstansiServiceImpl implements InstansiService {
	@Autowired
	private InstansiDB instansiDB;
	
	@Override
	public InstansiModel getInstansiDetailById(long id) {
		return instansiDB.findById(id);
	}
	
	@Override
	public List<InstansiModel> getAllInstansiInProvinsi(ProvinsiModel provinsi) {
		return instansiDB.findByProvinsi(provinsi);
	}
	
	@Override
	public List<InstansiModel> getAllInstansi() {
		return instansiDB.findAll();
	}
}
