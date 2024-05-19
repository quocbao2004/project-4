package com.javaweb.controller.admin;

import com.javaweb.enums.Status;
import com.javaweb.enums.TransactionType;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.CustomerService;
import com.javaweb.service.TransactionService;
import com.javaweb.service.impl.UserService;
import com.javaweb.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Controller(value="customerControllerOfAdmin")
@ControllerAdvice
public class CustomerController {
    @Autowired
    private MessageUtils messageUtil;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionService transactionService;

    private void setResponse(CustomerSearchResponse customerSearchResponse, CustomerSearchRequest customerSearchRequest){
        List<CustomerSearchResponse>findAll = customerService.findAll(customerSearchRequest , PageRequest.of(1, 10));
        customerSearchResponse.setListResult(findAll);
        customerSearchResponse.setTotalItems((int)customerService.countTotalItems(customerSearchRequest));
    }

    @RequestMapping(value = "/admin/customer-list", method = RequestMethod.GET)
    public ModelAndView customerList(@ModelAttribute CustomerSearchRequest customerSearchRequest, HttpServletRequest request, Exception ex) {
        ModelAndView mav = new ModelAndView("admin/customer/list");
        CustomerSearchResponse customerSearchResponse = new CustomerSearchResponse();
        if(SecurityUtils.getAuthorities().contains("ROLE_STAFF")) {
            Long staffid = SecurityUtils.getPrincipal().getId();
            customerSearchRequest.setStaffId(staffid);
            setResponse(customerSearchResponse, customerSearchRequest);
            mav.addObject("customerList", customerSearchResponse);
        }
        else {
            setResponse(customerSearchResponse, customerSearchRequest);
            mav.addObject("customerList", customerSearchResponse);
        }
        mav.addObject("status", Status.StatusType());
        mav.addObject("modelSearchCustomer", customerSearchRequest);
        mav.addObject("listStaffs", userService.getStaffs());
        return mav;
    }
    @RequestMapping(value = "/admin/customer-edit", method = RequestMethod.GET)
    public ModelAndView customerEdit(@ModelAttribute CustomerDTO customerDTO,  HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject("customerEdit", customerDTO);
        return mav;
    }
    @RequestMapping(value = "/admin/customer-edit-{id}", method = RequestMethod.GET)
    public ModelAndView customerEdit(@PathVariable("id") Long Customerid, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        if(Customerid != null){
            CustomerDTO customerDTO = customerService.getOneCustomerEdit(Customerid);
            List<TransactionDTO> transactionDTOCSKH = transactionService.getTransactionEditCSKH(Customerid);
            List<TransactionDTO> transactionDTODDX = transactionService.getTransactionEditDDX(Customerid);
            mav.addObject("customerEdit", customerDTO);
            mav.addObject("transactionEdit1", transactionDTOCSKH);
            mav.addObject("transactionEdit2", transactionDTODDX);
        }
        mav.addObject("status", Status.StatusType());
        mav.addObject("transactionType", TransactionType.transactionType());
        return mav;
    }

    @RequestMapping(value = "/lien-he", method = RequestMethod.POST)
    public ResponseEntity<?> contact(@ModelAttribute CustomerDTO customer){
        ModelAndView mav = new ModelAndView("/web/contact");
        if(customer.getPhone() != null && customer.getFullname() != null){
            customerService.AddOrUpdateCustomer(customer);
        }
        return ResponseEntity.ok("");
    }
}