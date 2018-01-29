package com.solutionsdelivery.User.service;


import com.solutionsdelivery.User.dao.ChangePassword;
import com.solutionsdelivery.User.dao.LoginDetails;
import com.solutionsdelivery.User.dao.User;
import com.solutionsdelivery.User.dto.Data;
import com.solutionsdelivery.User.dto.UserResponse;
import com.solutionsdelivery.User.model.RemovedUser;
import com.solutionsdelivery.User.model.UserLogin;
import com.solutionsdelivery.User.model.UserProfile;
import com.solutionsdelivery.User.repository.RemovedUserRepository;
import com.solutionsdelivery.User.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service("userProcessRequestService")
public class UserProcessRequestServiceImpl implements UserProcessRequestService {

    private final UserRepository userRepository;
    private final RemovedUserRepository removedUserRepository;

    @Autowired
    private UserProcessRequestServiceImpl(UserRepository userRepository, RemovedUserRepository removedUserRepository){
        this.userRepository = userRepository;
        this.removedUserRepository = removedUserRepository;
    }

    @Override
    public UserResponse createUser(User user, String createdBy) {

        UserResponse userResponse = new UserResponse();
        UserProfile userProfile = userRepository.findByStaffNoContaining(user.getStaffNo());

        if(userProfile != null){

            userResponse.setResponseCode("01");
            UserLogin userLogin = userProfile.getUserLogin();

            if(user.getEmail().equals(userLogin.getEmail()))
                userResponse.setResponseMessage("email already exist for another user.");
            else
                userResponse.setResponseMessage("staff No already exist.");

        }
        else {

            userProfile = new UserProfile();
            UserLogin userLogin = new UserLogin();

            String dateCreated = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());

            userProfile.setCreatedBy(createdBy.trim());
            userProfile.setCreatedDate(dateCreated);
            BeanUtils.copyProperties(user, userProfile);
            BeanUtils.copyProperties(user, userLogin);
            userLogin.setPassword(encryptPassword(user.getPassword()));

            userProfile.setUserLogin(userLogin);
            userLogin.setUserProfile(userProfile);

            userProfile = userRepository.save(userProfile);

            Data data = new Data();
            List<Data> data1 = new ArrayList<>();

            BeanUtils.copyProperties(userProfile, data);
            data1.add(data);

            userResponse.setResponseCode("00");
            userResponse.setResponseMessage("successful");
            userResponse.setData(data1);

        }

        return userResponse;
    }

    @Override
    public UserResponse removeUser(User user, String removedBy) {

        UserResponse userResponse = new UserResponse();
        UserProfile userProfile = userRepository.findByStaffNoContaining(user.getStaffNo());

        if (userProfile == null){
            userResponse.setResponseCode("01");
            userResponse.setResponseMessage("invalid user");
        }

        String removedDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        RemovedUser removedUser = new RemovedUser();

        assert userProfile != null;
        BeanUtils.copyProperties(userProfile, removedUser);
        removedUser.setRemovedBy(removedBy);
        removedUser.setRemovedDate(removedDate);

        userRepository.delete(userProfile);
        removedUserRepository.save(removedUser);

        userResponse.setResponseCode("00");
        userResponse.setResponseMessage("User removed successfully");

        return userResponse;
    }

    @Override
    public UserResponse updatePassword(ChangePassword changePassword) {

        UserResponse userResponse = new UserResponse();
        UserProfile userProfile = userRepository.findByStaffNoOOrEmail(changePassword.getEmail());

        if (userProfile == null){
            userResponse.setResponseCode("01");
            userResponse.setResponseMessage("invalid user");
        }

        assert userProfile != null;
        UserLogin userLogin = userProfile.getUserLogin();

        if(BCrypt.checkpw(changePassword.getOldPassword(), userLogin.getPassword())){

            String lastPasswordUpdate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());

            userLogin.setPassword(encryptPassword(changePassword.getNewPassword()));
            System.out.println(changePassword.getNewPassword());
            userLogin.setLastPasswordUpdate(lastPasswordUpdate);

            userProfile.setUserLogin(userLogin);
            userRepository.saveAndFlush(userProfile);

            userResponse.setResponseCode("00");
            userResponse.setResponseMessage("Password change was successful");

        }else {

            userResponse.setResponseCode("02");
            userResponse.setResponseMessage("Incorrect old password");
        }

        return userResponse;
    }

    @Override
    public UserResponse userLogin(LoginDetails loginDetails) {

        UserResponse userResponse = new UserResponse();
        UserProfile userProfile = userRepository.findByStaffNoOOrEmail(loginDetails.getStaffNoOrEmail());

        if(userProfile == null){
            userResponse.setResponseCode("01");
            userResponse.setResponseMessage("invalid user");
        }else {

            UserLogin userLogin = userProfile.getUserLogin();

            if ((userLogin.getEmail().equals(loginDetails.getStaffNoOrEmail()) || userLogin.getStaffNo().equals(loginDetails.getStaffNoOrEmail())) && BCrypt.checkpw(loginDetails.getPassword(), userLogin.getPassword())){

                String dateCreated = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
                userLogin.setLastLogin(dateCreated);

                /*set last login and update user profile*/
                userProfile.setUserLogin(userLogin);
                userRepository.saveAndFlush(userProfile);

                List<Data> data1 = new ArrayList<>();
                Data data = new Data();

                BeanUtils.copyProperties(userProfile, data);
                data1.add(data);

                userResponse.setResponseCode("00");
                userResponse.setResponseMessage("successful");
                userResponse.setData(data1);

            }else{
                userResponse.setResponseCode("02");
                userResponse.setResponseMessage("invalid user credentials");
            }
        }
        return userResponse;
    }

    @Override
    public UserResponse getUsers() {

        UserResponse userResponse = new UserResponse();
        List<UserProfile> userProfiles = userRepository.findAll();

        if(userProfiles == null){
            userResponse.setResponseCode("01");
            userResponse.setResponseMessage("no user found");
        }else {

            List<Data> data = new ArrayList<>();

            for(UserProfile userProfile: userProfiles){
                Data newData = new Data();
                BeanUtils.copyProperties(userProfile, newData);

                data.add(newData);
            }

            userResponse.setResponseCode("00");
            userResponse.setResponseMessage("successful");
            userResponse.setData(data);

        }
        return userResponse;
    }

    private String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
