package com.example.users.service;

import com.example.users.model.*;
import com.example.users.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UsersJpaService  implements UsersRepository {
    @Autowired
    private UsersJpaRepository userRepo;
    @Autowired
    private ManagerJpaRepository managerRepo;

    @Override
    public List<Users> getAllUsers(){
        return userRepo.findAll();
    }

    // Add New User in DB
    @Override
    public Users addNewUser(Users obj){
        try {
            //Full Name Validation
            if (obj.getFullName() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ENTER YOUR FULL NAME");
            }

            //Mobile Number Validation
            if(obj.getMobileNumber()==null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Plase Enter a mobile number");
            }else{

                String mobN = obj.getMobileNumber();
                //Replace all Special Charecters
                String cleanedNumber = mobN.replaceAll("\\D", "");
                if (cleanedNumber.startsWith("91")) {
                    cleanedNumber = cleanedNumber.substring(2);
                } else if (cleanedNumber.startsWith("0")) {
                    cleanedNumber = cleanedNumber.substring(1);
                }

                if (cleanedNumber.length() != 10) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Mobile number must be exactly 10 digits");
                }
                obj.setMobileNumber(cleanedNumber);

            }

            //PanNumber Validation
            if(obj.getPanNum()==null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"please enter a PAN number");
            }else{
                obj.setPanNum(obj.getPanNum().toUpperCase());
            }

            //Manager Validation
            try {
                //Get UUID of Manager
                UUID mId = obj.getManager().getManagerId();
                //Retrive Manager from DB
                Manager mObj = managerRepo.findById(mId).get();
                obj.setManager(mObj);
                //set Create at timeStamp
                LocalDateTime l = LocalDateTime.now();
                obj.setCreatedAt(String.valueOf(l));
                obj.setIsActive(true);
                return userRepo.save(obj);

            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Please enter a valid manager id");
            }
        }catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    // Return Specific User based on ManagerId or mobile number  or PAN number
    @Override
    public List<Users> getSpecificUser(Users obj){
        //get users based on managerId
        if(obj.getManager()!=null){
            Manager m=managerRepo.findById(obj.getManager().getManagerId()).get();
            return  m.getUsers();

        }
        // if request body empty then return all users
        if(obj==null){
            return  userRepo.findAll();
        }
        try{
            List<Users> newObj=null;
            // return users based on panNum
            if(obj.getPanNum()!=null){
                newObj=  userRepo.findByPanNum(obj.getPanNum());

            }
            //return users based on mobile number
            if(obj.getMobileNumber()!= null){
                newObj= userRepo.findByMobileNumber(obj.getMobileNumber());
            }
            if(newObj==null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            return newObj;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    //delete user based on userId or Mobile number
    @Override
    public String deleteUser(Users obj){
        try{
            //delete user based on userId
            if(obj.getUserId()!=null){
                Users u=userRepo.findById(obj.getUserId()).get();
                if(u==null){
                    return String.format("No Users Available with this userId %s",obj.getUserId());
                }
                userRepo.deleteById(obj.getUserId());

            }
            //delete user based on mobile number
            if(obj.getMobileNumber()!= null){
                List<Users> ul=userRepo.findByMobileNumber(obj.getMobileNumber());
                if(ul.isEmpty()){
                    return String.format("No Users Available with this mobileNumber %s",obj.getMobileNumber());
                }
                Users uObj=ul.get(0);
                userRepo.deleteById(uObj.getUserId());
            }
            return "user deleted sucessfully";

        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    //Updates Bulk user Manager or update a specific user details

    @Override
    public String updateUsers(List<UUID> userIds,Users userObj){
        try {

            // validate userIds and userObj
            if(userIds.isEmpty() || userObj==null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"some details are missing");
            }
            // if user tries to update multiple user details at time Rise an Error
            if(userIds.size()==1 && userObj.getFullName()!= null || userObj.getPanNum() != null || userObj.getMobileNumber() != null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"You can Update List of users individual basis only and not in bulk.");
            }
            //update Individual user
            if (userIds.size() == 1) {
                //return user from database
                Users userOneFromDb = userRepo.findById(userIds.get(0)).get();
                //update user details
                if (userObj.getFullName() != null) {
                    userOneFromDb.setFullName(userObj.getFullName());
                }//Mobile Number Validation
                if (userObj.getMobileNumber() != null) {
                    String mobN = userObj.getMobileNumber();
                    //Replace all Special Charecters
                    String cleanedNumber = mobN.replaceAll("\\D", "");
                    if (cleanedNumber.startsWith("91")) {
                        cleanedNumber = cleanedNumber.substring(2);
                    } else if (cleanedNumber.startsWith("0")) {
                        cleanedNumber = cleanedNumber.substring(1);
                    }

                    if (cleanedNumber.length() != 10) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Mobile number must be exactly 10 digits");
                    }
                    userObj.setMobileNumber(cleanedNumber);

                }
                //pan number validation
                if (userObj.getPanNum() != null) {
                    userOneFromDb.setPanNum(userObj.getPanNum().toUpperCase());
                }
                // manager validation
                if (userObj.getManager() != null) {
                    Manager m = managerRepo.findById(userObj.getManager().getManagerId()).get();
                    if(m==null){
                        throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid managerId");
                    }
                    userOneFromDb.setManager(m);
                }
                // set update timeStamp
                LocalDateTime l=LocalDateTime.now();
                userOneFromDb.setUpdatedAt(String.valueOf(l));
                userRepo.save(userOneFromDb);

            }
            // update Bulk users
            if(userIds.size()>1){
                // Retrive users from db
                List<Users> userLIst=userRepo.findAllById(userIds);
                if(userLIst.size()!= userIds.size()){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"some user id's are invalid");
                }
                //Retrive manager from db
                Manager m = managerRepo.findById(userObj.getManager().getManagerId()).get();
                if(m==null){
                    throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid managerId");
                }
                for( Users e : userLIst){
                    e.setManager(m);
                    LocalDateTime l=LocalDateTime.now();
                    e.setUpdatedAt(String.valueOf(l));
                }
                userRepo.saveAll(userLIst);
            }

            return "Updated Sucessfully";
        }catch (NoSuchElementException e){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
