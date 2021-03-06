package io.openems.edge.bridge.modbus.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.openems.edge.bridge.modbus.api.element.ModbusElement;
import io.openems.edge.bridge.modbus.api.task.ReadTask;
import io.openems.edge.bridge.modbus.api.task.Task;
import io.openems.edge.bridge.modbus.api.task.WriteTask;
import io.openems.edge.common.taskmanager.TasksManager;

public class ModbusProtocol {

	private final Logger log = LoggerFactory.getLogger(ModbusProtocol.class);

	/**
	 * The Parent component.
	 */
	private final AbstractOpenemsModbusComponent parent;

	/**
	 * TaskManager for ReadTasks.
	 */
	private final TasksManager<ReadTask> readTaskManager = new TasksManager<>();

	/**
	 * TaskManager for WriteTasks.
	 */
	private final TasksManager<WriteTask> writeTaskManager = new TasksManager<>();

	public ModbusProtocol(AbstractOpenemsModbusComponent parent, Task... tasks) {
		this.parent = parent;
		for (Task task : tasks) {
			addTask(task);
		}
	}

	public synchronized void addTask(Task task) {
		// add the the parent to the Task
		task.setParent(this.parent);
		// check abstractTask for plausibility
		this.checkTask(task);
		/*
		 * fill writeTasks
		 */
		if (task instanceof WriteTask) {
			this.writeTaskManager.addTask((WriteTask) task);
		}
		/*
		 * fill readTaskManager
		 */
		if (task instanceof ReadTask) {
			this.readTaskManager.addTask((ReadTask) task);
		}
	}

	public synchronized void removeTask(Task task) {
		if (task instanceof ReadTask) {
			this.readTaskManager.removeTask((ReadTask) task);
		}
		if (task instanceof WriteTask) {
			this.writeTaskManager.removeTask((WriteTask) task);
		}
	}

	/**
	 * Returns the next list of WriteTasks that should be executed within one cycle.
	 * 
	 * @return a list of WriteTasks
	 */
	public List<WriteTask> getNextWriteTasks() {
		return this.writeTaskManager.getNextTasks();
	}

	/**
	 * Returns one WriteTask sequentially.
	 * 
	 * @return a WriteTasks
	 */
	public WriteTask getOneWriteTask() {
		return this.writeTaskManager.getOneTask();
	}

	/**
	 * Returns the next list of ReadTasks that should be executed within one cycle.
	 * 
	 * @return a list of ReadTasks
	 */
	public List<ReadTask> getNextReadTasks() {
		return this.readTaskManager.getNextTasks();
	}

	/**
	 * Returns one ReadTask sequentially.
	 * 
	 * @return a ReadTasks
	 */
	public ReadTask getOneReadTask() {
		return this.readTaskManager.getOneTask();
	}

	/**
	 * Checks a {@link Task} for plausibility.
	 *
	 * @param task the Task that should be checked
	 */
	private synchronized void checkTask(Task task) {
		int address = task.getStartAddress();
		for (ModbusElement<?> element : task.getElements()) {
			if (element.getStartAddress() != address) {
				log.error("Start address is wrong. It is [" + element.getStartAddress() + "/0x"
						+ Integer.toHexString(element.getStartAddress()) + "] but should be [" + address + "/0x"
						+ Integer.toHexString(address) + "].");
			}
			address += element.getLength();
			// TODO: check BitElements
		}
	}
}
