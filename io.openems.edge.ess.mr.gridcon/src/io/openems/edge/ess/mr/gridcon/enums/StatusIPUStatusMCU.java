package io.openems.edge.ess.mr.gridcon.enums;

import io.openems.edge.common.channel.doc.OptionsEnum;

public enum StatusIPUStatusMCU implements OptionsEnum {
	UNDEFINED(-1, "Undefined"), //
	FAN_MONITOR(1, "Fan monitor"), //
	HARD_TRIP(2, "Hard trip"), //
	WAIT_FOR_ENABLE(3, "Wait for enable"), //
	IDLE(4, "Idle"), //
	WAIT_RPT(5, "Wait RPT"), //
	PRECHARGE(6, "Precharge wait for voltage"), //
	TRIP_PRECHG(7, "Trip, prechg"), //
	PRECHG_WAIT(8, "Prechg wait"), //
	PRECHG_CANCELED(9, "Prechg cancelled"), //
	HS_ON(10, "Hs on"), //
	HS_ON_POST(11, "Ha on post"), //
	READY(12, "Ready extern"), //
	READY_INTERN(13, "Ready intern"), //
	RUN(14, "Run extern"), //
	RUN_INTERN(15, "Run intern"), //
	UNKNOWN_STATE_18(18, "Unknown State"), //
	UNKNOWN_STATE_21(21, "Unknown State");

	private final int value;
	private final String name;

	private StatusIPUStatusMCU(int value, String name) {
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