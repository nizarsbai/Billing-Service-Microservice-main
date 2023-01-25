package emsi.nizar.billingservice.entities;

import emsi.nizar.billingservice.models.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date billingDate;
    @OneToMany(mappedBy = "bill")
    private Collection<ProductItems> productItems;
    private Long customerID;
    @Transient
    private Customer customer;



}
