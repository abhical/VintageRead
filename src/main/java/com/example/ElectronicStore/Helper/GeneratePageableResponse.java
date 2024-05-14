package com.example.ElectronicStore.Helper;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import com.example.ElectronicStore.Dtos.PageableResponse;

public class GeneratePageableResponse {

	public static <U, V> PageableResponse<V> getPageableResponse(Page <U> page,Class<V> type) {
		List<U> entity = page.getContent();
		ModelMapper modelMapper = new ModelMapper();  // Create only one ModelMapper instance
		List<V> dtoList = entity.stream()
                .map(object -> modelMapper.map(object, type))
                .collect(Collectors.toList());
		PageableResponse<V>response=new PageableResponse<>();
		response.setContent(dtoList);
		response.setPageNumber(page.getNumber()+1);
		response.setPageSize(page.getSize());
		response.setTotalElement(page.getNumberOfElements());
		response.setTotalPage(page.getTotalPages());
		response.setLast(page.isLast());
		return response;

	}

}
