package org.example.pdpbanksystem.accNumber;

import org.example.pdpbanksystem.accNumber.entity.AccountNumber;
import org.example.pdpbanksystem.common.repository.GenericSpecificationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountNumberRepository extends GenericSpecificationRepository<AccountNumber,Integer> {
}
