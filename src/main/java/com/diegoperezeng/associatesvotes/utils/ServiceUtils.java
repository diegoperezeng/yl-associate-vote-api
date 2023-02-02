// Method created to reuse the database server messages handler with repositories JPA calls
//
// - Usage: <Instance_of_Service_Utils>.handleRepositoryCall(() -> <JPA_repository_instance>.<JPA method>);
// - Example: serviceUtils.handleRepositoryCall(() -> associateRepository.findAll());
//
// By diegoperezeng

package com.diegoperezeng.associatesvotes.utils;

import java.util.function.Supplier;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

public class ServiceUtils {
	public <T> T handleRepositoryCall(Supplier<T> repositoryCall) throws ConstraintViolationException {
        try {
            return repositoryCall.get();
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof ConstraintViolationException) {
                ConstraintViolationException constraintException = (ConstraintViolationException) e.getCause();
                throw constraintException;
            } else {
                throw e;
            }
        }
    }
}
