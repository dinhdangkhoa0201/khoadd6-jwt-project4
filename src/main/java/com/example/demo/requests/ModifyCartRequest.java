package com.example.demo.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ModifyCartRequest {

	@JsonProperty
	private long itemId;

	@JsonProperty
	private String username;

	@JsonProperty
	private int quantity;
}
