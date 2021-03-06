package io.openems.common.websocket;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

import org.java_websocket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.openems.common.exceptions.OpenemsError.OpenemsNamedException;
import io.openems.common.jsonrpc.base.JsonrpcRequest;
import io.openems.common.jsonrpc.base.JsonrpcResponse;
import io.openems.common.jsonrpc.base.JsonrpcResponseError;
import io.openems.common.jsonrpc.base.JsonrpcResponseSuccess;

public class OnRequestHandler implements Runnable {

	private final Logger log = LoggerFactory.getLogger(OnRequestHandler.class);

	private final AbstractWebsocket<?> parent;
	private final WebSocket ws;
	private final JsonrpcRequest request;
	private final Consumer<JsonrpcResponse> responseCallback;

	public OnRequestHandler(AbstractWebsocket<?> parent, WebSocket ws, JsonrpcRequest request,
			Consumer<JsonrpcResponse> responseCallback) {
		this.parent = parent;
		this.ws = ws;
		this.request = request;
		this.responseCallback = responseCallback;
	}

	@Override
	public final void run() {
		JsonrpcResponse response;
		try {
			CompletableFuture<JsonrpcResponseSuccess> responseFuture = this.parent.getOnRequest().run(this.ws,
					this.request);
			// Get success response
			response = responseFuture.get();
		} catch (OpenemsNamedException e) {
			// Get Named Exception error response
			this.parent.logWarn(this.log, "JSON-RPC Error Response: " + e.getMessage());
			response = new JsonrpcResponseError(request.getId(), e);
		} catch (ExecutionException | InterruptedException e) {
			// Get GENERIC error response
			this.parent.logWarn(this.log, "JSON-RPC Error Response: " + e.getMessage());
			response = new JsonrpcResponseError(request.getId(), e.getMessage());
		}

		responseCallback.accept(response);
	}

}
