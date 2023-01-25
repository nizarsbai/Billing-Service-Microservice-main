package emsi.nizar.billingservice.web;

import emsi.nizar.billingservice.Repository.BillRepository;
import emsi.nizar.billingservice.Repository.ProductItemsRepository;
import emsi.nizar.billingservice.entities.Bill;
import emsi.nizar.billingservice.feign.CustomerRestClient;
import emsi.nizar.billingservice.feign.ProductItemsRestClient;
import emsi.nizar.billingservice.models.Customer;
import emsi.nizar.billingservice.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingRestController {
    private ProductItemsRepository productItemsRepository;
    private BillRepository billRepository;
    private CustomerRestClient customerRestClient;
    private ProductItemsRestClient productItemsRestClient;
    public BillingRestController(ProductItemsRepository productItemsRepository, BillRepository billRepository, CustomerRestClient customerRestClient, ProductItemsRestClient productItemsRestClient) {
        this.productItemsRepository = productItemsRepository;
        this.billRepository = billRepository;
        this.customerRestClient = customerRestClient;
        this.productItemsRestClient = productItemsRestClient;
    }
    @GetMapping(path = "/fullBills/{id}")
    public Bill getBill(@PathVariable(name = "id") Long id) {
        Bill bill = billRepository.findById(id).get();
        Customer customer = customerRestClient.getCustomerById(bill.getCustomerID());
        bill.setCustomer(customer);
        //bill.setProductItems(productItemsRepository.findByBillId(id));
        bill.getProductItems().forEach(pi -> {
            Product product = productItemsRestClient.getProductById(pi.getProductID());
            pi.setProduct(product);
            pi.setProductName(product.getName());

        });
        return bill;
    }
}
