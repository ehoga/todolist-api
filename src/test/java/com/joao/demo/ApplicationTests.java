package com.joao.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.joao.demo.entity.Todo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ApplicationTests {
//	@Autowired
//	private WebTestClient webTestClient;
//
//	@Test
//	void testCreateTodoSuccess() {
//		var todo = new Todo("Todo 1","desc todo 1", false, 1);
//
//		webTestClient
//		.post()
//		.uri("/todos")
//		.bodyValue(todo)
//		.exchange()
//		.expectStatus().isOk()
//		.expectBody()
//		.jsonPath("$").isArray()
//		.jsonPath("$.length()").isEqualTo(1)
//		.jsonPath("$[0].title").isEqualTo(todo.getTitle())
//		.jsonPath("$[0].description").isEqualTo(todo.getDescription())
//		.jsonPath("$[0].done").isEqualTo(todo.getDone())
//		.jsonPath("$[0].priority").isEqualTo(todo.getPriority());
//	}
//
//	@Test
//	void testCreateTodoFailure() {
//		var todo = new Todo( "","", false, 0);
//
//		webTestClient
//		.post()
//		.uri("/todos")
//		.bodyValue(todo)
//		.exchange()
//		.expectStatus().isBadRequest();
//
//	}
//
//	@Test
//	void testGetAllTodos() {
//		var todo = new Todo( "Todo 1","desc todo 1", false, 1);
//
//		webTestClient.post()
//		.uri("/todos")
//		.bodyValue(todo)
//		.exchange();
//
//		webTestClient
//			.get()
//			.uri("/todos")
//			.exchange()
//			.expectStatus().isOk()
//			.expectBody()
//			.jsonPath("$").isArray()
//			.jsonPath("$.length()").isEqualTo(1);
//	}
//
//	@Test
//	void testDeleteTodoSuccess() {
//		var todo = new Todo( "Todo 1","desc todo 1", false, 1);
//
//		var created = webTestClient
//			.post()
//			.uri("/todos")
//			.bodyValue(todo)
//			.exchange()
//			.expectStatus().isOk()
//			.expectBodyList(Todo.class)
//			.returnResult()
//			.getResponseBody()
//			.get(0);
//
//		webTestClient
//			.delete()
//			.uri("/todos/" + created.getId())
//			.exchange()
//			.expectStatus().isOk();
//
//	}


}
