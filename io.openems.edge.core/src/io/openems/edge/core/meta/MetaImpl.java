package io.openems.edge.core.meta;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

import io.openems.common.OpenemsConstants;
import io.openems.edge.common.component.AbstractOpenemsComponent;
import io.openems.edge.common.component.OpenemsComponent;
import io.openems.edge.common.meta.Meta;
import io.openems.edge.common.modbusslave.ModbusSlave;

@Component(//
		name = "Core.Meta", //
		immediate = true, //
		property = { //
				"id=" + OpenemsConstants.META_ID, //
				"enabled=true" //
		})
public class MetaImpl extends AbstractOpenemsComponent implements Meta, OpenemsComponent, ModbusSlave {

	public MetaImpl() {
		Utils.initializeChannels(this).forEach(channel -> this.addChannel(channel));
	}

	@Activate
	void activate(ComponentContext context) {
		super.activate(context, OpenemsConstants.META_ID, true);
	}

	@Deactivate
	protected void deactivate() {
		super.deactivate();
	}

}
