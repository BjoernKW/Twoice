package twoice

import org.cometd.bayeux.Message
import org.cometd.bayeux.client.ClientSessionChannel

class TwoiceSubscriberListener implements ClientSessionChannel.MessageListener {
	def callbackObject
	def callbackMethod
	def callbackParams
	
	public TwoiceSubscriberListener(def object, def methodName, def params = []) {
		callbackObject = object
		callbackMethod = methodName
		callbackParams = params
	}
	
	public void onMessage(ClientSessionChannel channel, Message message) {
		def callbackParamValues = []
		callbackParams.each { param ->
			callbackParamValues.add(message.data.payload."${param}")
		}
		
		callbackObject."${callbackMethod}"(*callbackParamValues)
	}
}
