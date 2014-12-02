package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skpw.bean.TSmsTemplete;


@Repository
public interface TSmsTempleteRepository extends JpaRepository<TSmsTemplete, String>{

}
