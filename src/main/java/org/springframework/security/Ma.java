package org.springframework.security;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @see java.util.Date
 */
public class Ma {

	public static void main(String[] args) throws JsonProcessingException {

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());

		// Thu Aug 08 03:26:50 +0800 2013

		A a = new A();
		a.setCreatedAt(LocalDateTime.now());

		String s = objectMapper.writeValueAsString(a);
		System.out.println(s);

	}

	@Data
	public static class A {

		// @JsonFormat()
		@JsonProperty("created_at")
		private LocalDateTime createdAt;

	}

}
