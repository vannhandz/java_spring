package com.techzen.academy_n1224.nv.nhanvien;

import com.techzen.academy_n1224.nv.en.ApiResponse;
import com.techzen.academy_n1224.nv.en.ApiException;
import com.techzen.academy_n1224.nv.en.ErrorCode;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class NhanVienController {

    private List<NhanVien> nhanviens = new ArrayList<>(
            Arrays.asList(
                    new NhanVien(1,"van nhan", LocalDate.of(2003,10,3),"nam",5000.000,"0762605901"),
                    new NhanVien(2,"anh tu", LocalDate.of(2003,10,20),"nam",5000.000,"0762605902"),
                    new NhanVien(3,"van tuan", LocalDate.of(2003,6,19),"nam",5000.000,"0762605903")

            )
    );
    @GetMapping("/nhanvien")
    public ResponseEntity<List<NhanVien>> getAllNhanvien() {
        return ResponseEntity.ok(nhanviens);
    }

    // ưu tiên dùng
    @GetMapping("/nhanvien/{id}")
    public ResponseEntity<ApiResponse<NhanVien>> getNhanvien(@PathVariable("id") int id) {

        for (NhanVien nv : nhanviens) {
            if (nv.getId()==id) {
                return ResponseEntity.ok(ApiResponse.<NhanVien>builder().data(nv).build());
            }
        }
        throw new ApiException(ErrorCode.NhanVien_NOT_EXIST);
    }

    @PostMapping("/nhanvien/add")
    public ResponseEntity<ApiResponse<NhanVien>> addNhanvien(@RequestBody NhanVien nhanvien) {
        nhanvien.setId(nhanviens.size()+1);
        nhanviens.add(nhanvien);
        return ResponseEntity.ok(ApiResponse.<NhanVien>builder().data(nhanvien).build());
    }

    @PutMapping("/nhanvien/upd/id={id}")
        public ResponseEntity<ApiResponse<NhanVien>> updNhanvien(@RequestBody NhanVien nhanvien,
                                                      @PathVariable("id") int id) {
        for (NhanVien nv : nhanviens) {
            if (nv.getId()==id) {
                nv.setName(nhanvien.getName());
                nv.setBỉth(nhanvien.getBỉth());
                nv.setGender(nhanvien.getGender());
                nv.setSalary(nhanvien.getSalary());
                nv.setPhone(nhanvien.getPhone());
                return ResponseEntity.ok( ApiResponse.<NhanVien>builder().data(nhanvien).build());
            }
        }
        throw new ApiException(ErrorCode.NhanVien_NOT_EXIST);
    }

    @DeleteMapping("/nhanvien/del/id={id}")
    public ResponseEntity<ApiResponse<NhanVien>> deleteNhanvien(@PathVariable("id") int id) {
        for (NhanVien nv : nhanviens) {
            if (nv.getId()==id) {
                nhanviens.remove(nv);
                return ResponseEntity.ok( ApiResponse.<NhanVien>builder().data(nv).build());
            }
        }

        throw new ApiException(ErrorCode.NhanVien_NOT_EXIST);
    }


}
