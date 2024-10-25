package controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Year;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import service.ServiceDienThoai;
import service.ServiceNhaCungCap;
import utils.EntityManagerFactoryProvider;

/**
 * Servlet implementation class QuanLyFormServlet
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 10)
@WebServlet(urlPatterns = { "/managerForm", "/managerForm/*" })
public class QuanLyFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ServiceDienThoai serviceDienThoai;
	private static ServiceNhaCungCap serviceNhaCungCap;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuanLyFormServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		serviceDienThoai = new ServiceDienThoai(EntityManagerFactoryProvider.getFactory().createEntityManager());
		serviceNhaCungCap = new ServiceNhaCungCap(EntityManagerFactoryProvider.getFactory().createEntityManager());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String deletePhoneString = request.getParameter("deletePhone");
		String deleteSupplierString = request.getParameter("deleteSupplier");
		String find = request.getParameter("find");
		if (deletePhoneString != null) {
			deletePhone(request, response, deletePhoneString);
		}
		if (deleteSupplierString != null) {
			deleteSupplier(request, response, deleteSupplierString);
		}
		if (find != null) {
			handleFind(request, response, find);
			request.getRequestDispatcher("views/Manager/index.jsp").forward(request, response);
			return;
		}
		if (request.getParameter("Phoneid") == null) {
			request.setAttribute("nhaCungCaps", serviceNhaCungCap.getAll());
			request.getRequestDispatcher("views/Manager/index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idPhone = request.getParameter("Phoneid");
		String idSupplier = request.getParameter("idSupplier");
		if (idPhone != null) {
			updatePhone(request, response, idPhone);
		} else if (idSupplier != null) {
			updateSupplier(request, response, idSupplier);
		}

	}

	private void updatePhone(HttpServletRequest request, HttpServletResponse response, String idPhone)
			throws IOException, ServletException {
		String phoneName = request.getParameter("phoneName");
		int year = Integer.parseInt(request.getParameter("phoneYear"));
		String configuration = request.getParameter("phoneSpecs");
		String uploadPath = createUploadPath();
		Part filePart = request.getPart("phoneImage");
		String fileName = filePart.getSubmittedFileName();
		String maNcc = request.getParameter("supplieid");
		String message = serviceDienThoai.update(Integer.parseInt(idPhone), phoneName, Year.of(year), configuration,
				Integer.parseInt(maNcc), fileName);
		if (message.equals("Cập nhật thành công")) {
			if (fileName != null && !fileName.isEmpty()) {
				filePart.write(uploadPath + File.separator + fileName);
			}
			request.setAttribute("nhaCungCaps", serviceNhaCungCap.getAll());
			request.getRequestDispatcher("views/Manager/index.jsp").forward(request, response);
		}
	}

	private String createUploadPath() {
		String relativePath = "assets" + File.separator + "imgs";
		String absolutePath = getServletContext().getRealPath("/") + File.separator + relativePath;
		File uploadDir = new File(absolutePath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		return absolutePath;
	}

	private void updateSupplier(HttpServletRequest request, HttpServletResponse response, String idSupplier)
			throws IOException, ServletException {
		String supplierName = request.getParameter("supplierName");
		String supplierAddress = request.getParameter("supplierAddress");
		String supplierPhone = request.getParameter("supplierPhone");
		String message = serviceNhaCungCap.update(Integer.parseInt(idSupplier), supplierName, supplierAddress,
				supplierPhone);
		if (!message.equals("Cập nhật thành công")) {
			request.setAttribute("message", message);
		}
		request.setAttribute("nhaCungCaps", serviceNhaCungCap.getAll());
		request.getRequestDispatcher("views/Manager/index.jsp").forward(request, response);
	}

	private void deletePhone(HttpServletRequest request, HttpServletResponse response, String deletePhoneString)
			throws IOException, ServletException {
		int id = Integer.parseInt(deletePhoneString);
		serviceDienThoai.delete(id);
	}

	private void deleteSupplier(HttpServletRequest request, HttpServletResponse response, String deleteSupplierString)
			throws IOException, ServletException {
		int id = Integer.parseInt(deleteSupplierString);
		if (!serviceNhaCungCap.delete(id)) {
			request.setAttribute("message", "Hãy xóa hết điện thoại của nhà cung cấp trước khi xóa nhà cung cấp này");
		}
	}

	private void handleFind(HttpServletRequest request, HttpServletResponse response, String find)
			throws ServletException, IOException {
		String[] parts = find.split(":");
		if (parts.length == 2) {
			String searchType = parts[0];
			String searchValue = parts[1];
			switch (searchType) {
			case "name":
				request.setAttribute("nhaCungCaps", serviceNhaCungCap.getByName(searchValue));
				break;
			case "address":
				request.setAttribute("nhaCungCaps", serviceNhaCungCap.getByAddress(searchValue));
				break;
			case "phone":
				request.setAttribute("nhaCungCaps", serviceNhaCungCap.getByPhone(searchValue));
				break;
			case "all":
				request.setAttribute("nhaCungCaps", serviceNhaCungCap.getAll());
				break;
			default:
				break;
			}
		}
	}
}
