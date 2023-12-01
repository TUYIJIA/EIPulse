package com.eipulse.teamproject.service.employeeservice;


import com.eipulse.teamproject.dto.employeedto.DeptDTO;
import com.eipulse.teamproject.dto.employeedto.EmergencyDTO;
import com.eipulse.teamproject.entity.employee.Dept;
import com.eipulse.teamproject.entity.employee.Emergency;
import com.eipulse.teamproject.entity.employee.Employee;
import com.eipulse.teamproject.entity.view.AlldeptPepole;
import com.eipulse.teamproject.repository.employeerepository.EmergencyRepository;
import com.eipulse.teamproject.repository.employeerepository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmergencyService {

    private EmergencyRepository emergencyRepository;
    private EmployeeRepository employeeRepository;
    @Autowired
    public EmergencyService(EmergencyRepository emergencyRepository, EmployeeRepository employeeRepository) {
        this.emergencyRepository = emergencyRepository;
        this.employeeRepository = employeeRepository;
    }



    // add
    public void addEmergency(EmergencyDTO emergencyDTO) {
        Employee emp = employeeRepository.findById(emergencyDTO.getEmpId())
                .orElseThrow(() -> new NoSuchElementException("Employee with ID " + emergencyDTO.getEmpId() + " not found."));

        emergencyRepository.save(new Emergency(emergencyDTO.getEmergencyName(), emergencyDTO.getPhone(), emergencyDTO.getRelation(), emp));
    }

    // find once
    public Emergency findById(Integer id){
        Optional<Emergency> optional = emergencyRepository.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    // delete
    public void deleteEmergency(Integer id){emergencyRepository.deleteById(id);}

    // find all
    public List<Emergency> findAllEmergency(){
        return emergencyRepository.findAll();
    }

    // find all by empId
    public Page<EmergencyDTO> findEmergencyByEmpId( Integer id,Integer pageNumber){
        Pageable pgb = PageRequest.of(pageNumber - 1, 5);
        Page<EmergencyDTO> page = emergencyRepository.findEmergencyByEmpId(id, pgb);
        return page;
    }

    // update(緊急聯絡人姓名、電話、關係)
    public Emergency updateEmergency(EmergencyDTO dto) {
        Emergency oldData = emergencyRepository.findById(dto.getEmergencyId())
                .orElseThrow(() -> new RuntimeException("Emergency with ID  not found."));

        oldData.setEmergencyName(dto.getEmergencyName());
        oldData.setPhone(dto.getPhone());
        oldData.setRelation(dto.getRelation());

        return emergencyRepository.save(oldData);
    }
    // 分頁功能
    // select all 分頁功能
    public Page<EmergencyDTO> findByPage(Integer pageNumber) {
        Pageable pgb = PageRequest.of(pageNumber - 1, 5, Sort.Direction.ASC, "emergencyId");
        Page<Emergency> page =emergencyRepository.findAll(pgb);
        Page<EmergencyDTO> result = page.map(emergency -> new EmergencyDTO(emergency));
        return result;
    }
}
