package com.arief.fx.services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.arief.fx.entity.Pegawai;

import java.util.List;


@Repository
public interface PegawaiRepository extends CrudRepository<Pegawai, String>{

	public List<Pegawai> findAll();
	
}
