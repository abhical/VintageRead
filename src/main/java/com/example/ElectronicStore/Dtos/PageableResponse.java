package com.example.ElectronicStore.Dtos;

import java.util.*;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PageableResponse<T> {
	private List<T> content;
	private int pageNumber;
	private int pageSize;
	private long totalElement;
	private int totalPage;
	private boolean isLast;

}
