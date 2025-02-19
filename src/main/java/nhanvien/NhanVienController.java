package nhanvien;

import org.springframework.http.HttpStatus;
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
                    new NhanVien(1,"van nhan", LocalDate.of(2003,10,03),"nam",5000.000,"0762605901"),
                    new NhanVien(2,"anh tu", LocalDate.of(2003,10,20),"nam",5000.000,"0762605902"),
                    new NhanVien(3,"van tuan", LocalDate.of(2003,6,19),"nam",5000.000,"0762605903")

            )
    );
    @GetMapping("/nhanvien")
    public ResponseEntity<List<NhanVien>> getAllNhanvien() {
        return ResponseEntity.ok(nhanviens);
    }
    @GetMapping("/nhanvien/id={id}")
    public ResponseEntity<List<NhanVien>> getNhanvien(@PathVariable("id") int id) {

        List<NhanVien> nhanvien=new ArrayList<>();
        for (NhanVien nv : nhanviens) {
            if (nv.getId()==id) {
                nhanvien.add(nv);
                return ResponseEntity.ok(nhanvien);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/nhanvien/add")
    public ResponseEntity<List<NhanVien>> addNhanvien(@RequestBody NhanVien nhanvien) {
        nhanvien.setId(nhanviens.size()+1);
        nhanviens.add(nhanvien);
        return ResponseEntity.ok(nhanviens);
    }

    @PutMapping("/nhanvien/upd/id={id}")
    public ResponseEntity<List<NhanVien>> updNhanvien(@RequestBody NhanVien nhanvien,
                                                      @PathVariable("id") int id) {
        for (NhanVien nv : nhanviens) {
            if (nv.getId()==id) {
                nv.setName(nhanvien.getName());
                nv.setBỉth(nhanvien.getBỉth());
                nv.setGender(nhanvien.getGender());
                nv.setSalary(nhanvien.getSalary());
                nv.setPhone(nhanvien.getPhone());
                return ResponseEntity.ok(nhanviens);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/nhanvien/del/id={id}")
    public ResponseEntity<List<NhanVien>> deleteNhanvien(@PathVariable("id") int id) {
        for (NhanVien nv : nhanviens) {
            if (nv.getId()==id) {
                nhanviens.remove(nv);
                return ResponseEntity.ok(nhanviens);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}
