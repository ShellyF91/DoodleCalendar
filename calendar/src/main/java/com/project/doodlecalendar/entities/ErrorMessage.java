package com.project.doodlecalendar.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class ErrorMessage {

	private String message;
	private Object data;
}