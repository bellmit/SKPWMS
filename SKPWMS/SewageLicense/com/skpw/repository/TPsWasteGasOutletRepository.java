package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.skpw.bean.TPsWasteGasOutlet;

@Repository
public interface TPsWasteGasOutletRepository extends JpaRepository<TPsWasteGasOutlet, String>,JpaSpecificationExecutor<TPsWasteGasOutlet>{

}
