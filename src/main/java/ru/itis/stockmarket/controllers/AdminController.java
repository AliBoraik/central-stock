package ru.itis.stockmarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.stockmarket.dtos.*;
import ru.itis.stockmarket.services.BankService;
import ru.itis.stockmarket.services.ContractService;
import ru.itis.stockmarket.services.OrganizationService;
import ru.itis.stockmarket.services.ProductService;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final BankService bankService;
    private final ProductService productService;
    private final OrganizationService<OrganizationRequestDto, OrganizationResponseDto> organizationService;
    private final ContractService contractService;

    @GetMapping("/bank")
    String getBanks(ModelMap model) {
        List<BankResponseDto> response = bankService.getAllOrganizations();
        model.addAttribute("list", response);
        return "bank";
    }

    @GetMapping("/organization")
    String getOrganizations(ModelMap model) {
        List<OrganizationResponseDto> response = organizationService.getAllOrganizations();
        model.addAttribute("list", response);
        return "organization";
    }

    @GetMapping("/product")
    String getProducts(ModelMap model,
                       @RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        PagedResponse<ProductResponseDto> response = productService.getAllProducts(pageable);
        model.addAttribute("pagedResponse", response);
        return "product";
    }

    @GetMapping("/contract")
    String getContracts(ModelMap model,
                        @RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        PagedResponse<ContractResponseDto> response = this.contractService.getAllContracts(pageable);
        model.addAttribute("contracts", response);
        return "contract";
    }

    @GetMapping("/*")
    String defaultAdminPage() {
        return "redirect:/admin/bank";
    }
}
