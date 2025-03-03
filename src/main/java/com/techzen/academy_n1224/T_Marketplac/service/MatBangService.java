package com.techzen.academy_n1224.T_Marketplac.service;

import com.techzen.academy_n1224.T_Marketplac.model.MatBang;
import com.techzen.academy_n1224.T_Marketplac.repository.IMatBangRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class MatBangService implements IMatBangService {

    IMatBangRepository matBangRepository;
    public List<?> getAll(){
        return matBangRepository.getAll();
    }
    public MatBang save(MatBang matBang){
        return matBangRepository.save(matBang);
    }
    public MatBang findById(int id){
        return matBangRepository.findById(id);
    }
    public MatBang delete(int id){
        return matBangRepository.delete(id);
    }
    public List<MatBang> find(Integer matMatBang, String tenMatBang, String diaChi, String dienTich, Double giaThue, String ngayBatDauThue, Integer loaiMatBang){
        return matBangRepository.find(matMatBang,tenMatBang,diaChi,dienTich,giaThue,ngayBatDauThue,loaiMatBang);
    }
}
