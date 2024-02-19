package com.demo.cloudinary.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileSearchResponse {
	
	private String asset_id;
	private String public_id;
	private String format;
	private Integer version;
	private String resource_type;
	private String type;
	private String created_at;
	private Integer bytes;
	private Integer width;
	private Integer height;
	private String folder;
	private String url;
	private String secure_url;
	
}
