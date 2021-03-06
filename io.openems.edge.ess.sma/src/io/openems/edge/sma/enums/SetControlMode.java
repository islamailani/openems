package io.openems.edge.sma.enums;

import io.openems.edge.common.channel.doc.OptionsEnum;

public enum SetControlMode implements OptionsEnum {
	UNDEFINED(-1, "Undefined"), //
	START(802, "START"), //
	STOP(803, "STOP");

	private final int value;
	private final String name;

	private SetControlMode(int value, String name) {
		this.value = value;
		this.name = name;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public OptionsEnum getUndefined() {
		return UNDEFINED;
	}
}