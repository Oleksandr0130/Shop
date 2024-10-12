package de.ait_tr.shop.service.mapping;

import de.ait_tr.shop.model.dto.CartDTO;
import de.ait_tr.shop.model.entity.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CartMappingService.class)
public interface CartMappingService {

    Cart mapDtoToEntity(CartDTO dto);
    CartDTO mapEntityToDto(Cart entity);
}
