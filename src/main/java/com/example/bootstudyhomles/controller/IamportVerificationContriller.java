package com.example.bootstudyhomles.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.Util.ImportResponseWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

//아임포트 검증
@Controller
@RequestMapping("/iamport/")
public class IamportVerificationContriller {

	private IamportClient api;

	public IamportVerificationContriller() {
		// REST API 키와 REST API secret 를 아래처럼 순서대로 입력한다.
		this.api = new IamportClient("3093180756470691",
				"63a1b0983ec8a5d7e6d0681cdb413b635f2484928aef777ccb0d7265557cd711cf2a538f28135545");
	}

	@ResponseBody
	@RequestMapping(value = "/verifyIamport/{imp_uid}", produces = "application/json")
	public IamportResponse<Payment> paymentByImpUid(Model model, Locale locale, HttpSession session,
			@PathVariable(value = "imp_uid") String imp_uid) throws IamportResponseException, IOException {

		return api.paymentByImpUid(imp_uid);

	}


}
