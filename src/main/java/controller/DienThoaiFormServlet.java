package controller;

import java.io.File;
import java.io.IOException;
import java.time.Year;
import java.util.List;

import entity.NhaCungCap;
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
 * Servlet implementation class DienThoaiFormServlet
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 10)
@WebServlet(urlPatterns = { "/AddData" })
public class DienThoaiFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ServiceDienThoai serviceDienThoai;
	private static ServiceNhaCungCap serviceNhaCungCap;
	
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DienThoaiFormServlet() {
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
		List<NhaCungCap> nhaCungCaps = serviceNhaCungCap.getAll();
		request.setAttribute("nhaCungCaps", nhaCungCaps);
		if (request.getParameter("action") == null) {
			request.getRequestDispatcher("views/AddData/index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("formNhacc".equals(action)) {
			// Xử lý logic cho form 1
			handleFormNhacc(request, response);
		} else if ("formDienThoai".equals(action)) {
			// Xử lý logic cho form 2
			handleFormDienThoai(request, response);
		} else {
			response.sendRedirect("views/AddData/index.jsp");
		}
	}

	private void handleFormNhacc(HttpServletRequest request, HttpServletResponse response) {
		String tenNcc = request.getParameter("supplierName");
		String diaChi = request.getParameter("supplierAddress");
		String sdt = request.getParameter("supplierContact");
		String message = serviceNhaCungCap.insert(tenNcc, diaChi, sdt);
		request.setAttribute("message", message);
		request.setAttribute("statusNcc", false);
		if (message.equals("Thêm thành công")) {
			request.setAttribute("statusNcc", true);
		}
		try {
			List<NhaCungCap> nhaCungCaps = serviceNhaCungCap.getAll();
			request.setAttribute("nhaCungCaps", nhaCungCaps);
			request.getRequestDispatcher("views/AddData/index.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private void handleFormDienThoai(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String tenDienThoai = request.getParameter("phoneName");
		int namSx = Integer.valueOf(request.getParameter("phoneYear"));
		String ncc = request.getParameter("codeSupplier");
		String cauHinh = request.getParameter("configPhone");
		String uploadPath = createUploadPath();
		Part filePart = request.getPart("phoneImage");
		String fileName = filePart.getSubmittedFileName();
		String message = serviceDienThoai.insert(tenDienThoai, Year.of(namSx), cauHinh,Integer.valueOf(ncc), fileName);
		request.setAttribute("message", message);
		request.setAttribute("statusDt", false);
		if (message.equals("Thêm thành công")) {
			request.setAttribute("statusDt", true);
			String filePath = uploadPath + File.separator + fileName;
			filePart.write(filePath);
		}
		try {
			List<NhaCungCap> nhaCungCaps = serviceNhaCungCap.getAll();
			request.setAttribute("nhaCungCaps", nhaCungCaps);
			request.getRequestDispatcher("views/AddData/index.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
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

}
