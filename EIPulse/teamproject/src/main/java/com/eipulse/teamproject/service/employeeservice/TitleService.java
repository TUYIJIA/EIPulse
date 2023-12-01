package com.eipulse.teamproject.service.employeeservice;
import com.eipulse.teamproject.dto.employeedto.TitleDTO;
import com.eipulse.teamproject.entity.employee.Dept;
import com.eipulse.teamproject.entity.employee.Title;
import com.eipulse.teamproject.repository.employeerepository.DeptRepository;
import com.eipulse.teamproject.repository.employeerepository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TitleService {

    private TitleRepository titleRepository;
    private DeptRepository deptRepository;
    @Autowired
    public TitleService(TitleRepository titleRepository, DeptRepository deptRepository) {
        this.titleRepository = titleRepository;
        this.deptRepository = deptRepository;
    }

    // 新增
    public void addTitle(TitleDTO titleDTO){
        Dept dept = deptRepository.findById(titleDTO.getDeptId()).get();
        titleRepository.save(new Title(titleDTO.getTitleName(),dept));
    }

    // 查詢單筆
    public Title findById(Integer id){
        Optional<Title> optional = titleRepository.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }
    // 刪除
    public void deleteTitle(Integer id) {
        titleRepository.deleteById(id);
    }
    // 查詢全部職位
    public List<Title> findAllTitle(){
        return titleRepository.findAll();
    }

    // 更新修改職位
    public Title update(TitleDTO titleDTO){
        Title title= titleRepository.findById(titleDTO.getId()).orElseThrow(()-> new RuntimeException("abcccc"));
        System.out.println(title);
            return titleRepository.save(new Title(title.getId(),titleDTO.getTitleName(),title.getDept()));
    }
}
