package com.apap.tugas1.controller;

import com.apap.tugas1.model.*;
import com.apap.tugas1.service.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PegawaiController {
	@Autowired
	private PegawaiService pegawaiService;

	@Autowired
	private InstansiService instansiService;
	
	@Autowired
	private ProvinsiService provinsiService;
	
	@Autowired
	private JabatanService jabatanService;
	
	@RequestMapping(value = "/pegawai", method = RequestMethod.GET)
	private String viewPegawaiByNip(String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiDetailByNip(nip);
		InstansiModel instansi = instansiService.getInstansiDetailById(pegawai.getInstansi().getId());
		ProvinsiModel provinsi = provinsiService.getProvinsiDetailById(instansi.getProvinsi().getId());
		int gaji = pegawaiService.getGajiTerbesar(pegawai.getJabatan(), provinsi.getPresentaseTunjangan());
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("gaji", gaji);
		return "view-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/cari", method = RequestMethod.GET)
	private String viewPegawai(Model model) {
		List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();
		List<InstansiModel> listInstansi = instansiService.getAllInstansi();
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		model.addAttribute("listProvinsi", listProvinsi);
		model.addAttribute("listInstansi", listInstansi);
		model.addAttribute("listJabatan", listJabatan);
		return "cari-pegawai";
	}
	
	@RequestMapping(value = "/cari", method = RequestMethod.GET)
	private List<PegawaiModel> viewPegawaiByQuery(
			@RequestParam long idProvinsi,
			@RequestParam long idInstansi,
			@RequestParam long idJabatan) {
		List<PegawaiModel> listPegawai = pegawaiService.getAllPegawai();
		List<PegawaiModel> listPegawaiQueried = new ArrayList<PegawaiModel>();
//		if (idProvinsi.isPresent() && idInstansi.isPresent() && idJabatan.isPresent()) {
//			InstansiModel instansi = instansiService.getInstansiDetailById(idInstansi.get());
//			JabatanModel jabatan = jabatanService.getJabatanDetailById(idJabatan.get());
//			List<PegawaiModel> listPegawaiQueried = pegawaiService.getAllPegawaiInInstansi(pegawaiService.getAllPegawaiInJabatan(listPegawai, jabatan), instansi);
//			listPegawai = listPegawaiQueried;
//		} else if (idProvinsi.isPresent() && idInstansi.isPresent() && idJabatan.isPresent()) {
//			
//		}
		if (idProvinsi > 0) {
			if (idInstansi > 0) {
				InstansiModel instansi = instansiService.getInstansiDetailById(idInstansi);
				if (idJabatan > 0) {
					JabatanModel jabatan = jabatanService.getJabatanDetailById(idJabatan);
					listPegawaiQueried = pegawaiService.getAllPegawaiInInstansi(pegawaiService.getAllPegawaiInJabatan(listPegawai, jabatan), instansi);	
				} else {
					listPegawaiQueried = pegawaiService.getAllPegawaiInInstansi(listPegawai, instansi);
				}	
			} else {
				if (idJabatan > 0) {
					JabatanModel jabatan = jabatanService.getJabatanDetailById(idJabatan);
					List<InstansiModel> listInstansiDiProvinsi = instansiService.getAllInstansiInProvinsi(provinsiService.getProvinsiDetailById(idProvinsi));
					List<PegawaiModel> listTemp = new ArrayList<PegawaiModel>();
					for (int i = 0; i < listInstansiDiProvinsi.size(); i++) {
						List<PegawaiModel> listPegawaiDiInstansi = listInstansiDiProvinsi.get(i).getPegawaiInstansi();
						listTemp.addAll(listPegawaiDiInstansi);
					}
					listPegawaiQueried = pegawaiService.getAllPegawaiInJabatan(listTemp, jabatan);
				}
			}
		} else if (idInstansi > 0) {
			InstansiModel instansi = instansiService.getInstansiDetailById(idInstansi);
			if (idJabatan > 0) {
				JabatanModel jabatan = jabatanService.getJabatanDetailById(idJabatan);
				listPegawaiQueried = pegawaiService.getAllPegawaiInInstansi(pegawaiService.getAllPegawaiInJabatan(listPegawai, jabatan), instansi);	
			} else {
				listPegawaiQueried = pegawaiService.getAllPegawaiInInstansi(listPegawai, instansi);
			}
		} else if (idJabatan > 0) {
			JabatanModel jabatan = jabatanService.getJabatanDetailById(idJabatan);
			listPegawaiQueried = pegawaiService.getAllPegawaiInJabatan(listPegawai, jabatan);
		}
		
		return listPegawaiQueried;
	}
	
	@RequestMapping(value = "/pegawai/tambah")
	private String add(Model model) {
		List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		model.addAttribute("pegawai", new PegawaiModel());
		model.addAttribute("listProvinsi", listProvinsi);
		model.addAttribute("listJabatan", listJabatan);
		return "add-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST)
	private String addPegawaiSubmit(
			PegawaiModel pegawai, 
			String nama, 
			String tempatLahir, 
			Date tanggalLahir, 
			String tahunMasuk, 
			long instansi, 
			long jabatan, 
			Model model) {
		InstansiModel instansiInput = instansiService.getInstansiDetailById(instansi);
		List<JabatanModel> jabatanInput = new ArrayList<JabatanModel>();
		jabatanInput.add(jabatanService.getJabatanDetailById(jabatan));	
		
		String idInstansi = Long.toString(instansi);
		
		String tglLahirGenerated = pegawaiService.generateDate(tanggalLahir);
		
		String nipIncomplete = idInstansi + tglLahirGenerated + tahunMasuk;
		
		String nip = nipIncomplete + pegawaiService.generateLastTwoDigits(nipIncomplete);
		
		pegawai.setNip(nip);
		pegawai.setNama(nama);
		pegawai.setTempatLahir(tempatLahir);
		pegawai.setTanggalLahir(tanggalLahir);
		pegawai.setTahunMasuk(tahunMasuk);
		pegawai.setInstansi(instansiInput);
		pegawai.setJabatan(jabatanInput);
		
		pegawaiService.addPegawai(pegawai);
		model.addAttribute("pegawai", pegawai);
		return "pegawai-added";
	}
	
	@RequestMapping(value = "/instansi", method = RequestMethod.GET)
	public @ResponseBody List<InstansiModel> findAllInstansi (@RequestParam(value = "provinsiId", required = true) Long provinsiId) {
	    ProvinsiModel provinsi = provinsiService.getProvinsiDetailById(provinsiId);
	    return provinsi.getInstansiProvinsi();
	}
	
	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.GET)
	private String updatePegawai(long idPegawai, Model model) {
		List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		PegawaiModel archive = pegawaiService.getPegawaiDetailById(idPegawai);
		model.addAttribute("pegawai", archive);
		model.addAttribute("listProvinsi", listProvinsi);
		model.addAttribute("listJabatan", listJabatan);
		return "update-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.POST)
	private String updatePegawaiSubmit(
			PegawaiModel pegawai,
			long id,
			String nama, 
			String tempatLahir, 
			Date tanggalLahir, 
			String tahunMasuk, 
			long instansi, 
			long jabatan, 
			Model model) {
		InstansiModel instansiInput = instansiService.getInstansiDetailById(instansi);
		JabatanModel jabatanInput = jabatanService.getJabatanDetailById(jabatan);
		
		String idInstansi = Long.toString(instansi);
		
		String tglLahirGenerated = pegawaiService.generateDate(tanggalLahir);
		
		String nipIncomplete = idInstansi + tglLahirGenerated + tahunMasuk;
		
		String nip = nipIncomplete + pegawaiService.generateLastTwoDigits(nipIncomplete);
		
		pegawai.setId(id);
		pegawai.setNip(nip);
		pegawai.setNama(nama);
		pegawai.setTempatLahir(tempatLahir);
		pegawai.setTanggalLahir(tanggalLahir);
		pegawai.setTahunMasuk(tahunMasuk);
		pegawai.setInstansi(instansiInput);
		pegawai.getJabatan().add(jabatanInput);
		
		pegawaiService.addPegawai(pegawai);
		model.addAttribute("pegawai", pegawai);
		return "pegawai-updated";
	}
	
	@RequestMapping(value = "/pegawai/termuda-tertua")
	private String cariTermudaTertua(@RequestParam(value="idInstansi") long id, Model model) {
		List<PegawaiModel> archive = instansiService.getInstansiDetailById(id).getPegawaiInstansi();
		PegawaiModel pegawaiMuda = pegawaiService.getPegawaiTermuda(archive);
		PegawaiModel pegawaiTua = pegawaiService.getPegawaiTertua(archive);
		model.addAttribute("pegawaiTermuda", pegawaiMuda);
		model.addAttribute("pegawaiTertua", pegawaiTua);
		return "termuda-tertua";
	}
}
