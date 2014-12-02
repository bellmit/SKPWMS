package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skpw.bean.TSmsChoiceItem;

@Repository
public interface TSmsChoiceItemRepository extends JpaRepository<TSmsChoiceItem, String>{

}
