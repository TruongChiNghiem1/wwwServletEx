package entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "")
//NHACUNGCAP(MANCC, TENNHACC, DIACHI, SODIENTHOAI)
public class NhaCungCap {
	@Id
	@Column(name = "MANCC")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maNCC;
	
	@NotEmpty(message = "Tên nhà cung cấp không được để trống")
	@Size(max = 240, message = "Tên điện thoại tối đa 240 ký tự")	
	@Column(name = "TENNHACC")
	private String tenNCC;
	
	@NotEmpty(message = "Địa chỉ không được để trống")
	@Column(name = "DIACHI")
	private String diaChi;
	
	@Pattern(regexp = "0[0-9]{9}", message = "Số điện thoại không hợp lệ")
	@Column(name = "SODIENTHOAI")
	private String soDienThoai;
	
	@OneToMany(mappedBy = "nhaCungCap")
	private List<DienThoai> listDienThoai;

	public NhaCungCap(String tenNCC, String diaChi, String soDienThoai) {
		super();
		this.tenNCC = tenNCC;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
	}

	public NhaCungCap(int maNCC, String tenNCC, String diaChi, String soDienThoai) {
		super();
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
	}

	public NhaCungCap() {
		super();
	}

	public int getMaNCC() {
		return maNCC;
	}

	public void setMaNCC(int maNCC) {
		this.maNCC = maNCC;
	}

	public String getTenNCC() {
		return tenNCC;
	}

	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public List<DienThoai> getListDienThoai() {
		return listDienThoai;
	}

	public void setListDienThoai(List<DienThoai> listDienThoai) {
		this.listDienThoai = listDienThoai;
	}
	
	
	
	
}