package com.yuval.coupons.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.yuval.coupons.dto.PurchaseDto;
import com.yuval.coupons.dto.PurchaseInfoDto;
import com.yuval.coupons.dto.UserLoginData;
import com.yuval.coupons.exceptions.ApplicationException;
import com.yuval.coupons.logic.PurchasesController;

@RestController
@RequestMapping("/purchases")
public class PurchasesApi {

	@Autowired
	private PurchasesController purchasesController;

	@PostMapping
	public void createPurchase(@RequestBody PurchaseDto purchase, HttpServletRequest request)
			throws ApplicationException {
		// Collecting the userId from the cache in order to avoid hacking attempts.
		UserLoginData data = (UserLoginData) request.getAttribute("userLoginData");
		purchase.setUserId(data.getUserId());
		this.purchasesController.addPurchase(purchase);
	}

	@DeleteMapping("/{purchaseId}")
	public void deletePurchase(@PathVariable("purchaseId") long id) throws ApplicationException {
		this.purchasesController.deletePurchase(id);
	}

	@GetMapping("/{purchaseId}")
	public PurchaseInfoDto getPurchase(@PathVariable("purchaseId") long id) throws ApplicationException {
		PurchaseInfoDto purchase = this.purchasesController.getPurchase(id);
		return purchase;
	}

	@GetMapping
	public List<PurchaseInfoDto> getAllPurchases() throws ApplicationException {
		List<PurchaseInfoDto> purchases = this.purchasesController.getAllPurchases();
		return purchases;
	}

	@GetMapping("/byUserId")
	public List<PurchaseInfoDto> getPurchasesByUser(HttpServletRequest request) throws ApplicationException {
		// Collecting the userId from the cache in order to avoid hacking attempts.
		UserLoginData data = (UserLoginData) request.getAttribute("userLoginData");
		long userId = data.getUserId();
		List<PurchaseInfoDto> purchases = this.purchasesController.getAllPurchasesByUser(userId);
		return purchases;
	}

	@GetMapping("/companyId")
	public List<PurchaseInfoDto> getPurchasesByCompanyId(@RequestParam("companyId") long companyId)
			throws ApplicationException {
		List<PurchaseInfoDto> purchases = this.purchasesController.getAllCompanyPurchases(companyId);
		return purchases;
	}

}
