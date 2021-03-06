package io.openems.edge.sma;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import io.openems.edge.common.channel.AbstractReadChannel;
import io.openems.edge.common.channel.EnumReadChannel;
import io.openems.edge.common.channel.IntegerReadChannel;
import io.openems.edge.common.channel.IntegerWriteChannel;
import io.openems.edge.common.channel.LongReadChannel;
import io.openems.edge.common.channel.StateChannel;
import io.openems.edge.common.channel.StateCollectorChannel;
import io.openems.edge.common.component.OpenemsComponent;
import io.openems.edge.common.sum.GridMode;
import io.openems.edge.ess.api.AsymmetricEss;
import io.openems.edge.ess.api.ManagedAsymmetricEss;
import io.openems.edge.ess.api.ManagedSinglePhaseEss;
import io.openems.edge.ess.api.ManagedSymmetricEss;
import io.openems.edge.ess.api.SinglePhaseEss;
import io.openems.edge.ess.api.SymmetricEss;

public class Utils {
	public static Stream<? extends AbstractReadChannel<?>> initializeChannels(SunnyIsland6Ess c) {
		List<AbstractReadChannel<?>> result = new ArrayList<>();
		for (OpenemsComponent.ChannelId channelId : OpenemsComponent.ChannelId.values()) {
			switch (channelId) {
			case STATE:
				result.add(new StateCollectorChannel(c, channelId));
				break;
			}
		}
		for (SymmetricEss.ChannelId channelId : SymmetricEss.ChannelId.values()) {
			switch (channelId) {
			case SOC:
			case ACTIVE_POWER:
			case REACTIVE_POWER:
				result.add(new IntegerReadChannel(c, channelId));
				break;
			case MAX_APPARENT_POWER:
				result.add(new IntegerReadChannel(c, channelId, SunnyIsland6Ess.MAX_APPARENT_POWER));
				break;
			case GRID_MODE:
				result.add(new EnumReadChannel(c, channelId, GridMode.UNDEFINED));
				break;
			case ACTIVE_DISCHARGE_ENERGY:
			case ACTIVE_CHARGE_ENERGY:
				result.add(new LongReadChannel(c, channelId));
				break;
			}
		}
		for (ManagedSymmetricEss.ChannelId channelId : ManagedSymmetricEss.ChannelId.values()) {
			switch (channelId) {
			case DEBUG_SET_ACTIVE_POWER:
			case DEBUG_SET_REACTIVE_POWER:
			case ALLOWED_CHARGE_POWER:
			case ALLOWED_DISCHARGE_POWER:
				result.add(new IntegerReadChannel(c, channelId));
				break;
			case SET_ACTIVE_POWER_EQUALS:
			case SET_REACTIVE_POWER_EQUALS:
			case SET_ACTIVE_POWER_LESS_OR_EQUALS:
			case SET_ACTIVE_POWER_GREATER_OR_EQUALS:
			case SET_REACTIVE_POWER_LESS_OR_EQUALS:
			case SET_REACTIVE_POWER_GREATER_OR_EQUALS:
				result.add(new IntegerWriteChannel(c, channelId));
				break;
			case APPLY_POWER_FAILED:
				result.add(new StateChannel(c, channelId));
				break;
			}
		}
		for (AsymmetricEss.ChannelId channelId : AsymmetricEss.ChannelId.values()) {
			switch (channelId) {
			case ACTIVE_POWER_L1:
			case ACTIVE_POWER_L2:
			case ACTIVE_POWER_L3:
			case REACTIVE_POWER_L1:
			case REACTIVE_POWER_L2:
			case REACTIVE_POWER_L3:
				result.add(new IntegerReadChannel(c, channelId));
				break;
			}
		}
		for (ManagedAsymmetricEss.ChannelId channelId : ManagedAsymmetricEss.ChannelId.values()) {
			switch (channelId) {
			case DEBUG_SET_ACTIVE_POWER_L1:
			case DEBUG_SET_REACTIVE_POWER_L1:
			case DEBUG_SET_ACTIVE_POWER_L2:
			case DEBUG_SET_REACTIVE_POWER_L2:
			case DEBUG_SET_ACTIVE_POWER_L3:
			case DEBUG_SET_REACTIVE_POWER_L3:
				result.add(new IntegerReadChannel(c, channelId));
				break;
			case SET_ACTIVE_POWER_L1_EQUALS:
			case SET_ACTIVE_POWER_L2_EQUALS:
			case SET_ACTIVE_POWER_L3_EQUALS:
			case SET_REACTIVE_POWER_L1_EQUALS:
			case SET_REACTIVE_POWER_L2_EQUALS:
			case SET_REACTIVE_POWER_L3_EQUALS:
			case SET_ACTIVE_POWER_L1_LESS_OR_EQUALS:
			case SET_ACTIVE_POWER_L2_LESS_OR_EQUALS:
			case SET_ACTIVE_POWER_L3_LESS_OR_EQUALS:
			case SET_REACTIVE_POWER_L1_LESS_OR_EQUALS:
			case SET_REACTIVE_POWER_L2_LESS_OR_EQUALS:
			case SET_REACTIVE_POWER_L3_LESS_OR_EQUALS:
			case SET_ACTIVE_POWER_L1_GREATER_OR_EQUALS:
			case SET_ACTIVE_POWER_L2_GREATER_OR_EQUALS:
			case SET_ACTIVE_POWER_L3_GREATER_OR_EQUALS:
			case SET_REACTIVE_POWER_L1_GREATER_OR_EQUALS:
			case SET_REACTIVE_POWER_L2_GREATER_OR_EQUALS:
			case SET_REACTIVE_POWER_L3_GREATER_OR_EQUALS:
				result.add(new IntegerWriteChannel(c, channelId));
				break;
			}
		}
		for (SinglePhaseEss.ChannelId channelId : SinglePhaseEss.ChannelId.values()) {
			switch (channelId) {
			}
		}
		for (ManagedSinglePhaseEss.ChannelId channelId : ManagedSinglePhaseEss.ChannelId.values()) {
			switch (channelId) {
			}
		}
		for (SiChannelId channelId : SiChannelId.values()) {
			switch (channelId) {
			case SYSTEM_STATE:
			case BATTERY_TEMPERATURE:
			case BATTERY_VOLTAGE:
			case FREQUENCY:
			case OPERATING_MODE_FOR_ACTIVE_POWER_LIMITATION:
			case OPERATING_MODE_FOR_REACTIVE_POWER:
			case ABSORBED_ENERGY:
			case AMP_HOURS_COUNTER_FOR_BATTERY_CHARGE:
			case AMP_HOURS_COUNTER_FOR_BATTERY_DISCHARGE:
			case DEVICE_CLASS:
			case DEVICE_TYPE:
			case ENERGY_CONSUMED_FROM_GRID:
			case ENERGY_FED_INTO_GRID:
			case FAULT_CORRECTION_MEASURE:
			case GRID_FEED_IN_COUNTER_READING:
			case GRID_REFERENCE_COUNTER_READING:
			case MESSAGE:
			case METER_READING_CONSUMPTION_METER:
			case NUMBER_OF_EVENT_FOR_INSTALLER:
			case NUMBER_OF_EVENT_FOR_SERVICE:
			case NUMBER_OF_EVENT_FOR_USER:
			case NUMBER_OF_GENERATORS_STARTS:
			case NUMBER_OF_GRID_CONNECTIONS:
			case POWER_OUTAGE:
			case RECOMMENDED_ACTION:
			case RELEASED_ENERGY:
			case RISE_IN_SELF_CONSUMPTION:
			case RISE_IN_SELF_CONSUMPTION_TODAY:
			case SOFTWARE_PACKAGE:
			case WAITING_TIME_UNTIL_FEED_IN:
			case ACTIVE_POWER_L1:
			case ACTIVE_POWER_L2:
			case ACTIVE_POWER_L3:
			case GRID_VOLTAGE_L1:
			case GRID_VOLTAGE_L2:
			case GRID_VOLTAGE_L3:
			case REACTIVE_POWER_L1:
			case REACTIVE_POWER_L2:
			case REACTIVE_POWER_L3:
			case COSPHI_SET_POINT_READ:
			case ACTIVE_BATTERY_CHARGING_MODE:
			case BATTERY_MAINT_SOC:
			case CURRENT_BATTERY_CAPACITY:
			case NUMBER_OF_BATTERY_CHARGE_THROUGHPUTS:
			case CURRENT_RISE_IN_SELF_CONSUMPTION:
			case LOAD_POWER:
			case POWER_GRID_FEED_IN:
			case POWER_GRID_REFERENCE:
			case PV_POWER_GENERATED:
			case MULTIFUNCTION_RELAY_STATUS:
			case POWER_SUPPLY_STATUS:
			case PV_MAINS_CONNECTION:
			case REASON_FOR_GENERATOR_REQUEST:
			case STATUS_OF_UTILITY_GRID:
			case CURRENT_EXTERNAL_POWER_CONNECTION_PHASE_A:
			case CURRENT_EXTERNAL_POWER_CONNECTION_PHASE_B:
			case CURRENT_EXTERNAL_POWER_CONNECTION_PHASE_C:
			case GRID_FREQ_OF_EXTERNAL_POWER_CONNECTION:
			case VOLTAGE_EXTERNAL_POWER_CONNECTION_PHASE_A:
			case VOLTAGE_EXTERNAL_POWER_CONNECTION_PHASE_B:
			case VOLTAGE_EXTERNAL_POWER_CONNECTION_PHASE_C:
			case DATA_TRANSFER_RATE_OF_NETWORK_TERMINAL_A:
			case GENERATOR_STATUS:
			case DUPLEX_MODE_OF_NETWORK_TERMINAL_A:
			case TOTAL_CURRENT_EXTERNAL_GRID_CONNECTION:
			case GRID_CURRENT_L1:
			case GRID_CURRENT_L2:
			case GRID_CURRENT_L3:
			case OUTPUT_OF_PHOTOVOLTAICS:
			case FAULT_BATTERY_SOC:
			case CHARGE_FACTOR_RATIO_OF_BATTERY_CHARGE_DISCHARGE:
			case HIGHEST_MEASURED_BATTERY_TEMPERATURE:
			case LOWER_DISCHARGE_LIMIT_FOR_SELF_CONSUMPTION_RANGE:
			case LOWEST_MEASURED_BATTERY_TEMPERATURE:
			case MAXIMUM_BATTERY_CURRENT_IN_CHARGE_DIRECTION:
			case MAXIMUM_BATTERY_CURRENT_IN_DISCHARGE_DIRECTION:
			case MAX_OCCURRED_BATTERY_VOLTAGE:
			case OPERATING_STATUS_MASTER_L1:
			case OPERATING_TIME_OF_BATTERY_STATISTICS_COUNTER:
			case REMAINING_ABSORPTION_TIME:
			case REMAINING_MIN_OPERATING_TIME_OF_GENERATOR:
			case TOTAL_OUTPUT_CURRENT_OF_SOLAR_CHARGER:
			case ABSORPTION_PHASE_ACTIVE:
			case BATTERY_TYPE:
			case CONTROL_OF_BATTERY_CHARGING_VIA_COMMUNICATION_AVAILABLE:
			case FIRMWARE_VERSION_OF_THE_LOGIC_COMPONENET:
			case FIRMWARE_VERSION_OF_THE_MAIN_PROCESSOR:
			case MAX_BATTERY_TEMPERATURE:
			case NUMBER_OF_EQALIZATION_CHARGES:
			case NUMBER_OF_FULL_CHARGES:
			case OPERATING_TIME_ENERGY_COUNT:
			case OUTPUT_EXTERNAL_POWER_CONNECTION:
			case OUTPUT_EXTERNAL_POWER_CONNECTION_L1:
			case OUTPUT_EXTERNAL_POWER_CONNECTION_L2:
			case OUTPUT_EXTERNAL_POWER_CONNECTION_L3:
			case PHOTOVOLTAIC_ENERGY_IN_SOLAR_CHARGER:
			case RATED_BATTERY_VOLTAGE:
			case REACTIVE_POWER_EXTERNAL_POWER_CONNECTION:
			case REACTIVE_POWER_EXTERNAL_POWER_CONNECTION_L1:
			case REACTIVE_POWER_EXTERNAL_POWER_CONNECTION_L2:
			case REACTIVE_POWER_EXTERNAL_POWER_CONNECTION_L3:
			case RELATIVE_BATTERY_DISCHARGING_SINCE_LAST_EQUALIZATION_CHARGE:
			case RELATIVE_BATTERY_DISCHARGING_SINCE_THE_LAST_FULL_CHARGE:
			case STATUS_BATTERY_APPLICATION_AREA:
			case STATUS_DIGITAL_INPUT:
			case TOTAL_ENERGY_PHOTOVOLTAICS:
			case TOTAL_ENERGY_PHOTOVOLTAICS_CURRENT_DAY:
			case BATTERY_NOMINAL_CAPACITY:
			case TYPE_OF_AC_SUBDISTRIBUTION:
			case COMMUNICATION_VERSION:
			case UPDATE_VERSION_OF_THE_LOGIC_COMPONENT:
			case FIRMWARE_VERSION_OF_PROTOCOL_CONVERTER:
			case HARDWARE_VERSION_OF_PROTOCOL_CONVERTER:
			case SERIAL_NUMBER_OF_THE_PROTOCOL_CONVERTER:
			case DEBUG_SET_ACTIVE_POWER:
				result.add(new IntegerReadChannel(c, channelId));
				break;
			case BATTERY_CHARGING_SOC:
			case BATTERY_DISCHARGING_SOC:
			case SPEED_WIRE_CONNECTION_STATUS_OF_NETWORK_TERMINAL_A:
			case RATED_BATTERY_CAPACITY:
			case CURRENT_BATTERY_CHARGING_SET_VOLTAGE:
			case SERIAL_NUMBER:
			case REMAINING_TIME_UNTIL_EQUALIZATION_CHARGE:
			case REMAINING_TIME_UNTIL_FULL_CHARGE:
			case CURRENT_SELF_CONSUMPTION:
				result.add(new LongReadChannel(c, channelId));
				break;
			case BMS_OPERATING_MODE:
			case MIN_SOC_POWER_ON:
			case MIN_SOC_POWER_OFF:
			case METER_SETTING:
			case SET_CONTROL_MODE:
			case SET_REACTIVE_POWER:
			case SET_ACTIVE_POWER:
			case GRID_GUARD_CODE:
			case BATTERY_BOOST_CHARGE_TIME:
			case BATTERY_EQUALIZATION_CHARGE_TIME:
			case BATTERY_FULL_CHARGE_TIME:
			case RATED_GENERATOR_CURRENT:
			case AUTOMATIC_GENERATOR_START:
			case MANUAL_GENERATOR_CONTROL:
			case GENERATOR_REQUEST_VIA_POWER_ON:
			case GENERATOR_SHUT_DOWN_LOAD_LIMIT:
			case GENERATOR_START_UP_LOAD_LIMIT:
			case GRID_CREATING_GENERATOR:
			case RISE_IN_SELF_CONSUMPTION_SWITCHED_ON:
			case CELL_CHARGE_NOMINAL_VOLTAGE_FOR_BOOST_CHARGE:
			case CELL_CHARGE_NOMINAL_VOLTAGE_FOR_EQUALIZATION_CHARGE:
			case CELL_CHARGE_NOMINAL_VOLTAGE_FOR_FLOAT_CHARGE:
			case CELL_CHARGE_NOMINAL_VOLTAGE_FOR_FULL_CHARGE:
			case VOLTAGE_MONITORING_HYSTERESIS_MAXIMUM_THRESHOLD:
			case VOLTAGE_MONITORING_HYSTERESIS_MINIMUM_THRESHOLD:
			case VOLTAGE_MONITORING_UPPER_MAXIMUM_THRESHOLD:
			case VOLTAGE_MONITORING_UPPER_MINIMUM_THRESHOLD:
			case FREQUENCY_MONITORING_HYSTERESIS_MINIMUM_THRESHOLD:
			case FREQUENCY_MONITORING_HYSTERESIS_MAXIMUM_THRESHOLD:
			case FREQUENCY_MONITORING_UPPER_MAXIMUM_THRESHOLD:
			case FREQUENCY_MONITORING_UPPER_MINIMUM_THRESHOLD:
			case MAX_BATTERY_CHARGING_CURRENT:
			case ACKNOWLEGDE_GENERATOR_ERRORS:
			case FREQUENCY_MONITORING_GENERATOR_MAXIMUM_THRESHOLD:
			case FREQUENCY_MONITORING_GENERATOR_MINIMUM_THRESHOLD:
			case NOMINAL_FREQUENCY:
			case VOLTAGE_MONITORING_GENERATOR_MAXIMUM_REVERSE_POWER:
			case VOLTAGE_MONITORING_GENERATOR_MAXIMUM_REVERSE_POWER_TRIPPING_TIME:
			case VOLTAGE_MONITORING_GENERATOR_MAXIMUM_THRESHOLD:
			case VOLTAGE_MONITORING_GENERATOR_MINIMUM_THRESHOLD:
			case ACTIVE_POWER_AT_END_POINT:
			case ACTIVE_POWER_AT_STARTING_POINT:
			case ACTIVE_POWER_GRADIENT_CONFIGURATION:
			case AVERAGE_IDLE_PERIOD_OF_GENERATOR:
			case AVERAGE_OPERATING_TIME_OF_GENERATOR:
			case AVERAGE_TIME_FOR_GENERATOR_REQUEST_VIA_POWER:
			case CONFIGURATION_OF_THE_COSPHI_END_POINT:
			case CONFIGURATION_OF_THE_COSPHI_STARTING_POINT:
			case COOLING_DOWN_TIME_OF_GENERATOR:
			case COSPHI_AT_STARTING_POINT:
			case COSPHI_AT_THE_END_POINT:
			case DIFFERENCE_BETWEEN_RESET_FREQ_AND_GRID_FREQ:
			case DIFFERENCE_BETWEEN_STARTING_FREQ_AND_GRID_FREQ:
			case GENERATOR_NOMINAL_FREQUENCY:
			case GENERATOR_REQUEST:
			case GENERATOR_REQUEST_WITH_SET_CHARGE_TYPE:
			case GRID_REQUEST_SWITCH_OFF_POWER_LIMIT:
			case GRID_REQUEST_SWITCH_ON_POWER_LIMIT:
			case GRID_REQUEST_VIA_CHARGE_TYPE:
			case GRID_REQUEST_VIA_POWER_SWITCH_ON:
			case IDLE_PERIOD_AFTER_GENERATOR_FAULT:
			case LIMIT_SOC_GENERATOR_SHUTDOWN_IN_TIME_RANGE:
			case LIMIT_SOC_GENERATOR_START_ADD_IN_TIME_RANGE:
			case LIMIT_SOC_GENERATOR_START_IN_TIME_RANGE:
			case LIMIT_SOC_GENERATOR_STOP_ADD_IN_TIME_RANGE:
			case MANUAL_CONTROL_OF_NETWORK_CONNECTION:
			case MANUAL_EQUAIZATION_CHARGE:
			case OPERATING_TIME_FOR_TIME_CONTROLLED_GENERATOR_OPERATION:
			case REACTION_TO_DIGITAL_INPUT_OF_GENERATOR_REQUEST:
			case REPETITION_CYCLE_OF_TIME_CONTROLLED_GENERATOR_OPERATION:
			case START_TIME_ADDTIONAL_TIME_RANGE_GENERATOR_REQUEST:
			case START_TIME_FOR_TIME_CONTROLLED_GENERATOR_OPERATION:
			case START_TIME_RANGE_FOR_GENERATOR_REQUEST:
			case TIME_CONTROLLED_GENERATOR_OPERATION:
			case TIME_CONTROLLED_INVERTER_OPERATION:
			case WARM_UP_TIME_OF_GENERATOR:
			case AUTOMATIC_EQUALIZATION_CHARGE:
			case AUTOMATIC_FREQUENCY_SYNCHRONIZATION:
			case AUTOMATIC_UPDATES_ACTIVATED:
			case BATTERY_SOC_FOR_PROTECTION_MODE:
			case BATTERY_SWITCH_ONLIMIT_AFTER_OVER_TEMP_SHUT_DOWN:
			case BATTERY_TEMPERATUR_COMPENSATION:
			case CLUSTER_BEHAVIOUR_WHEN_A_DEVICE_FAILS:
			case CUT_OFF_TIME_UNTIL_CONNECTION_TO_EXTERNAL_NETWORK:
			case CYCLE_TIME_FOR_EQUALIZATION_CHARGE:
			case CYCLE_TIME_FOR_FULL_CHARGE:
			case DEVICE_NAME:
			case END_TIME_OF_BATTERY_PROTECTION_MODE_LEVEL:
			case ENERGY_SAVING_MODE:
			case ENERGY_SAVING_MODE_SWITCH_ON:
			case GRID_GUARD_VERSION:
			case GRID_REQUEST_VIA_SOC_SWITCHED_ON:
			case INVERTER_NOMINAL_FREQUENCY:
			case INVERTER_NOMINAL_VOLTAGE:
			case LIMITATION_TYPE_OF_GENERATOR_CURRENT:
			case LIMIT_SOC_FOR_CONNECTION_TO_GRID:
			case LIMIT_SOC_FOR_CONNECT_TO_GRID_IN_ADD_TIME_RANGE:
			case LIMIT_SOC_FOR_DISCONNECTION_FROM_GRID:
			case LIMIT_SOC_FOR_DISCONNECT_FROM_GRID_IN_ADD_TIME_RANGE:
			case LIMIT_SOC_FOR_START_LOAD_SHEDDING_1_IN_ADD_TIME_RANGE:
			case LIMIT_SOC_FOR_START_LOAD_SHEDDING_2_IN_ADD_TIME_RANGE:
			case LIMIT_SOC_FOR_STOP_LOAD_SHEDDING_1_IN_ADD_TIME_RANGE:
			case LIMIT_SOC_FOR_STOP_LOAD_SHEDDING_2_IN_ADD_TIME_RANGE:
			case LIMIT_VALUE_SOC_FOR_START_LOAD_SHEDDING_1:
			case LIMIT_VALUE_SOC_FOR_START_LOAD_SHEDDING_2:
			case LIMIT_VALUE_SOC_FOR_STOP_LOAD_SHEDDING_1:
			case LIMIT_VALUE_SOC_FOR_STOP_LOAD_SHEDDING_2:
			case LOWER_LIMIT_DEEP_DISCHARGE_PROTECT_AREA_PRIOR_SHUTDOWN:
			case MAXIMUM_AC_BATTERY_CHARGE_CURRENT:
			case MAXIMUM_DURATION_OF_ENERGY_SAVING_MODE:
			case MAXIMUM_GRID_REVERSE_POWER:
			case MAXIMUM_GRID_REVERSE_POWER_TRIPPING_TIME:
			case MAXIUMUM_CURRENT_FROM_PUBLIC_GRID:
			case MEMORY_CARD_STATUS:
			case MINIMUM_WIDTH_OF_BAKCUP_POWER_AREA:
			case MINIMUM_WIDTH_OF_DEEP_DISCHARGE_PROTECTION_AREA:
			case MINIMUM_WIDTH_OF_OWN_CONSUMPTION_AREA:
			case MOST_PRODUCTIVE_MONTH_FOR_BATTERY_USAGE_RANGE:
			case OPERATING_TIME_FOR_TIME_CONTROLLED_INVERTER:
			case OUTPUT_RESISTANCE_OF_BATTERY_CONNECTION:
			case POWER_FEEDBACK_TO_PUBLIC_GRID_ALLOWED:
			case REPETITION_CYCLE_OF_TIME_CONTROLLED_INVERTER:
			case SEASON_OPERATION_ACTIVE:
			case SENSIVITY_OF_GENERATOR_FAILURE_DETECTION:
			case START_FEED_IN_PV:
			case START_INTERVAL_GRID_REQUEST:
			case START_TIME_ADDITIONAL_TIME_RANGE_LOAD_SHEDDING_1:
			case START_TIME_ADDITIONAL_TIME_RANGE_LOAD_SHEDDING_2:
			case START_TIME_ADDTIONAL_TIME_RANGE_GRID_REQUEST:
			case START_TIME_FOR_TIME_CONTROLLED_INVERTER:
			case START_TIME_OF_BATTERY_PROTECTION_MODE_LEVEL:
			case STOP_FEED_IN_PV:
			case TIME_LOAD_SHEDDING_1:
			case TIME_LOAD_SHEDDING_2:
			case TIME_OF_THE_AUTOMATIC_UPDATE:
			case TIME_OUT_FOR_COMMUNICATION_FAULT_INDICATION:
			case TIME_UNTIL_CHANGE_OVER_TO_ENERGY_SAVING_MODE:
			case TYPE_OF_ADDTIONAL_DC_SOURCES:
			case UPDATE_VERSION_OF_THE_MAIN_PROCESSOR:
			case VOLTAGE_SET_POINT_WITH_DEACTIVATED_BATTERY_MENAGEMENT:
			case AREA_WIDTH_FOR_CONSERVING_SOC:
			case OPERATING_MODE_OF_ACTIVE_POWER_LIMITATION_AT_OVERFREQUENCY:
			case MAXIMUM_BATTERY_CHARGING_POWER_CAPACITY:
			case MAXIMUM_BATTERY_DISCHARGING_POWER_CAPACITY:
			case INITIATE_DEVICE_RESTART:
				result.add(new IntegerWriteChannel(c, channelId));
				break;
			}
			return null;
		}
		return result.stream();
	}
}
