package entity;

import java.time.Year;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "DIENTHOAI")
//DIENTHOAI(MADT, TENDT, NAMSANXUAT, CAUHINH, MANCC, HINHANH)
public class DienThoai {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MADT")
	private int id;

	@Size(max = 240, message = "Tên điện thoại tối đa 240 ký tự")
	@NotEmpty(message = "Tên điện thoại không được để trống")
	@Column(name = "TENDT")
	private String tenDT;

	@NotNull(message = "Năm sản xuất không được để trống")
	@PastOrPresent(message = "Năm sản xuất phải nhỏ hơn hoặc bằng năm hiện tại")
	@Column(name = "NAMSANXUAT")
	private Year namSanXuat;

	@Size(max = 255, message = "Tên điện thoại tối đa 240 ký tự")
	@Column(name = "CAUHINH")
	private String cauHinh;

	@ManyToOne
	@JoinColumn(name = "MANCC")
	private NhaCungCap nhaCungCap;

	@Pattern(regexp = "(^$|.*\\.(png|jpg)$)", message = "Hình ảnh phải có định dạng .png hoặc .jpg")
	@Column(name = "HINHANH")
	private String hinhAnh;

	public DienThoai(int id, String tenDT, Year namSanXuat, String cauHinh, NhaCungCap nhaCungCap, String hinhAnh) {
		super();
		this.id = id;
		this.tenDT = tenDT;
		this.namSanXuat = namSanXuat;
		this.cauHinh = cauHinh;
		this.nhaCungCap = nhaCungCap;
		this.hinhAnh = hinhAnh;
	}

	public DienThoai(String tenDT, Year namSanXuat, String cauHinh, NhaCungCap nhaCungCap, String hinhAnh) {
		super();
		this.tenDT = tenDT;
		this.namSanXuat = namSanXuat;
		this.cauHinh = cauHinh;
		this.nhaCungCap = nhaCungCap;
		this.hinhAnh = hinhAnh;
	}

	public DienThoai() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTenDT() {
		return tenDT;
	}

	public void setTenDT(String tenDT) {
		this.tenDT = tenDT;
	}

	public Year getNamSanXuat() {
		return namSanXuat;
	}

	public void setNamSanXuat(Year namSanXuat) {
		this.namSanXuat = namSanXuat;
	}

	public String getCauHinh() {
		return cauHinh;
	}

	public void setCauHinh(String cauHinh) {
		this.cauHinh = cauHinh;
	}

	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

}
