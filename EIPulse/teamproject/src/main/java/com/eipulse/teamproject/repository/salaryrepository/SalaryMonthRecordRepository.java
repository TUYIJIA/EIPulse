package com.eipulse.teamproject.repository.salaryrepository;
import com.eipulse.teamproject.entity.salaryentity.SalaryMonthRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface SalaryMonthRecordRepository extends JpaRepository<SalaryMonthRecord, Integer> {
	
	// 員工編號找歷史月紀錄/明細 for 薪資明細
	@Query(value = "select * from salary_month_record where emp_id = :empId and sl_Year = :year and sl_Month = :month", nativeQuery = true)
	SalaryMonthRecord findByEmpId(@Param(value="empId") Integer empId,@Param(value="year") Integer year,@Param(value="month")Integer month);
	
	//透年月找
	@Query(value = "select * from salary_month_record where sl_Year = :year and sl_Month = :month", nativeQuery = true)
	 List<SalaryMonthRecord> findBySlYearAndSlMonth(@Param(value="year") Integer year,@Param(value="month")Integer month);
}