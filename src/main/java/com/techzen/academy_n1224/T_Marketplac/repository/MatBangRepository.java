package com.techzen.academy_n1224.T_Marketplac.repository;

import com.techzen.academy_n1224.T_Marketplac.model.MatBang;

import com.techzen.academy_n1224.employees.en.ApiException;
import com.techzen.academy_n1224.employees.en.ErrorCode;
import com.techzen.academy_n1224.employees.model.Employee;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MatBangRepository implements IMatBangRepository {

    public List<MatBang> find(Integer matMatBang, String tenMatBang, String diaChi, String dienTich, Double giaThue, String ngayBatDauThue, Integer loaiMatBang) {
        List<MatBang> matBangs = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM matbang WHERE 1=1");

        if (matMatBang != null) {
            sql.append(" AND id_matbang = ?");
        }
        if (tenMatBang != null && !tenMatBang.isEmpty()) {
            sql.append(" AND name LIKE ?");
        }
        if (diaChi != null && !diaChi.isEmpty()) {
            sql.append(" AND diachi LIKE ?");
        }
        if (dienTich != null && !dienTich.isEmpty()) {
            sql.append(" AND dientich = ?"); // Sử dụng = thay vì BETWEEN
        }
        if (giaThue != null) {
            sql.append(" AND giathue = ?"); // Sử dụng = thay vì BETWEEN
        }
        if (ngayBatDauThue != null && !ngayBatDauThue.isEmpty()) {
            sql.append(" AND ngaybatdauthue = ?"); // Sử dụng = thay vì BETWEEN
        }
        if (loaiMatBang != null) {
            sql.append(" AND id_loaimatbang = ?");
        }

        try (PreparedStatement ps = BaseRepository.getConnection().prepareStatement(sql.toString())) {
            int parameterIndex = 1;

            if (matMatBang != null) {
                ps.setInt(parameterIndex++, matMatBang);
            }
            if (tenMatBang != null && !tenMatBang.isEmpty()) {
                ps.setString(parameterIndex++, "%" + tenMatBang + "%");
            }
            if (diaChi != null && !diaChi.isEmpty()) {
                ps.setString(parameterIndex++, "%" + diaChi + "%");
            }
            if (dienTich != null && !dienTich.isEmpty()) {
                ps.setString(parameterIndex++, dienTich);
            }
            if (giaThue != null) {
                ps.setDouble(parameterIndex++, giaThue);
            }
            if (ngayBatDauThue != null && !ngayBatDauThue.isEmpty()) {
                ps.setString(parameterIndex++, ngayBatDauThue);
            }
            if (loaiMatBang != null) {
                ps.setInt(parameterIndex++, loaiMatBang);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                matBangs.add(MatBang.builder()
                        .maMatBang(rs.getInt("id_matbang"))
                        .tenMatBang(rs.getString("name"))
                        .diaChi(rs.getString("diachi"))
                        .dienTich(rs.getString("dientich"))
                        .giaThue(rs.getDouble("giathue"))
                        .ngayBatDauThue(rs.getString("ngaybatdauthue"))
                        .loaiMatBang(rs.getInt("id_loaimatbang"))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return matBangs;
    }

    public MatBang findById(int id) {
        try {
            PreparedStatement ps = BaseRepository.getConnection().prepareStatement("SELECT * FROM matbang WHERE id_matbang = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return MatBang.builder()
                        .maMatBang(rs.getInt("id_matbang"))
                        .tenMatBang(rs.getString("name"))
                        .diaChi(rs.getString("diachi"))
                        .dienTich(rs.getString("dientich"))
                        .giaThue(rs.getDouble("giathue"))
                        .ngayBatDauThue(rs.getString("ngaybatdauthue"))
                        .loaiMatBang(rs.getInt("id_loaimatbang"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new ApiException(ErrorCode.MattBang_Not_Exist);
    }

    public List<?> getAll() {
        List<MatBang> matBang = new ArrayList<>();
        try {
            PreparedStatement ps = BaseRepository.getConnection().prepareStatement("select mb.id_matbang,mb.name,mb.diachi,mb.dientich,mb.giathue,mb.ngaybatdauthue, l.id_loaimatbang from matbang mb join loaimatbang l on l.id_loaimatbang = mb.id_loaimatbang");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                matBang.add(MatBang.builder()
                        .maMatBang(rs.getInt("id_matbang"))
                        .tenMatBang(rs.getString("name"))
                        .diaChi(rs.getString("diachi"))
                        .dienTich(rs.getString("dientich"))
                        .giaThue(rs.getDouble("giathue"))
                        .ngayBatDauThue(rs.getString("ngaybatdauthue"))
                        .loaiMatBang(rs.getInt("id_loaimatbang"))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return matBang;
    }


    public MatBang save(MatBang matBang) {
        try {
            PreparedStatement ps = BaseRepository.getConnection().prepareStatement("INSERT INTO matbang (name,diachi,dientich,giathue,ngaybatdauthue,id_loaimatbang) values(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, matBang.getTenMatBang());
            ps.setString(2, matBang.getDiaChi());
            ps.setString(3, matBang.getDienTich());
            ps.setDouble(4, matBang.getGiaThue());
            ps.setString(5,matBang.getNgayBatDauThue());
            ps.setInt(6, matBang.getLoaiMatBang());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                matBang.setMaMatBang(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new ApiException(ErrorCode.MattBang_Not_Exist);
    }

    public MatBang delete(int id) {
        MatBang matBang = findById(id);
        if (matBang != null) {
            try {
                PreparedStatement ps = BaseRepository.getConnection().prepareStatement("delete from matbang where id_matbang = ?");
                ps.setInt(1, id);
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return matBang;
        }
        throw new ApiException(ErrorCode.MattBang_Not_Exist);
    }

}
