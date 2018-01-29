package com.solutionsdelivery.User.service;

import com.solutionsdelivery.User.dao.ChangePassword;
import com.solutionsdelivery.User.dao.LoginDetails;
import com.solutionsdelivery.User.dao.User;
import com.solutionsdelivery.User.dto.UserResponse;
import com.solutionsdelivery.User.repository.UserRepository;

public interface UserProcessRequestService {

	UserResponse createUser(User user, String createdBy);
	UserResponse removeUser(User user, String removedBy);
	UserResponse updatePassword(ChangePassword changePassword);
	UserResponse userLogin(LoginDetails loginDetails);
	UserResponse getUsers();

}
