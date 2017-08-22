package com.arief.fx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_pegawai")
public class Pegawai {

	
	
	@Id
	@Column(name="id_pegawai")
	private String idPegawai;
	
	
	@Column(name="nama_pegawai")
	private String namaPegawai;

	@Column(name="gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	
	
	public Pegawai() {
		
	}
	
	
	
	public Pegawai(String idPegawai, String namaPegawai, Gender gender) {
		this.idPegawai = idPegawai;
		this.namaPegawai = namaPegawai;
		this.gender = gender;
	}



	public String getIdPegawai() {
		return idPegawai;
	}

	public void setIdPegawai(String idPegawai) {
		this.idPegawai = idPegawai;
	}

	public String getNamaPegawai() {
		return namaPegawai;
	}

	public void setNamaPegawai(String namaPegawai) {
		this.namaPegawai = namaPegawai;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}



	@Override
	public String toString() {
		return "Pegawai [idPegawai=" + idPegawai + ", namaPegawai=" + namaPegawai + ", gender=" + gender + "]";
	}
	
	
	
	
	
}
