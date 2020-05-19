package cn.itcast.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.Servlet;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.entity.Customer;
import cn.itcast.entity.LinkMan;
import cn.itcast.service.CustomerService;
import cn.itcast.service.LinkManService;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{
private LinkManService linkManService;
private CustomerService customerService;
private LinkMan linkMan=new LinkMan();
public void setCustomerService(CustomerService customerService) {
	this.customerService = customerService;
}
public void setLinkManService(LinkManService linkManService) {
	this.linkManService = linkManService;
}
public String toAddPage() {
	 List<Customer> listCustomer=customerService.findAll();
	 ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);
	return "toAddPage";
}
/*
 * ��Ҫ�ϴ��ļ�������
 * ��Ҫ�ϴ��ļ�����
 * ��1����action�����Ա����λ�ö�������������淶��
 * һ����ʾ�ϴ��ļ�
 * һ����ʾ�ļ�����
 * ��2��
 * ���ɱ�����get��set����
 */
//1 �ϴ��ļ�
//������������Ҫ�Ǳ������ļ��ϴ����nameֵ
private File upload;
public File getUpload() {
	return upload;
}
public void setUpload(File upload) {
	this.upload = upload;
}
public String getUploadFileName() {
	return uploadFileName;
}
public void setUploadFileName(String uploadFileName) {
	this.uploadFileName = uploadFileName;
}
//2 �ϴ��ļ�����
//������������Ҫ�Ǳ������ļ��ϴ����nameֵ+FileName
private String uploadFileName;
public String addLinkMan() throws IOException {
	/*
	 * ���Է�װ��ϵ�˻�����Ϣ
	 * ������cid�ǿͻ�idֵ������ֱ�ӷ�װ��
	 * ��cid��װLinkManʵ��������customer��������
	 **/
	//1 ԭʼ��ʽʵ��
//	String scid=ServletActionContext.getRequest().getParameter("cid");
//	int cid=Integer.parseInt(scid);
//	Customer c=new Customer();
//	c.setCid(cid);
//	linkMan.setCustomer(c);
	if(upload!=null) {
		//д�ϴ�����
		//�ڷ������ļ������洴���ļ�
		File serverFile=new File("E:\\sshfileupload"+"/"+uploadFileName);
		
		//���ϴ����ļ����Ƶ��������ļ�����
		FileUtils.copyFile(upload, serverFile);
	}
	linkManService.addLinkMan(linkMan);
	return "addLinkMan";
}
public String listLinkMan() {
	List<LinkMan> list=linkManService.listLinkMan();
	ServletActionContext.getRequest().setAttribute("list", list);
	return "listLinkMan";
}
public String update() {
	List<Customer> listCustomer=customerService.findAll();
	ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);
	LinkMan linkMan1=linkManService.findOne(linkMan.getLinkid());
	ServletActionContext.getRequest().setAttribute("linkman", linkMan1);
	return "update";
}
public String updateLinkMan() {
	linkManService.updateLinkMan(linkMan);
	return "updateLinkMan";
	
}
public String delete() {
	linkManService.delete(linkMan);
	return "delete";
}
@Override
public LinkMan getModel() {
	// TODO Auto-generated method stub
	return linkMan;
}
public String toSelectPage() {
	List<Customer> list=customerService.findAll();
	ServletActionContext.getRequest().setAttribute("list", list);
	return "toSelectPage";
}
public String moreCondition() {
	List<LinkMan> list=linkManService.findMoreCondition(linkMan);
	ServletActionContext.getRequest().setAttribute("list", list);
	return "moreCondition";
}
}
