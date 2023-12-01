package com.eipulse.teamproject.repository.salaryrepository;
import com.eipulse.teamproject.entity.salaryentity.SalaryDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SalaryDetailRepository extends JpaRepository<SalaryDetail, Integer> {
	
}
 