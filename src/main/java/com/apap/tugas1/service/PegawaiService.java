package com.apap.tugas1.service;

import com.apap.tugas1.model.*;

import java.util.List;
import java.sql.Date;

public interface PegawaiService {
	List<PegawaiModel> getAllPegawai();
	List<PegawaiModel> getAllPegawaiInInstansi(List<PegawaiModel> listPegawai, InstansiModel instansi);
	List<PegawaiModel> getAllPegawaiInJabatan(List<PegawaiModel> listPegawai, JabatanModel jabatan);
	PegawaiModel getPegawaiDetailByNip(String nip);
	PegawaiModel getPegawaiDetailById(long id);
	int getGajiTerbesar(List<JabatanModel> jabatan, double persentaseTunjangan);
	void addPegawai(PegawaiModel pegawai);
	PegawaiModel getPegawaiTermuda(List<PegawaiModel> pegawai);
	PegawaiModel getPegawaiTertua(List<PegawaiModel> pegawai);
	String generateDate(Date tanggalLahir);
	String generateLastTwoDigits(String digits);
}
