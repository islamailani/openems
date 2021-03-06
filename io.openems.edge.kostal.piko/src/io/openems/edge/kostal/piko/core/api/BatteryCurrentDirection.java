package io.openems.edge.kostal.piko.core.api;

import io.openems.edge.common.channel.doc.OptionsEnum;

public enum BatteryCurrentDirection implements OptionsEnum {
	UNDEFINED(-1, "Undefined"), //
	CHARGE(0, "Charge"), //
	DISCHARGE(1, "Discharge");

	private final int value;
	private final String name;

	private BatteryCurrentDirection(int value, String name) {
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