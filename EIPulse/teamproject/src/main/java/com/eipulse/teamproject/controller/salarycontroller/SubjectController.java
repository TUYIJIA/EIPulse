package com.eipulse.teamproject.controller.salarycontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.eipulse.teamproject.dto.salarydto.SubjectTypeDto;
import com.eipulse.teamproject.service.salaryservice.SubjectTypeService;
import jakarta.transaction.Transactional;

@RestController
public class SubjectController {

	private SubjectTypeService subjectService;

	@Autowired
	public SubjectController(SubjectTypeService subjectService) {
		this.subjectService = subjectService;
	}

	// 新增 or 更新ok
	@PostMapping("/payroll/subject/edit")
	public ResponseEntity<?> editSubject(@RequestBody SubjectTypeDto subjectDto) {
		return new ResponseEntity<>(subjectService.saveOrUpdate(subjectDto), HttpStatus.OK);
	}

	// 透過Id找加項科目 ok
	@GetMapping("/payroll/subject/{subjectId}")
	public ResponseEntity<?> findById(@PathVariable Integer subjectId) {
		SubjectTypeDto result = subjectService.findById(subjectId);
		if (result != null) {
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("查無科目");
		}
	}

	// 查詢所有科目 ok
	@GetMapping("/payroll/subjects")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(subjectService.findAll(), HttpStatus.OK);
	}

	// 找加項且啟用的科目
	@GetMapping("/payroll/subject/plus")
	public ResponseEntity<?> findPlus() {

		return new ResponseEntity<>(subjectService.findPlus(), HttpStatus.OK);

	}

	// 找減項且啟用的科目
	@GetMapping("/payroll/subject/minus")
	public ResponseEntity<?> findMinus() {
		return new ResponseEntity<>(subjectService.findMinus(), HttpStatus.OK);
	}
	
	// 更改科目狀態
	@Transactional
	@PostMapping("/payroll/subject/status")
	public ResponseEntity<?> updateStatus(@RequestParam String status, @RequestParam Integer subjectId) {
		return new ResponseEntity<>(subjectService.updateStatus(status, subjectId), HttpStatus.OK);
	}
}
