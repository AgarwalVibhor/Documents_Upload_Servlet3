package com.tcs.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tcs.business.UserBusinessInterface;
import com.tcs.business.UserDocumentBusinessInterface;
import com.tcs.model.FileBucket;
import com.tcs.model.User;
import com.tcs.model.UserDocument;
import com.tcs.util.FileValidator;
import com.tcs.util.UserValidator;

@Controller
public class AppController {
	
	@Autowired
	@Qualifier("userBusinessImpl")
	private UserBusinessInterface userBusiness;
	
	@Autowired
	@Qualifier("userDocumentBusinessImpl")
	private UserDocumentBusinessInterface userDocumentBusiness;
	
	@Autowired
	private FileValidator fileValidator;
	
	@Autowired
	private UserValidator userValidator;
	
/*	@Autowired
	private UserDocumentValidator userDocumentValidator;  */
	
	@InitBinder("fileBucket")
	protected void initFileBinder(WebDataBinder binder)
	{
		binder.setValidator(fileValidator);
	}
	
	@InitBinder("userForm")
	public void initUserValidator(WebDataBinder binder)
	{
		binder.setValidator(userValidator);
	}
	
	
	@RequestMapping(value = "/")
	public ModelAndView start(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView("redirect:/index");
		return model;
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView("index");
		return model;
	}
	
	@RequestMapping(value = "/registerGet", method = RequestMethod.GET)
	public ModelAndView registerGet(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView("registration");
		User user = new User();
		model.addObject("userForm", user);
		return model;
	}
	
	@RequestMapping(value = "/registerPost", method = RequestMethod.POST)
	public ModelAndView registerPost(HttpServletRequest request, HttpServletResponse response,
			@Valid @ModelAttribute("userForm") User user, BindingResult result)
	{
		ModelAndView model = null;
		int pk = 0;
		if(result.hasErrors())
		{
			model = new ModelAndView("registration");
			model.addObject("userForm", user);
		}
		else
		{
			pk = userBusiness.save(user);
			if(pk > 0)
			{
				model = new ModelAndView("registrationSuccess");
				model.addObject("userId", pk);
			}
			else
			{
				model = new ModelAndView("registration");
				model.addObject("userForm", user);
				model.addObject("message", "Some error occured. Please try again !!");
			}
		}
		return model;
	}
	
	@RequestMapping(value = "/uploadStartGet", method = RequestMethod.GET)
	public ModelAndView uploadStartGet(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView("uploadStart");
		return model;
	}
	
