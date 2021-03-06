package io.openems.edge.ess.sinexcel;

import org.osgi.service.metatype.annotations.ObjectClassDefinition;

import org.osgi.service.metatype.annotations.AttributeDefinition;

@ObjectClassDefinition( //
		name = "ESS Sinexcel", //
		description = "Implements the Sinexcel battery inverter.")
@interface Config {
	String service_pid();

	String id() default "ess0";

	boolean enabled() default true;

	@AttributeDefinition(name = "Modbus-ID", description = "ID of Modbus brige.")
	String modbus_id();

	@AttributeDefinition(name = "Modbus target filter", description = "This is auto-generated by 'Modbus-ID'.")
	String Modbus_target() default "";

	@AttributeDefinition(name = "Battery-ID", description = "ID of Battery.")
	String battery_id();

	@AttributeDefinition(name = "Battery target filter", description = "This is auto-generated by 'Battery-ID'.")
	String Battery_target() default "";

	@AttributeDefinition(name = "Start and standby", description = "Turn ON and turn OFF the Inverter")
	InverterState InverterState() default InverterState.ON;

	String webconsole_configurationFactory_nameHint() default "ESS Sinexcel [{id}]";
}