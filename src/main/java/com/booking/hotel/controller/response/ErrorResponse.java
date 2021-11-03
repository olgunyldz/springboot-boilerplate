package com.booking.hotel.controller.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {

	private final String errorCode;
	private final String errorText;
}
