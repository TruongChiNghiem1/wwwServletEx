package impl;

import java.util.List;

import dao.NhaCungCapDao;
import entity.NhaCungCap;
import jakarta.persistence.EntityManager;

public class NhaCungCapImpl implements NhaCungCapDao {
	private EntityManager eManager;

	public NhaCungCapImpl(EntityManager eManager) {
		this.eManager = eManager;
	}

	@Override
	public List<NhaCungCap> getAll() {
		eManager.clear(); 
		return eManager.createQuery("from NhaCungCap", NhaCungCap.class).getResultList();
	}

	@Override
	public NhaCungCap getById(int id) {
		return eManager.find(NhaCungCap.class, id);
	}

	@Override
	public boolean insert(NhaCungCap ncc) {
		try {
			eManager.getTransaction().begin();
			eManager.persist(ncc);
			eManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			if (eManager.getTransaction().isActive()) {
				eManager.getTransaction().rollback();
			}
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean update(NhaCungCap ncc) {
		try {
			eManager.getTransaction().begin();
			eManager.merge(ncc);
			eManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			if (eManager.getTransaction().isActive()) {
				eManager.getTransaction().rollback();
			}
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		try {
			eManager.getTransaction().begin();
			NhaCungCap ncc = eManager.find(NhaCungCap.class, id);
			if (ncc == null) {
				eManager.getTransaction().rollback();
				return false;
			}
			eManager.remove(ncc);
			eManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			if (eManager.getTransaction().isActive()) {
				eManager.getTransaction().rollback();
			}
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<NhaCungCap> getByName(String name) {
		List<NhaCungCap> nhaCungCaps = eManager
				.createQuery("SELECT n FROM NhaCungCap n WHERE n.tenNCC LIKE :tenNCC", NhaCungCap.class)
				.setParameter("tenNCC", "%" + name + "%").getResultList();
		return nhaCungCaps;
	}

	@Override
	public List<NhaCungCap> getByAddress(String address) {
		List<NhaCungCap> nhaCungCaps = eManager
				.createQuery("SELECT n FROM NhaCungCap n WHERE n.diaChi LIKE :diaChi", NhaCungCap.class)
				.setParameter("diaChi", "%" + address + "%").getResultList();
		return nhaCungCaps;
	}

	@Override
	public List<NhaCungCap> getByPhone(String phone) {
		List<NhaCungCap> nhaCungCaps = eManager
				.createQuery("SELECT n FROM NhaCungCap n WHERE n.soDienThoai LIKE :soDienThoai", NhaCungCap.class)
				.setParameter("soDienThoai", "%" + phone + "%").getResultList();
		return nhaCungCaps;
	}

}
