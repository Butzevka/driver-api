package com.butzevka.driverapi.service;

import com.butzevka.driverapi.dto.AdviceDto;
import com.butzevka.driverapi.model.Advice;
import com.butzevka.driverapi.model.Training;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MapService {

    private final AdviceService adviceService;
    private final ModelMapper modelMapper;

    private AdviceDto convertToAdviceDto(Advice advice) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        AdviceDto adviceDto = modelMapper.map(advice, AdviceDto.class);
        return adviceDto;
    }

    public List<AdviceDto> findAllAdviceArticles() {
        return (adviceService.findAllAdvices())
                .stream()
                .map(this::convertToAdviceDto)
                .collect(Collectors.toList());
    }

    public Optional<AdviceDto> findAdviceArticleById(Long id) {
        Optional<Advice> adviceModel = adviceService.findById(id);
        return adviceModel.map(this::convertToAdviceDto);
    }


}
