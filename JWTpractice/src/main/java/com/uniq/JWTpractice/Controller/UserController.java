package com.uniq.JWTpractice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uniq.JWTpractice.Dto.Login;
import com.uniq.JWTpractice.Dto.SignUp;
import com.uniq.JWTpractice.Dto.Token;
import com.uniq.JWTpractice.Response.ApiResponse;
import com.uniq.JWTpractice.Service.UserService;

import jakarta.websocket.server.PathParam;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<ApiResponse> signUp(@RequestBody SignUp signUp) {

		ApiResponse apiResponse = userService.signUp(signUp);

		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse> login(@RequestBody Login login,Token token) {

		ApiResponse apiResponse = userService.login(login,token);

		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

	}

	
	@GetMapping("/findUser")
	public ResponseEntity<ApiResponse> findUser(@RequestParam int id , @RequestHeader String authorization) throws Exception {

		ApiResponse apiResponse = userService.findUser(id, authorization);

		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

	}

	@GetMapping("/findUser/{id}")
	public ResponseEntity<ApiResponse> findUserById(@PathVariable int id,@RequestHeader String authorization  ) {

		ApiResponse apiResponse = userService.byId(id,authorization);

		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

	}
	
	
	@GetMapping("/findAll")
	public ResponseEntity<ApiResponse> findAll() {

		ApiResponse apiResponse = userService.findAll();

		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

	}

}
