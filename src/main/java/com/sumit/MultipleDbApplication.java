package com.sumit;

import com.sumit.db1.entities.User;
import com.sumit.db1.repositories.UserRepo;
import com.sumit.db2.entities.Book;
import com.sumit.db2.repositories.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class MultipleDbApplication implements ApplicationRunner {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private BookRepo bookRepo;

	public static void main(String[] args) {
		SpringApplication.run(MultipleDbApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		User user1=User.builder()
				.firstName("sumit")
				.lastName("dhanorkar")
				.email("sumit@gmail.com")
				.build();
		User user2=User.builder()
				.firstName("shreyash")
				.lastName("meshram")
				.email("shreyash@gmail.com")
				.build();

		User user3=User.builder()
				.firstName("shubham")
				.lastName("wankhede")
				.email("shubham@gmail.com")
				.build();


		userRepo.saveAll(List.of(user1,user2,user3));


		Book book1=Book.builder()
				.bookName("spring boot")
				.author("tejas tipre")
				.isbn("12345")
				.build();

		Book book2=Book.builder()
				.bookName("Tailwind css")
				.author("samiksha sir")
				.isbn("123445")
				.build();

		Book book3=Book.builder()
				.bookName("angular")
				.author("pavan sir")
				.isbn("124345")
				.build();

		bookRepo.saveAll(List.of(book1,book2,book3));

		System.out.println("Data added successfully...");

	}
}
