package com.solutionsdelivery.GetStatus.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "MERCHANTS")
public @Data
class Merchants {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "MERCHANTID")
	private String merchantId;

	@Column(name = "MERCHANTNAME")
	private String merchantName;

	@Column(name = "APIKEY")
	private String apiKey;

	@Column(name = "DESCRIPTION")
	private String description;

}
