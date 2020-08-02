package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.Supplier;
import it.opensource.ecompany.repository.SuppliersRepository;
import it.opensource.ecompany.service.SuppliersService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("suppliersService")
public class SuppliersServiceImpl implements SuppliersService {

    private final SuppliersRepository suppliersRepository;

    public SuppliersServiceImpl(SuppliersRepository suppliersRepository) {

        this.suppliersRepository = suppliersRepository;
    }

    @Override
    public List<Supplier> getAllSuppliers() {

        return suppliersRepository.findAll();
    }

    @Override
    public Supplier getSupplierById(Long supplierId) {

        return suppliersRepository.findById(supplierId).orElse(null);
    }

    @Override
    public Supplier getSupplierByEmail(String email) {

        return suppliersRepository.findByContactEmail(email);
    }

    @Override
    public Supplier getSupplierByUsername(String username) {

        return null;
    }

    @Override
    public Supplier getSupplierByUsernameAndPassword(String username, String password) {

        return null;
    }

    @Override
    public Supplier save(Supplier supplier) {

        return suppliersRepository.save(supplier);
    }
}
