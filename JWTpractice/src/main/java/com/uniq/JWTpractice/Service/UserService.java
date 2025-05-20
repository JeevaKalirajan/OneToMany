package com.uniq.JWTpractice.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.uniq.JWTpractice.Dto.Login;
import com.uniq.JWTpractice.Dto.SignUp;
import com.uniq.JWTpractice.Dto.Token;
import com.uniq.JWTpractice.Entity.User;
import com.uniq.JWTpractice.Global.UserNotFoundException;
import com.uniq.JWTpractice.Repository.UserRepo;
import com.uniq.JWTpractice.Response.ApiResponse;
import com.uniq.JWTpractice.utils.JwtUtils;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private JwtUtils jwtUtils;

	public ApiResponse signUp(SignUp signUp) {

		ApiResponse apiResponse = new ApiResponse();

		User user = new User();
		user.setName(signUp.getName());
		user.setEmail(signUp.getEmail());
		user.setPassword(signUp.getPassword());

		userRepo.save(user);

		apiResponse.setStatus(HttpStatus.CREATED.value());
		apiResponse.setData(user);

		return apiResponse;

	}

	public ApiResponse login(Login login, Token token) {

		ApiResponse apiResponse = new ApiResponse();

		User user = userRepo.findByEmailAndPassword(login.getEmail(), login.getPassword());

		if (user == null) {
			throw new UserNotFoundException();
		}

		// these three line
		String token1;

		if (token.getToken() != null) {
			token1 = token.getToken();
		} else {
			token1 = jwtUtils.generateJwt(user);
			user.setToken(token1);
			userRepo.save(user);
		}

		Map<String, Object> data = new HashMap<>();
		data.put("AccessToken", token1);

		apiResponse.setStatus(HttpStatus.FOUND.value());
		apiResponse.setData(data);

		return apiResponse;
	}

	public ApiResponse findUser(int id, String token) throws Exception {
		ApiResponse apiResponse = new ApiResponse();

		if (jwtUtils.verify(token)) {

			int uId = Integer.parseInt(jwtUtils.extractJwtToken(token));

			if (uId == id) {
				Optional<User> user = userRepo.findById(id);

				if (user.isPresent()) {
					User u = user.get();
					apiResponse.setData(u);
					apiResponse.setStatus(HttpStatus.FOUND.value());
					return apiResponse;
				} else
					throw new UserNotFoundException();
			} else {
				apiResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
				apiResponse.setError("Unauthorized access");
				return apiResponse;
			}

		} else {
			apiResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
			apiResponse.setError("Token Mismatch");
			return apiResponse;
		}

	}

	public ApiResponse byId(int id,String token) {
		
		ApiResponse apiResponse = new ApiResponse();
		Optional<User> user = userRepo.findById(id);
		
		String t = jwtUtils.extractJwtToken(token);
		
		if(Integer.parseInt(t) == id) {
			User u = user.get();
			apiResponse.setData(u);
			apiResponse.setStatus(HttpStatus.FOUND.value());
			return apiResponse;
		}else {
			apiResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
			apiResponse.setError("Unauthorized access");
			return apiResponse;
		}
		
		
		
	}

	public ApiResponse findAll() {

		ApiResponse apiResponse = new ApiResponse();

		List<User> users = userRepo.findAll();

		apiResponse.setData(users);
		apiResponse.setStatus(HttpStatus.OK.value());

		return apiResponse;
	}
}
