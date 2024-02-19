package com.demo.cloudinary.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileUploadResponse {

	private String asset_id;
	private String public_id;
	private Integer version;
	private String version_id;
	private String signature;
	private Integer width;
	private Integer height;
	private String format;
	private String resource_type;
	private String created_at;
	private List<Object> tags;
	private Integer bytes;
	private String type;
	private String etag;
	private String placeholder;
	private String url;
	private String secure_url;
	private String folder;
	private String original_filename;
	private String original_extension;

}
