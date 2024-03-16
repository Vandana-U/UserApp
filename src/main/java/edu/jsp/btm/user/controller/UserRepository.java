package edu.jsp.btm.user.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer>{
//	@Query(value="SELECT u FROM User u WHERE u.userEmail=?1 and u.userPassword=?2")
//	User findUserByEmailAndPassword(String userEmail,String userPassword);
	User findUserByUserEmailAndUserPassword(String userEmail, String userPassword);
	User findUserByuserEmail(String userEmail );

}
