package com.neosoft.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.model.AuthRequest;
import com.neosoft.model.BlogMessage;
import com.neosoft.model.BlogMessageRequest;
import com.neosoft.model.UserInfo;
import com.neosoft.model.UserInfoRequest;
import com.neosoft.repo.BlogMessageRepository;
import com.neosoft.repo.UserInforepository;
import com.neosoft.service.JWTService;

@RestController
@RequestMapping("/api/v1")
public class BlogController {
	
	@Autowired
	private UserInforepository repo;
	@Autowired
	private BlogMessageRepository blogRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private AuthenticationManager authmanger;

	@PostMapping("/register")
	public String adduser(@RequestBody UserInfoRequest userRequestInfo) {
		
		UserInfo userInfo=new UserInfo();
		userInfo.setUsn(userRequestInfo.getUsn());
		userInfo.setPwd(encoder.encode(userRequestInfo.getPwd()));
		userInfo.setRoles(userRequestInfo.getRoles());
		try {
		repo.save(userInfo);
		
		return "successfully save user ";
		}catch(Exception e) {
			return e.getMessage();
		}
		
	}
	
	@PostMapping("/login")
	public String generateToken(@RequestBody AuthRequest authReq) throws Exception {
		Authentication authenticate = authmanger.authenticate(new UsernamePasswordAuthenticationToken(authReq.getUsn(),authReq.getPwd()));
		if(authenticate.isAuthenticated())
			return jwtService.generateToken(authReq.getUsn());
		else
			throw new Exception("Invalid User");
	}
	@GetMapping("/get-profile/{uid}")
	public ResponseEntity<?> adduser(@PathVariable Integer uid) {
		
		try {
		Optional<UserInfo> findByIdOpt = repo.findById(uid);
		if(findByIdOpt.isPresent()) {
			return new ResponseEntity<UserInfo>(findByIdOpt.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("user profile not present",HttpStatus.OK);
		}
		}catch(Exception e) {
			return  new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/get-profile-blog/{uid}")
	public ResponseEntity<?> adduserBlog(@PathVariable Integer uid) {
		
		try {
		Optional<UserInfo> findByIdOpt = repo.findById(uid);
		if(findByIdOpt.isPresent()) {
			List<BlogMessage> listBlogMessages=findByIdOpt.get().getBlog();
			return new ResponseEntity<List<BlogMessage>>(listBlogMessages,HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("user profile not present",HttpStatus.OK);
		}
		}catch(Exception e) {
			return  new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping("/saveblog")
	public ResponseEntity<?> addBlog(@RequestBody BlogMessageRequest request) {
		
		try {
			Optional<UserInfo> findById = repo.findById(request.getUserId());
			if(findById.isPresent()) {
				UserInfo userInfo=findById.get();
					BlogMessage blogMessage=new BlogMessage();
					blogMessage.setBlog(request.getMessage());
					blogMessage.setInfo(userInfo);
					List<BlogMessage> listBlogMessages=new ArrayList<>();
					listBlogMessages.add(blogMessage);
					userInfo.setBlog(listBlogMessages);
					
					repo.save(userInfo);
				
				return new ResponseEntity<String>("Blog message successfully added",HttpStatus.OK);
			}
			return new ResponseEntity<String>("User Id not found",HttpStatus.OK);
		
		}catch(Exception e) {
			return  new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
}
