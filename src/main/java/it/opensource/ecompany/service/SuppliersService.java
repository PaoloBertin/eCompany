package it.opensource.ecompany.service;

import it.opensource.ecompany.domain.Supplier;

import java.util.List;

public interface SuppliersService {

    public List<Supplier> getAllSuppliers();

    public Supplier getSupplierById(Long supplierId);

    public Supplier getSupplierByEmail(String email);

    public Supplier getSupplierByUsername(String username);

    public Supplier getSupplierByUsernameAndPassword(String username, String password);

    public Supplier saveSupplier(final Supplier supplier);
}
