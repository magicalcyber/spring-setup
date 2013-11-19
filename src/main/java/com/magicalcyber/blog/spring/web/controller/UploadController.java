package com.magicalcyber.blog.spring.web.controller;

import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(
			@RequestParam(value = "file", required = true) MultipartFile file) {
		if (file.getSize() > 0) {
			return "upload-complete";
		} else {
			return "upload";
		}

	}
}
