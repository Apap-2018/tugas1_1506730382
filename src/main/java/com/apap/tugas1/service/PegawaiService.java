package com.apap.tugas1.service;

import com.apap.tugas1.model.*;

import java.util.List;

public interface PegawaiService {
	List<PegawaiModel> getAllPegawai();
	PegawaiModel getPegawaiDetailByNip(String nip);
	PegawaiModel getPegawaiDetailById(long id);
	int getGajiTerbesar(List<JabatanModel> jabatan, double persentaseTunjangan);
	void addPegawai(PegawaiModel pegawai);
	PegawaiModel getPegawaiTermuda(List<PegawaiModel> pegawai);
	PegawaiModel getPegawaiTertua(List<PegawaiModel> pegawai);
}
