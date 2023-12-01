package com.eipulse.teamproject.service.salaryservice;

import com.eipulse.teamproject.dto.salarydto.SubjectTypeDto;
import com.eipulse.teamproject.entity.salaryentity.SubjectType;
import com.eipulse.teamproject.repository.salaryrepository.SubjectTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class SubjectTypeService {

    private SubjectTypeRepository subjectRepo;

    @Autowired
    public SubjectTypeService(SubjectTypeRepository subjectRepo) {
        this.subjectRepo = subjectRepo;
    }

    // 建立&更新
    public SubjectTypeDto saveOrUpdate(SubjectTypeDto subjectDto) {

        SubjectType subject = new SubjectType(subjectDto);
        SubjectType result = subjectRepo.save(subject);

        return new SubjectTypeDto(result);
    }

    // 透過id找科目
    public SubjectTypeDto findById(Integer id) {
        Optional<SubjectType> optional = subjectRepo.findById(id);
        if (optional.isPresent()) {
            SubjectType subject = optional.get();
            return new SubjectTypeDto(subject);
        }
        return null;

    }

    // 找尋全部
    public List<SubjectTypeDto> findAll() {
        List<SubjectType> subjectList = subjectRepo.findAll();
        List<SubjectTypeDto> dtoList = new ArrayList<>();
        for (SubjectType s : subjectList) {

            SubjectTypeDto subjectDto = new SubjectTypeDto(s);
            dtoList.add(subjectDto);
        }
        return dtoList;
    }


    // 更新狀態(啟用/不啟用)
    public Boolean enabledSubject(Integer id, String newStatus) {
        SubjectType subjectType = subjectRepo.findById(id).get();
        String oldStatus = subjectType.getStatus();
        if (oldStatus.equals(newStatus)) {
            subjectType.setStatus(newStatus);
            subjectRepo.save(subjectType);
            return true;
        } else {
            return false;
        }
    }
    
    // 更改科目狀態
    public Boolean updateStatus(String newStatus,Integer id) {
    	Integer transStatus = subjectRepo.transStatus(newStatus, id);
    	if(transStatus>0) {
    		return true;
    	}
    	return  false;
    }

    
    //  找加項且啟用的科目
    public List<SubjectTypeDto> findPlus() {
        List<SubjectType> result = subjectRepo.findTypeIsP();
        List<SubjectTypeDto> dtoList = new ArrayList<>();
        
        for (SubjectType s : result) {

            SubjectTypeDto subjectDto = new SubjectTypeDto(s);
            dtoList.add(subjectDto);
        }
        return dtoList;
    }

    //  找減項且啟用的科目
    public List<SubjectTypeDto> findMinus() {
        List<SubjectType> result = subjectRepo.findTypeIsM();
        List<SubjectTypeDto> dtoList = new ArrayList<>();
       
        for (SubjectType s : result) {

            SubjectTypeDto subjectDto = new SubjectTypeDto(s);
            dtoList.add(subjectDto);
        }
        return dtoList;
    }
}

