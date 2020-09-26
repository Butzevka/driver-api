package com.butzevka.driverapi.converter;

import com.butzevka.driverapi.dto.AdviceDto;
import com.butzevka.driverapi.model.Advice;
import com.butzevka.driverapi.service.AdviceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdviceConverter {

    private final AdviceService adviceService;
    private final ModelMapper modelMapper;

    public AdviceDto convertToAdviceDto(Advice advice) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        AdviceDto adviceDto = modelMapper.map(advice, AdviceDto.class);
        return adviceDto;
    }

    public Advice convertToAdviceEntity(AdviceDto adviceDto) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Advice advice = modelMapper.map(adviceDto, Advice.class);
        return advice;
    }

}
