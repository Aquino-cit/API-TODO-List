package com.TODOList2.TODOList2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class TodoList2Application {

	public static void main(String[] args) {
		SpringApplication.run(TodoList2Application.class, args);
	}
}
