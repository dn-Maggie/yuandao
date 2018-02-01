package com.dongnao.workbench.common.fileUpload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.apache.log4j.config.PropertyGetter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.PropertiesEditor;

import com.dongnao.workbench.common.util.DateUtil;
import com.dongnao.workbench.common.util.SpringInit;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.school.model.EmpNotice;
import com.dongnao.workbench.school.service.EmpNoticeService;

import net.sf.json.JSONObject;
import com.dongnao.workbench.common.util.AjaxUtils;
/**
 * Servlet implementation class FileUpload
 */
@WebServlet("/FileUpload")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;


	Logger logger = Logger.getLogger(FileUpload.class);// 日志

	private EmpNoticeService empNoticeService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileUpload() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 拿到上下文才能在HttpServlet实现类中调用该接口方法
		empNoticeService = (EmpNoticeService) SpringInit.getApplicationContext().getBean("empNoticeService");
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/xml");//设置返回json的编码格式，防止在jsp页面出现乱码
		response.setCharacterEncoding("UTF-8");
		JSONObject reJo = new JSONObject();//设置返回json
		
		File uploadPath = new File("/usr/dnfile");
		logger.debug("uploadPath=====" + uploadPath);// 打印日志
		if (!uploadPath.exists() && !uploadPath.isDirectory()) {
			uploadPath.mkdir();
		}
		File filePath = new File(uploadPath.getAbsoluteFile() + File.separator + "file");
		if (!filePath.exists() && !filePath.isDirectory()) {
			filePath.mkdir();
		}
		//创建临时路径
		File tempPath = new File(getServletContext().getRealPath("/temp"));
		if (!tempPath.exists()) {
			tempPath.mkdir();
		}
		/********************************* 使用 FileUpload 组件解析表单********************/
		// DiskFileItemFactory：创建 FileItem 对象的工厂，在这个工厂类中可以配置内存缓冲区大小和存放临时文件的目录。
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// maximum size that will be stored in memory
		factory.setSizeThreshold(4096);// 设置是否将上传文件以临时文件的形式保存在磁盘的临界值如果从没有调用该方法设置此临界值，将会采用系统默认值10KB。
										// //对应的getSizeThreshold() 方法用来获取此临界值。
		// the location for saving data that is larger than getSizeThreshold()
		factory.setRepository(tempPath);// 指定临时文件目录
		// ServletFileUpload：负责处理上传的文件数据，并将每部分的数据封装成到 FileItem 对象中。
		// 在接收上传文件数据时，会将内容保存到内存缓存区中，如果文件内容超过了 DiskFileItemFactory 指定的缓冲区的大小，
		// 那么文件将被保存到磁盘上，存储为 DiskFileItemFactory 指定目录中的临时文件。
		// 等文件数据都接收完毕后，ServletUpload再从文件中将数据写入到上传文件目录下的文件中
		ServletFileUpload upload = new ServletFileUpload(factory);
		// maximum size before a FileUploadException will be thrown
		upload.setSizeMax(1000000 * 20);// 设置上传文件的最大值
		/******************************** 解析表单传递过来的数据，返回List集合数据-类型:FileItem***********/
		try {
			// 解析request对象，并把表单中的每一个输入项包装成一个fileItem对象，并返回一个保存了所有FileItem的list集合
			List fileItems = upload.parseRequest(request);
			String itemNo = "";
			// Iterator iter = fileItems.iterator()取其迭代器
			for (Iterator iter = fileItems.iterator(); iter.hasNext();) {// (遍历表单的每个字段包括文件类型)
				// 获得序列中的下一个元素
				FileItem item = (FileItem) iter.next();
				if (item.isFormField()) {// isFormField方法判断该表单域是普通域（true）还是上传文件（false）
					itemNo = item.getString();//得到表单域内容（value）
					if (itemNo != null && !"".equals(itemNo)) {
						itemNo = new String(itemNo.getBytes("ISO-8859-1"), "UTF-8");//格式转换
					}
				}
				if (!item.isFormField()) {
					// 上传文件的名称和完整路径
					String fileName = item.getName();
					String size = String.valueOf(item.getSize());
					// 判断是否选择了文件
					if ((fileName == null || fileName.equals("")) && Long.parseLong(size) == 0) {
						continue;
					}
					// 保存文件在服务器的物理磁盘中：第一个参数是：完整路径（不包括文件名）第二个参数是：文件名称
					File file = new File(filePath, fileName);
					item.write(file);
					reJo.put("code", "0000");
					reJo.put("message", "公告发布成功！");
					//linux系统中采用正斜杠“/”windows采用反斜杠“\”
					reJo.put("fileAddr", filePath  + "/" + fileName);
					AjaxUtils.sendAjaxForObject(response, reJo);
				}
			}
		} catch (Exception e) {
			reJo.put("code", "1111");
			reJo.put("message", "文件上传失败！");
			AjaxUtils.sendAjaxForObject(response, reJo);
			e.printStackTrace();
		}
 	}
}
