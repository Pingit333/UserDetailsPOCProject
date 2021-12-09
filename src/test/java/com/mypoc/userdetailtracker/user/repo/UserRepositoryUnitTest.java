package com.mypoc.userdetailtracker.user.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mypoc.userdetailtracker.repo.UserRepository;
import com.mypoc.userdetailtracker.user.bean.Users;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class UserRepositoryUnitTest{

	@Autowired
	private UserRepository userRepository;

	@Test
	void retrieveAllUsers_noUserTest() throws Exception {
		List<Users> findAll = userRepository.findAll();
		assertEquals(0, findAll.toArray().length);
	}

	@Test
	void usersObjectDetailsTest() throws Exception {

		Users checkuser = new Users();
		checkuser.setName("Himu");
		checkuser.setSurname("Shinde");
		checkuser.setPincode(452011);
		checkuser.setBirthDate(new Date());
		checkuser.setDateOfJoining(new Date());
		checkuser.setDeleted(false);

		userRepository.save(checkuser);
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String resultAll = ow.writeValueAsString(userRepository.findAll());
		System.out.println("usersObjectDetailsTest() : \n" + resultAll);

		List<Users> findByName = userRepository.findByName("Himu");

		assertEquals("Himu", findByName.get(0).getName());
		assertEquals("Shinde", findByName.get(0).getSurname());
		assertEquals(452011, findByName.get(0).getPincode());
		assertEquals(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
				new SimpleDateFormat("yyyy-MM-dd").format(findByName.get(0).getBirthDate()));
		assertEquals(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
				new SimpleDateFormat("yyyy-MM-dd").format(findByName.get(0).getDateOfJoining()));
	}

	@Test
	void saveUserTest() throws Exception {
		Users checkuser = new Users();
		checkuser.setName("Himanshu");
		checkuser.setSurname("Shinde");
		checkuser.setPincode(763567);
		checkuser.setBirthDate(new Date());
		checkuser.setDateOfJoining(new Date());
		checkuser.setDeleted(false);
		Users save = userRepository.save(checkuser);

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

		String resultAll = ow.writeValueAsString(userRepository.findAll());
		System.out.println("saveUserTest() : \n" + resultAll);

		assertNotNull(save);
		userRepository.deleteAll();

		String result = ow.writeValueAsString(userRepository.findAll());
		System.out.println("saveUserTest() : \n" + result);

	}

	@Test
	void retrieveAllUsersTest() throws Exception {

		Users checkuser1 = new Users();
		checkuser1.setName("Himansh");
		checkuser1.setSurname("Shinde");
		checkuser1.setPincode(452011);
		checkuser1.setBirthDate(new Date());
		checkuser1.setDateOfJoining(new Date());
		checkuser1.setDeleted(false);
		userRepository.save(checkuser1);

		Users checkuser2 = new Users();
		checkuser2.setName("Bala");
		checkuser2.setSurname("Shinde");
		checkuser2.setPincode(452001);
		checkuser2.setBirthDate(new Date());
		checkuser2.setDateOfJoining(new Date());
		checkuser2.setDeleted(false);
		userRepository.save(checkuser2);

		List<Users> findAll = userRepository.findAll();
		assertEquals(2, findAll.size());

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String resultAll = ow.writeValueAsString(userRepository.findAll());
		System.out.println("retrieveAllUsersTest() : \n" + resultAll);

	}

	@Test
	void userObjectSearchFunctionTest() throws Exception {

		Users checkuser = new Users();
		checkuser.setName("Himi");
		checkuser.setSurname("Shinde");
		checkuser.setPincode(452011);
		checkuser.setBirthDate(new Date());
		checkuser.setDateOfJoining(new Date());
		checkuser.setDeleted(false);
		userRepository.save(checkuser);

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

		List<Users> findAll = userRepository.findAll();
		String resultAll = ow.writeValueAsString(findAll);
		assertNotNull(resultAll);

		System.out.println("userObjectSearchFunctionTest() : \n" + resultAll);

		Users findById = userRepository.findById(8).get();
		String resultById = ow.writeValueAsString(findById);
		assertNotNull(resultById);

		List<Users> findByName = userRepository.findByName("Himi");
		String resultByName = ow.writeValueAsString(findByName);
		assertNotNull(resultByName);

		List<Users> findBySurname = userRepository.findByName("Shinde");
		String resultBySurnname = ow.writeValueAsString(findBySurname);
		assertNotNull(resultBySurnname);

		List<Users> findByPincode = userRepository.findByPincode(452011);
		String resultByPincode = ow.writeValueAsString(findByPincode);
		assertNotNull(resultByPincode);
	}

	@Test
	void userObjectDeleteFunctionTest() throws Exception {

		Users checkuser1 = new Users();
		checkuser1.setName("Himanshi");
		checkuser1.setSurname("Shinde");
		checkuser1.setPincode(452011);
		checkuser1.setBirthDate(new Date());
		checkuser1.setDateOfJoining(new Date());
		checkuser1.setDeleted(false);
		userRepository.save(checkuser1);

		Users checkuser2 = new Users();
		checkuser2.setName("Bali");
		checkuser2.setSurname("Shinde");
		checkuser2.setPincode(452701);
		checkuser2.setBirthDate(new Date());
		checkuser2.setDateOfJoining(new Date());
		checkuser2.setDeleted(false);
		userRepository.save(checkuser2);

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String resultAll = ow.writeValueAsString(userRepository.findAll());
		System.out.println("userObjectDeleteFunctionTest() : \n" + resultAll);

		userRepository.deleteById(5);
		
		assertThat(userRepository.count()).isOne();

		String result = ow.writeValueAsString(userRepository.findAll());
		System.out.println("userObjectDeleteFunctionTest() : \n" + result);
	}

	@Test
	void userObjectUpdateFunctionTest() throws Exception {

		Users checkuser1 = new Users();
		checkuser1.setName("Himanshi");
		checkuser1.setSurname("Shinde");
		checkuser1.setPincode(452011);
		checkuser1.setBirthDate(new Date());
		checkuser1.setDateOfJoining(new Date());
		checkuser1.setDeleted(false);
		userRepository.save(checkuser1);

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String resultAll = ow.writeValueAsString(userRepository.findAll());
		System.out.println("userObjectUpdateFunctionTest() : \n" + resultAll);

		checkuser1.setName("Himanshuuu");
		checkuser1.setDeleted(true);
		userRepository.save(checkuser1);
		
		String result = ow.writeValueAsString(userRepository.findAll());
		System.out.println("userObjectUpdateFunctionTest() : \n" + result);
		
		String expected = "[ {\r\n" + "  \"id\" : 7,\r\n" + "  \"name\" : \"Himanshuuu\",\r\n"
				+ "  \"surname\" : \"Shinde\",\r\n" + "  \"pincode\" : 452011,\r\n"
				+ "  \"birthDate\" : \"2021-12-09\",\r\n" + "  \"dateOfJoining\" : \"2021-12-09\",\r\n"
				+ "  \"deleted\" : true\r\n" + "} ]";
		assertEquals(expected, result);

		
	}

	@Test
	void UsersObjectTest() throws Exception {

		Users checkuser = new Users();
		checkuser.setName("Himi");
		checkuser.setSurname("Shinde");
		checkuser.setPincode(452011);
		checkuser.setBirthDate(new Date());
		checkuser.setDateOfJoining(new Date());
		checkuser.setDeleted(false);
		userRepository.save(checkuser);

		List<Users> findAll = userRepository.findAll();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String resultAll = ow.writeValueAsString(findAll);
		System.out.println("UsersObjectTest() : \n" + resultAll);

		String expected = "[ {\r\n" + "  \"id\" : 9,\r\n" + "  \"name\" : \"Himi\",\r\n"
				+ "  \"surname\" : \"Shinde\",\r\n" + "  \"pincode\" : 452011,\r\n"
				+ "  \"birthDate\" : \"2021-12-09\",\r\n" + "  \"dateOfJoining\" : \"2021-12-09\",\r\n"
				+ "  \"deleted\" : false\r\n" + "} ]";
		assertEquals(expected, resultAll);
	}

}
