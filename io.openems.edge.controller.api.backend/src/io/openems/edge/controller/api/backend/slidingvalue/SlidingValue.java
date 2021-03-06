package io.openems.edge.controller.api.backend.slidingvalue;

import java.util.Objects;

import com.google.gson.JsonElement;

import io.openems.common.types.OpenemsType;
import io.openems.edge.common.type.TypeUtils;

public abstract class SlidingValue<T> {

	private T lastSentValue = null;

	/**
	 * Adds a value.
	 * 
	 * @param value the value
	 */
	public abstract void addValue(T value);

	/**
	 * Gets the sliding value, e.g. the average of all values.
	 * 
	 * @return the sliding value
	 */
	protected abstract T getSlidingValue();

	/**
	 * Resets the values.
	 */
	protected abstract void resetValues();

	/**
	 * Gets the OpenemsType of this SlidingValue.
	 * 
	 * @return the OpenemsType
	 */
	protected abstract OpenemsType getType();

	/**
	 * Gets the value as a JsonElement if it changed. Resets the values.
	 * 
	 * @return the value; or null if it had not changed
	 */
	public JsonElement getChangedValueOrNull() {
		T value = this.getSlidingValue();
		this.resetValues();
		if (Objects.equals(this.lastSentValue, value)) {
			return null;
		}
		return TypeUtils.getAsJson(this.getType(), value);
	}

	/**
	 * Gets the value as a JsonElement. Resets the values.
	 * 
	 * @return the value; null if is null
	 */
	public JsonElement getValue() {
		T value = this.getSlidingValue();
		this.resetValues();
		return TypeUtils.getAsJson(this.getType(), value);
	}

}