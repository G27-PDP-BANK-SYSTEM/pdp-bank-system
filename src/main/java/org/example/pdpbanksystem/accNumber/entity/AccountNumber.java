package org.example.pdpbanksystem.accNumber.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.pdpbanksystem.user.entity.User;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "`AccountNumber`")
public class AccountNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String accountNumber;

    @OneToOne(mappedBy = "accountNumber", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;


}
