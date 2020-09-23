package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.Product;
import it.opensource.ecompany.domain.Ware;
import it.opensource.ecompany.repository.WaresRepository;
import it.opensource.ecompany.service.Dto.WareDto;
import it.opensource.ecompany.service.WaresService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Page<Ware> getAllWaresByPage(Pageable pageable) {

        return waresRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Ware> getAllWaresInWarehouseByPage(Long warehouseId, Pageable pageable) {

        return waresRepository.findByWarehouseWarehouseid(warehouseId, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Ware> getBySkuAndWarehouse(String sku, Long warehouseId) {

        return waresRepository.findBySkuAndWarehouseWarehouseid(sku, warehouseId);
    }

    @Transactional(readOnly = true)
    @Override
    public Ware getWareBySku(String sku) {

        return waresRepository.findBySku(sku);
    }

    public List<Object[]> getWaresWithProduct() {

        List<Object[]> wares = waresRepository.findAllWaresWithProduct();

        return waresRepository.findAllWaresWithProduct();
    }

    @Override
    public Page<WareDto> getAllWaresWithProductPageable(Pageable pageable) {

        final Page<Object[]> page = waresRepository.findAllWaresPageable(pageable);
        final Page<WareDto> wareDtoPage = page.map(this::convertToWareDto);


        return wareDtoPage;
    }

    @Override
    public Page<WareDto> getAllWaresInWarehousePageable(Long warehouseId, Pageable pageable) {

        final Page<Object[]> page = waresRepository.findAllWaresInWarehousePageable(warehouseId, pageable);
        final Page<WareDto> wareDtoPage = page.map(this::convertToWareDto);

        return wareDtoPage;
    }

    private WareDto convertToWareDto(final Object[] wareProductArray) {

        final WareDto wareDto = new WareDto();

        Ware ware = (Ware) wareProductArray[0];
        Product product = (Product) wareProductArray[1];

        wareDto.setId(ware.getId());
        wareDto.setSku(ware.getSku());
        wareDto.setCost(ware.getCost());
        wareDto.setUnit(ware.getUnit());
        wareDto.setQuantity(ware.getQuantity());
        wareDto.setReorderQuantity(ware.getReorderQuantity());
        wareDto.setInventoryValue(ware.getInventoryValue());
        wareDto.setReorder(ware.getReorder());
        wareDto.setContainer(ware.getContainer());
        wareDto.setLocation(ware.getLocation());
        wareDto.setWarehouse(ware.getWarehouse());
        wareDto.setName(product.getName());
        wareDto.setIsbn(product.getIsbn());

        return wareDto;
    }
}
