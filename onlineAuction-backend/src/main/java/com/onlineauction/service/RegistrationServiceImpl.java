package com.onlineauction.service;

import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.onlineauction.entity.Customer;
import com.onlineauction.entity.Seller;
import com.onlineauction.entity.User;
import com.onlineauction.exception.CustomException;
import com.onlineauction.model.RegistrationInfo;
import com.onlineauction.model.ResponseStatus;
import com.onlineauction.repository.CustomerRepository;
import com.onlineauction.repository.SellerRepository;
import com.onlineauction.repository.UserRepository;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private SellerRepository sellerRepo;

	@Override
	public ResponseStatus<Object> register(RegistrationInfo registrationInfo) throws CustomException {

		ResponseStatus<Object> response;
		User user = new User();
		try {
			User existingUser = userRepo.findByUserName(registrationInfo.getEmail());
			if (!Objects.isNull(existingUser)) {
				System.out.println("ExistingUser : " + existingUser.toString());
				throw new CustomException("UserName Already Exists", HttpStatus.CONFLICT.value());
			} else {
				switch (registrationInfo.getRole()) {
				case 1:
					user.setRoles("1");
					break;
				case 2:
					Customer customer = new Customer();
					customer.setFirstName(registrationInfo.getFirstName());
					customer.setLastName(registrationInfo.getLastName());
					customer.setEmail(registrationInfo.getEmail());
					customer.setPassword(registrationInfo.getPassword());
					user.setRoles("2");
					customerRepo.save(customer);
					break;
				case 3:
					Seller seller = new Seller();
					seller.setFirstName(registrationInfo.getFirstName());
					seller.setLastName(registrationInfo.getLastName());
					seller.setEmail(registrationInfo.getEmail());
					seller.setPassword(registrationInfo.getPassword());
					seller.setSellerRating(0.0);
					user.setRoles("3");
					sellerRepo.save(seller);
					break;

				default:
					break;
				}
				user.setFirstName(registrationInfo.getFirstName());
				user.setLastName(registrationInfo.getLastName());
				user.setUserName(registrationInfo.getEmail());
				user.setPassword(encoder.encode(registrationInfo.getPassword()));
				user.setStatus("Y");
				userRepo.save(user);
				response = new ResponseStatus<Object>(HttpStatus.OK.value(), "Success", registrationInfo);
			}
		} catch (Exception e) {
			throw new CustomException("Error In saving the User " + e.getMessage(), HttpStatus.NOT_ACCEPTABLE.value());
		}
		return response;
	}

	@Override
	public ResponseStatus<Object> updateUser(RegistrationInfo registrationInfo) throws CustomException {
		ResponseStatus<Object> response;
		User user = new User();
		User existingUser = userRepo.findByUserName(registrationInfo.getEmail());
		
		if (!Objects.isNull(existingUser)) {
			System.out.println("ExistingUser : " + existingUser.toString());
			switch (registrationInfo.getRole()) {
			case 1:
				user.setFirstName(registrationInfo.getFirstName());
				user.setLastName(registrationInfo.getLastName());
		
				break;
			case 2:
				Customer customer = customerRepo.findByEmail(registrationInfo.getEmail());
				customer.setFirstName(registrationInfo.getFirstName());
				customer.setLastName(registrationInfo.getLastName());
			//	customer.setEmail(registrationInfo.getEmail());
				//customer.setPassword(registrationInfo.getPassword());
				//user.setRoles("2");
				customerRepo.save(customer);
				break;
			case 3:
				Seller seller = new Seller();
				seller.setFirstName(registrationInfo.getFirstName());
				seller.setLastName(registrationInfo.getLastName());
				seller.setEmail(registrationInfo.getEmail());
				seller.setPassword(registrationInfo.getPassword());
				seller.setSellerRating(0.0);
				user.setRoles("3");
				sellerRepo.save(seller);
				break;

			default:
				break;
			}
			user.setFirstName(registrationInfo.getFirstName());
			user.setLastName(registrationInfo.getLastName());
			user.setUserName(registrationInfo.getEmail());
			user.setPassword(encoder.encode(registrationInfo.getPassword()));
			user.setStatus("Y");
			userRepo.save(user);
			response = new ResponseStatus<Object>(HttpStatus.OK.value(), "Success", registrationInfo);
		
		}
		
		
		return null;
	}

}
