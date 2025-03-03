package com.techzen.academy_n1224.T_Marketplac.repository;

import com.techzen.academy_n1224.T_Marketplac.model.MatBang;

import java.sql.Date;
import java.util.List;

public interface IMatBangRepository {
    public List<?> getAll();

    public MatBang save(MatBang matBang);

    public MatBang findById(int id);

    public MatBang delete(int id);

    public List<MatBang> find(Integer matMatBang, String tenMatBang, String diaChi, String dienTich, Double giaThue, String ngayBatDauThue, Integer loaiMatBang);
}
