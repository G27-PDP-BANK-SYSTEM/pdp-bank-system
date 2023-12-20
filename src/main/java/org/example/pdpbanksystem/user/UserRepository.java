package org.example.pdpbanksystem.user;

import org.example.pdpbanksystem.common.repository.GenericSpecificationRepository;
import org.example.pdpbanksystem.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends GenericSpecificationRepository<User, Integer> {
    Optional<User> findUserByEmail(String email);
}

