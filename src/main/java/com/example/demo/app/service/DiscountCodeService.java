package com.example.demo.app.service;

import com.example.demo.app.model.dto.DiscountCodeDto;
import com.example.demo.app.model.entity.DiscountCode;
import com.example.demo.app.repository.DiscountCodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.demo.app.model.factory.DiscountCodeCreator.createDiscountCode;

@Service
public class DiscountCodeService implements IDiscountCodeService {

    private static final Logger logger = LoggerFactory.getLogger(DiscountCodeService.class);

    private final DiscountCodeRepository discountCodeRepository;

    public DiscountCodeService(DiscountCodeRepository discountCodeRepository) {
        this.discountCodeRepository = discountCodeRepository;
    }

    @Override
    public Optional<DiscountCode> getById(Long id) {
        try {
            logger.info("Trying to find discountCode by id");
            return discountCodeRepository.findById(id);
        } catch (Exception e) {
            logger.error("Error while trying to get discountCode by id", e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<DiscountCode> getByCode(String code) {
        try {
            logger.info("Trying to find discountCode by code");
            return discountCodeRepository.getDiscountCodeByCode(code);
        } catch (Exception e) {
            logger.error("Error while trying to get discountCode by code", e);
            return Optional.empty();
        }
    }

    @Override
    public DiscountCode addDiscountCode(DiscountCodeDto discountCodeDto) {
        try {
            logger.info("Trying to persist discountCode");
            return discountCodeRepository.saveAndFlush(createDiscountCode(discountCodeDto));
        } catch (Exception e) {
            logger.error("Error while trying to get discountCode by code", e);
            return null;
        }
    }

    @Override
    public boolean toggleCode(List<Long> ids) {
        try {
            logger.info("Trying to persist discountCode");
            List<DiscountCode> discountCodes = discountCodeRepository.getDiscountCodesByIdIn(ids);
            discountCodes = discountCodes.stream().peek(discountCode -> discountCode.setActive(!discountCode.isActive())).toList();
            discountCodeRepository.saveAllAndFlush(discountCodes);
            return true;
        } catch (Exception e) {
            logger.error("Error while trying to toggle discountCode", e);
            return false;
        }
    }
}