	@RequestMapping(value = "/uploadStartPost", method = RequestMethod.POST)
	public ModelAndView uploadStartPost(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "userId") Integer id)
	{
		ModelAndView model = null;
		if(id == null)
		{
			model = new ModelAndView("uploadStart");
			model.addObject("message", "Please enter a valid user ID !");
			return model;
		}
		User user = userBusiness.findById(id);
		if(user != null)
		{
			model = new ModelAndView("redirect:/uploadDocumentGet");
			
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			/*
			 * This user object will be available throughout the application as long as the session is valid.
			 */
		}
		else
		{
			model = new ModelAndView("uploadStart");
			model.addObject("message", "Please enter a valid user ID !");
		}
		return model;
	}
	
	@RequestMapping(value = "/uploadDocumentGet", method = RequestMethod.GET)
	public ModelAndView uploadDocumentGet(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView("uploadDocument");
		
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		
		FileBucket fileBucket = new FileBucket();
		model.addObject("fileBucket", fileBucket);
		model.addObject("fname", user.getFirstName());
		model.addObject("lname", user.getLastName());
		
		return model;
	}
	
	@RequestMapping(value = "/uploadDocumentPost", method = RequestMethod.POST)
	public ModelAndView uploadDocument(HttpServletRequest request, HttpServletResponse response,
			@Valid @ModelAttribute("fileBucket") FileBucket fileBucket, BindingResult result) throws IOException
	{
		ModelAndView model = null;
		int pk = 0;
		if(result.hasErrors())
		{
			model = new ModelAndView("uploadDocument");
			model.addObject("fileBucket", fileBucket);
		}
		else
		{
			HttpSession session = request.getSession(false);
			User user = (User) session.getAttribute("user");
			
			pk = saveDocument(fileBucket, user);
			if(pk > 0)
			{
				System.out.println("Document inserted successfully");
				model = new ModelAndView("redirect:/finalDocuments/" + user.getId());
			}
			else
			{
				System.out.println("Document could not be inserted.");
				model = new ModelAndView("uploadDocument");
				model.addObject("message", "Some error occured in the document upload. Please try again!!");
			}
			
		}
		return model;
	}
	
	private int saveDocument(FileBucket fileBucket, User user) throws IOException
	{
		System.out.println("Inside saveDocument -- Controller");
		UserDocument document = new UserDocument();
		
		MultipartFile file = fileBucket.getMultipartFile();
		document.setDocumentName(file.getOriginalFilename());
		document.setDescription(fileBucket.getDescription());
		document.setType(file.getContentType());
		document.setContent(file.getBytes());
		document.setUser(user);
		
		int pk = userDocumentBusiness.save(document);
		return pk;
	}
	
	@RequestMapping(value = "/listDocumentsGet", method = RequestMethod.GET)
	public ModelAndView viewDocumentsGet(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView("userDocumentsStart");
		return model;
	}
	
	@RequestMapping(value = "/listDocumentsPost", method = RequestMethod.POST)
	public ModelAndView viewDocumentsPost(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("userId") Integer userId)
	{
		ModelAndView model = null;
		if(userId == null)
		{
			model = new ModelAndView("userDocumentsStart");
			model.addObject("message", "Please enter a valid user ID !");
			return model;
		}
		User user = userBusiness.findById(userId);
		if(user != null)
		{
			model = new ModelAndView("userDocuments");
			model.addObject("userId", user.getId());
			model.addObject("fname", user.getFirstName());
			model.addObject("lname", user.getLastName());
			List<UserDocument> documents = userDocumentBusiness.findAllByUserId(userId);
			model.addObject("documents", documents);
		}
		else
		{
			model = new ModelAndView("userDocumentsStart");
			model.addObject("message", "Please provide a valid User ID !");
		}
		return model;
	}
	
	@RequestMapping(value = "/finalDocuments/{userId}", method = RequestMethod.GET)
	public ModelAndView finalDocuments(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Integer userId)
	{
		ModelAndView model = new ModelAndView("userDocuments");
		User user = userBusiness.findById(userId);
		List<UserDocument> documents = userDocumentBusiness.findAllByUserId(userId);
		model.addObject("fname", user.getFirstName());
		model.addObject("lname", user.getLastName());
		model.addObject("userId", user.getId());
		model.addObject("documents", documents);
		System.out.println("End here...");
		return model;
	}
	
	@RequestMapping(value = "/download/{userId}/{documentId}", method = RequestMethod.GET)
	public void downloadDocument(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("userId") Integer userId, @PathVariable("documentId") Integer documentId) throws IOException
	{
		UserDocument document = userDocumentBusiness.findById(documentId);
		response.setContentType(document.getType());
		response.setContentLength(document.getContent().length);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + document.getDocumentName() + "\"");
		// response.setHeader("headerKey", "headerValue");
		
		FileCopyUtils.copy(document.getContent(), response.getOutputStream());
		System.out.println("Downloading of document");
	}
	
	@RequestMapping(value = "/delete/{userId}/{documentId}", method = RequestMethod.GET)
	public ModelAndView deleteDocument(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("userId") Integer userId,
			@PathVariable("documentId") Integer documentId)
	{
		userDocumentBusiness.deleteById(documentId);
		System.out.println("Deleting of document");
		ModelAndView model = new ModelAndView("redirect:/finalDocuments/" + userId);
		return model;
	}
	
	@RequestMapping(value = "/deleteGet", method = RequestMethod.GET)
	public ModelAndView deleteUserGet(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView("deleteStart");
		return model;
	}
	
	@RequestMapping(value = "/deletePost", method = RequestMethod.POST)
	public ModelAndView deleteUserPost(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("userId") Integer userId)
	{
		ModelAndView model = null;
		if(userId == null)
		{
			model = new ModelAndView("deleteStart");
			model.addObject("message", "Please enter a valid user ID !");
			return model;
		}
		User user = userBusiness.findById(userId);
		if(user != null)
		{
			userBusiness.deleteBySso(user.getSsoId());
			model = new ModelAndView("redirect:/allUsers");
		}
		else
		{
			model = new ModelAndView("deleteStart");
			model.addObject("message", "Please enter a valid User ID !!");
		}
		return model;
	}
	
	@RequestMapping(value = "/allUsers", method = RequestMethod.GET)
	public ModelAndView allUsers(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView("viewAllUsers");
		List<User> users = userBusiness.findAll();
		model.addObject("users", users);
		return model;
	}
	
	@RequestMapping(value = "/viewGet", method = RequestMethod.GET)
	public ModelAndView viewUserStart(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView("viewStart");
		return model;
	}
	
	@RequestMapping(value = "/viewPost", method = RequestMethod.POST)
	public ModelAndView viewUser(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("userId") Integer userId)
	{
		
		ModelAndView model = null;
		if(userId == null)
		{
			model = new ModelAndView("viewStart");
			model.addObject("message", "Please enter a valid User ID !!");
			return model;
		}
		
		User user = userBusiness.findById(userId);
		if(user != null)
		{
			model = new ModelAndView("viewUser");
			model.addObject("user", user);
		}
		else
		{
			model = new ModelAndView("viewStart");
			model.addObject("message", "This User ID does not exist !!");
		}
		return model;
	}
	
	@RequestMapping(value = "/updateStartGet", method = RequestMethod.GET)
	public ModelAndView updateStart(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView("updateStart");
		return model;
	}
	
	@RequestMapping(value = "/updateStartPost", method = RequestMethod.POST)
	public ModelAndView updateUser(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("userId") Integer userId)
	{
		ModelAndView model = null;
		if(userId == null)
		{
			model = new ModelAndView("updateStart");
			model.addObject("message", "Please enter a valid User ID !!");
			return model;
		}
		User user = userBusiness.findById(userId);
		if(user != null)
		{
			model = new ModelAndView("updateUser");
			model.addObject("user", user);
		}
		else
		{
			model = new ModelAndView("updateStart");
			model.addObject("message", "Please enter a valid User ID !!");
		}
		return model;
	}
	
	@RequestMapping(value = "/updatePost", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response,
			@Valid @ModelAttribute("user") User user, BindingResult result)
	{
		ModelAndView model = null;
		if(result.hasErrors())
		{
			model = new ModelAndView("updateUser");
			model.addObject("user", user);
		}
		else
		{
			userBusiness.update(user);
			model = new ModelAndView("updateSuccess");
			model.addObject("user", user);
		}
		return model;
	}
	
	
	
	

}
