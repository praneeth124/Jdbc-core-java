package com.slokam.da.hc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.slokam.da.hc.entity.PreLine;

@Repository
public interface PreLineDAO extends JpaRepository<PreLine, Integer>{

	
}
