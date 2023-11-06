package com.pichincha.clients.domain.error;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class Error implements Serializable {

	private static final long serialVersionUID = -8313092981322430433L;

	private String message;
	private List<String> errors;
}