package com.diegoperezeng.associatesvotes.services;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diegoperezeng.associatesvotes.entities.Associate;
import com.diegoperezeng.associatesvotes.repositories.AssociateRepository;
import com.diegoperezeng.associatesvotes.utils.ServiceUtils;

@Service
public class AssociateService {

	// Method created to reuse the database server messages handler and make code
	// more clean
	ServiceUtils serviceUtils = new ServiceUtils();

	private final AssociateRepository associateRepository;

	public AssociateService(AssociateRepository associateRepository) {
		this.associateRepository = associateRepository;
	}

	public List<Associate> getAllAssociates() {
		return serviceUtils.handleRepositoryCall(() -> associateRepository.findAll());
	}

	public Associate findAssociateById(Long id) {
		return serviceUtils.handleRepositoryCall(() -> associateRepository.findById(id).orElse(null));
	}

	@Transactional
	public void saveAssociate(String name, String email) throws ConstraintViolationException {
		Associate associate = new Associate();
		associate.setName(name);
		associate.setEmail(email);

		serviceUtils.handleRepositoryCall(() -> associateRepository.save(associate));

	}
}
