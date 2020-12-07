package it.opensource.ecompany.service.impl;

import it.opensource.ecompany.domain.LineItem;
import it.opensource.ecompany.repository.LineItemRepository;
import it.opensource.ecompany.service.LineItemService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("lineItemService")
public class LineItemServiceImpl implements LineItemService {

    private final LineItemRepository lineItemRepository;

    public LineItemServiceImpl(LineItemRepository lineItemRepository) {

        this.lineItemRepository = lineItemRepository;
    }

    @Override
    public Optional<LineItem> getLineItemById(Long lineItemId) {

        return lineItemRepository.findById(lineItemId);
    }

}
