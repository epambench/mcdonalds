package com.bench.mac.model;

import com.bench.mac.constants.HungryStates;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(exclude = { "queueNumber", "hungryState" })
@EqualsAndHashCode
public class Client {

	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private int order;
	@Getter
	@Setter
	private long eatTime;
	@Getter
	@Setter
	private HungryStates hungryState;
	@Getter
	@Setter
	private int queueNumber;

}
