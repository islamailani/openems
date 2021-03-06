package io.openems.edge.controller.api.backend.slidingvalue;

import io.openems.common.types.OpenemsType;

public class ShortSlidingValue extends AbstractNumberSlidingValue<Short> {

	@Override
	protected Short add(Short a, Short b) {
		return (short) (a + b);
	}

	protected OpenemsType getType() {
		return OpenemsType.SHORT;
	}

}
