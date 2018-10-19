package com.apap.tugas1.service;

import com.apap.tugas1.model.*;
import com.apap.tugas1.repository.*;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService {
	@Autowired
	private PegawaiDB pegawaiDB;
	
	@Override
	public List<PegawaiModel> getAllPegawai() {
		return pegawaiDB.findAll();
	}
	
	@Override
	public PegawaiModel getPegawaiDetailByNip(String nip) {
		return pegawaiDB.findByNip(nip);
	}
	
	@Override
	public PegawaiModel getPegawaiDetailById(long id) {
		return pegawaiDB.findById(id);
	}
	
	@Override
	public void addPegawai(PegawaiModel pegawai) {
		pegawaiDB.save(pegawai);
	}
	
	@Override
	public int getGajiTerbesar(List<JabatanModel> jabatan, double persentaseTunjangan) {
		double gajiAwal = 0;
		for (int i = 0; i < jabatan.size(); i++) {
			double gajiCompared = 0;
			double gajiPokok = jabatan.get(i).getGajiPokok();
			gajiCompared = (gajiPokok * (persentaseTunjangan / 100)) + gajiPokok;
			if (gajiAwal < gajiCompared) {
				gajiAwal = gajiCompared;
			}
		}
		int gaji = (int) gajiAwal;
		return gaji;
	}
	
	@Override
	public PegawaiModel getPegawaiTertua(List<PegawaiModel> pegawai) {
		Collections.sort(pegawai);
		return pegawai.get(0);
	}
	
	@Override
	public PegawaiModel getPegawaiTermuda(List<PegawaiModel> pegawai) {
		Collections.sort(pegawai, Collections.reverseOrder());
		return pegawai.get(0);
	}
}
