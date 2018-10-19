package com.apap.tugas1.model;

import java.io.Serializable;
import java.util.List;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.apap.tugas1.model.PegawaiModel;

@Entity
@Table(name = "jabatan")
public class JabatanModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "nama", nullable = false)
	private String nama;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "deskripsi", nullable = false)
	private String deskripsi;
	
	@NotNull
	@Column(name = "gaji_pokok", nullable = false)
	private double gajiPokok;
	
	@ManyToMany(mappedBy = "jabatan")
    private List<PegawaiModel> pegawai;
	
	public JabatanModel() {
		
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
	public void setNama(String nama) {
		this.nama = nama;
	}
	
	public String getNama() {
		return nama;
	}
	
	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}
	
	public String getDeskripsi() {
		return deskripsi;
	}
	
	public void setGajiPokok(double gajiPokok) {
		this.gajiPokok = gajiPokok;
	}
	
	public double getGajiPokok() {
		return gajiPokok;
	}
	
	public void setPegawai(List<PegawaiModel> pegawai) {
		this.pegawai = pegawai;
	}
	
	public List<PegawaiModel> getPegawai() {
		return pegawai;
	}
}
