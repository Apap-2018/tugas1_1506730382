package com.apap.tugas1.service;

import com.apap.tugas1.model.*;
import com.apap.tugas1.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JabatanServiceImpl implements JabatanService {
	@Autowired
	private JabatanDB jabatanDB;
	
	@Override
	public void addJabatan(JabatanModel jabatan) {
		jabatanDB.save(jabatan);
	}
	
	@Override
	public void deleteJabatan(JabatanModel jabatan) {
		jabatanDB.delete(jabatan);
	}
	
//	@Override
//	public void updateJabatan(JabatanModel jabatan) {
//		jabatanDB.
//	}
	
	@Override
	public List<JabatanModel> getAllJabatan() {
		return jabatanDB.findAll();
	}
	
	@Override
	public JabatanModel getJabatanDetailById(long id) {
		return jabatanDB.findById(id);
	}
	
	@Override
	public int getJumlahPegawai(JabatanModel jabatan) {
		return jabatan.getPegawai().size();
	}
}