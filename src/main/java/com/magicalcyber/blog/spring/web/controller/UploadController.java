package com.magicalcyber.blog.spring.web.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.magicalcyber.blog.spring.web.view.UploadForm;

@Controller
@RequestMapping("/upload")
public class UploadController {

	private static final Logger logger = LoggerFactory
			.getLogger(UploadController.class);

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload(Model model) {
		model.addAttribute("uploadForm", new UploadForm());
		return "upload";
	}

	@RequestMapping(value = "/upload", consumes = "multipart/form-data", method = RequestMethod.POST)
	public String upload(
			@Valid @ModelAttribute("uploadForm") final UploadForm form,
			BindingResult binding) {
		logger.debug("binding error: {}", binding.hasErrors());
		logger.debug("---- file content type: {}", form.getFile().getContentType());
		if (binding.hasErrors()) {
			return "upload";
		} else {
			
			return "upload-complete";
		}
	}
}
