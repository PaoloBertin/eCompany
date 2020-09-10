package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.Product;
import it.opensource.ecompany.domain.Ware;
import it.opensource.ecompany.domain.Warehouse;
import it.opensource.ecompany.repository.WaresRepository;
import it.opensource.ecompany.service.WaresService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Transactional
@Service("waresService")
public class WaresServiceImpl implements WaresService {

    private final WaresRepository waresRepository;

    public WaresServiceImpl(WaresRepository waresRepository) {

        this.waresRepository = waresRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Ware getWareInWarehouse(Long wareId, Long warehouseId) {

        Ware ware = waresRepository.findByIdAndWarehousesWarehouseid(wareId, warehouseId);

        return ware;
    }

    @Transactional(readOnly = true)
    @Override
    public long getNumberWares() {

        return waresRepository.count();
    }

    @Override
    public Page<Ware> getAllWaresInWarehouseByPage(Collection<Long> warehouseId, Pageable pageable) {

        return waresRepository.findByWarehousesWarehouseidIn(warehouseId, pageable);
    }

    public List<Ware> getByWarehousesWarehouseidIn(Collection<Long> warehouseId){

        return waresRepository.findByWarehousesWarehouseidIn(warehouseId);
    }

    @Override
    public List<Ware> getBySkuAndWarehousesIn(String sku, Collection<Long> warehouseId) {

        return waresRepository.findBySkuAndWarehousesWarehouseidIn(sku, warehouseId);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Ware> getWaresByCategoryCategoryid(Long warehouseId, Long categoryId, Pageable pageable) {

        return waresRepository.findByWarehousesWarehouseidAndProductCategoryCategoryid(warehouseId, categoryId, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Ware> getByWarehousesWarehouseidAndProductName(Long warehouseId, String name, Pageable pageable) {

        return waresRepository.findByWarehousesWarehouseidAndProductName(warehouseId, name, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Ware> getByWarehouseWarehouseidAndProductNameContaining(Long warehouseId, String searchText, Pageable pageable) {

        Page<Ware> wares = waresRepository.findByWarehousesWarehouseidAndProductNameContaining(warehouseId, searchText, pageable);

        return wares;
    }

    @Override
    public Ware getWareBySku(String sku) {

        return waresRepository.findBySku(sku);
    }

    @Override
    public Integer productsWithdrawalFromWarehouse(Long warehouseId, Long productId, Integer quantity) {


        return null;
    }

    @Override
    public Integer productsDeliveryFromWarehouse(Long warehouseId, Long productId, Integer quantity) {

        return null;
    }
}
