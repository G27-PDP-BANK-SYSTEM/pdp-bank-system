package org.example.atm.atm;

import org.example.atm.atm.entity.ATM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface ATMRepository extends JpaRepository<ATM, UUID>
{
}
