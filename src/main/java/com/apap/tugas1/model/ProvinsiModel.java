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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.apap.tugas1.model.InstansiModel;

@Entity
@Table(name = "provinsi")
public class ProvinsiModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "nama", nullable = false)
	private String nama;
	
	@NotNull
	@Column(name = "presentase_tunjangan", nullable = false)
	private double presentaseTunjangan;
	
	@OneToMany(mappedBy = "provinsi", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private List<InstansiModel> instansiProvinsi;
	
	public ProvinsiModel() {
		
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
	
	public void setPresentaseTunjangan(double presentaseTunjangan) {
		this.presentaseTunjangan = presentaseTunjangan;
	}
	
	public double getPresentaseTunjangan() {
		return presentaseTunjangan;
	}
	
	public void setInstansiProvinsi(List<InstansiModel> instansiProvinsi) {
		this.instansiProvinsi = instansiProvinsi;
	}
	
	public List<InstansiModel> getInstansiProvinsi() {
		return instansiProvinsi;
	}
}
