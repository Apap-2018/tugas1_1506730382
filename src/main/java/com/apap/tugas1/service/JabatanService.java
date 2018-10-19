package com.apap.tugas1.service;

import com.apap.tugas1.model.*;

import java.util.List;

public interface JabatanService {
	void addJabatan(JabatanModel jabatan);
	void deleteJabatan(JabatanModel jabatan);
//	void updateJabatan(JabatanModel jabatan);
	List<JabatanModel> getAllJabatan();
	JabatanModel getJabatanDetailById(long id);
	int getJumlahPegawai(JabatanModel jabatan);
}
