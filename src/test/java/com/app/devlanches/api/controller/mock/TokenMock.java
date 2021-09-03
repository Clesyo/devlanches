package com.app.devlanches.api.controller.mock;

import com.app.devlanches.api.models.dto.TokenDTO;

public class TokenMock {

	public TokenDTO token() {
		return new TokenDTO("clesyo", "223");
	}
}
